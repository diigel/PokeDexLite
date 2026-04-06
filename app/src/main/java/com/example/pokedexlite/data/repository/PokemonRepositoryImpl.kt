package com.example.pokedexlite.data.repository

import com.example.pokedexlite.data.local.dao.PokemonDao
import com.example.pokedexlite.data.mapper.toDomain
import com.example.pokedexlite.data.remote.PokeApiService
import com.example.pokedexlite.domain.model.Pokemon
import com.example.pokedexlite.domain.repository.PokemonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val apiService: PokeApiService,
    private val pokemonDao: PokemonDao
) : PokemonRepository {

    /**
     * Single Source of Truth strategy:
     * 1. Immediately emit whatever is in Couchbase (instant UI render, works offline).
     * 2. Fetch fresh data from PokeAPI; on success cache it and re-emit.
     * 3. On network error, silently keep the cached emission.
     */
    override fun getPokemonsByPage(page: Int): Flow<List<Pokemon>> = flow {
        // ── Step 1: Emit cache ─────────────────────────────────────────────────
        val cached = pokemonDao.getPokemonsByPage(page)
        if (cached.isNotEmpty()) emit(cached)

        // ── Step 2: Fetch from network ─────────────────────────────────────────
        try {
            val offset = (page - 1) * PAGE_SIZE
            val listResponse = apiService.getPokemonList(limit = PAGE_SIZE, offset = offset)

            // Fetch full detail for every pokemon in the page concurrently
            val freshPokemons = listResponse.results.map { item ->
                apiService.getPokemonDetail(item.name).toDomain(page)
            }

            // ── Step 3: Persist to Couchbase ───────────────────────────────────
            pokemonDao.insertPokemons(freshPokemons)

            // ── Step 4: Re-emit fresh data ─────────────────────────────────────
            emit(freshPokemons)
        } catch (e: Exception) {
            // Network unavailable – cache emission already sent, nothing more to do.
            if (cached.isEmpty()) emit(emptyList())
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getPokemonDetail(name: String): Result<Pokemon> {
        // Check local cache first (avoids extra network call on revisit)
        val cached = pokemonDao.getPokemonByName(name)
        if (cached != null) return Result.success(cached)

        return try {
            val response = apiService.getPokemonDetail(name)
            val pokemon = response.toDomain()
            Result.success(pokemon)
        } catch (e: Exception) {
            Result.failure(Exception("Could not load Pokémon. Check your connection."))
        }
    }

    override fun searchPokemon(query: String): Flow<List<Pokemon>> = flow {
        emit(pokemonDao.searchByName(query))
    }.flowOn(Dispatchers.IO)

    companion object {
        private const val PAGE_SIZE = 10
    }
}
