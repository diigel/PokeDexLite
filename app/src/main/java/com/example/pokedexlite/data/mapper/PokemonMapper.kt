package com.example.pokedexlite.data.mapper

import com.example.pokedexlite.data.remote.dto.PokemonDetailResponse
import com.example.pokedexlite.domain.model.Pokemon

fun PokemonDetailResponse.toDomain(page: Int = 0): Pokemon = Pokemon(
    id = id,
    name = name,
    abilities = abilities.map { it.ability.name },
    page = page
)
