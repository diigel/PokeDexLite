package com.example.pokedexlite.domain.usecase.pokemon

import com.example.pokedexlite.domain.model.Pokemon
import com.example.pokedexlite.domain.repository.PokemonRepository
import javax.inject.Inject

class GetPokemonDetailUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {
    suspend operator fun invoke(name: String): Result<Pokemon> {
        if (name.isBlank()) return Result.failure(IllegalArgumentException("Pokemon name cannot be empty"))
        return pokemonRepository.getPokemonDetail(name.lowercase().trim())
    }
}
