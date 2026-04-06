package com.example.pokedexlite.presentation.navigation

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Register : Screen("register")
    object Main : Screen("main")
    object Detail : Screen("detail/{pokemonName}") {
        fun createRoute(name: String) = "detail/$name"
    }
}
