package com.example.pokedexlite.domain.usecase.pokemon

import com.example.pokedexlite.domain.model.Pokemon
import com.example.pokedexlite.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPokemonsUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {
    operator fun invoke(page: Int): Flow<List<Pokemon>> {
        return pokemonRepository.getPokemonsByPage(page)
    }
}
