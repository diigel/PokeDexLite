package com.example.pokedexlite.domain.repository

import com.example.pokedexlite.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    fun getPokemonsByPage(page: Int): Flow<List<Pokemon>>
    suspend fun getPokemonDetail(name: String): Result<Pokemon>
    fun searchPokemon(query: String): Flow<List<Pokemon>>
}
