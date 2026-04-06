package com.example.pokedexlite.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pokedexlite.presentation.auth.LoginScreen
import com.example.pokedexlite.presentation.auth.RegisterScreen
import com.example.pokedexlite.presentation.detail.DetailScreen
import com.example.pokedexlite.presentation.main.MainScreen
import androidx.navigation.toRoute

@Composable
fun NavGraph(startDestination: Any) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        // ── Auth ──────────────────────────────────────────────────────────────
        composable<Screen.Login> {
            LoginScreen(
                onNavigateToRegister = {
                    navController.navigate(Screen.Register)
                },
                onLoginSuccess = {
                    navController.navigate(Screen.Main) {
                        popUpTo(Screen.Login) { inclusive = true }
                    }
                }
            )
        }

        composable<Screen.Register> {
            RegisterScreen(
                onNavigateToLogin = { navController.popBackStack() },
                onRegisterSuccess = {
                    navController.navigate(Screen.Main) {
                        popUpTo(Screen.Login) { inclusive = true }
                    }
                }
            )
        }

        // ── Main (tabbed) ─────────────────────────────────────────────────────
        composable<Screen.Main> {
            MainScreen(
                onPokemonClick = { name ->
                    navController.navigate(Screen.Detail(pokemonName = name))
                },
                onLogout = {
                    navController.navigate(Screen.Login) {
                        popUpTo(Screen.Main) { inclusive = true }
                    }
                }
            )
        }

        // ── Detail ────────────────────────────────────────────────────────────
        composable<Screen.Detail> { backStackEntry ->
            val detail = backStackEntry.toRoute<Screen.Detail>()
            DetailScreen(
                pokemonName = detail.pokemonName,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
