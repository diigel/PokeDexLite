package com.example.pokedexlite.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface Screen {
    @Serializable
    data object Login : Screen

    @Serializable
    data object Register : Screen

    @Serializable
    data object Main : Screen

    @Serializable
    data class Detail(val pokemonName: String) : Screen
}
