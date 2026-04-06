package com.example.pokedexlite.domain.usecase.pokemon

import com.example.pokedexlite.domain.model.Pokemon
import com.example.pokedexlite.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class SearchPokemonUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {
    operator fun invoke(query: String): Flow<List<Pokemon>> {
        if (query.isBlank()) return flowOf(emptyList())
        return pokemonRepository.searchPokemon(query.trim())
    }
}
