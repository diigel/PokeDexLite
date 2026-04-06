package com.example.pokedexlite.domain.model

data class Pokemon(
    val id: Int,
    val name: String,
    val abilities: List<String>,
    val page: Int = 0
)