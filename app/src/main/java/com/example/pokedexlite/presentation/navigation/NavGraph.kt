package com.example.pokedexlite.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pokedexlite.presentation.auth.LoginScreen
import com.example.pokedexlite.presentation.auth.RegisterScreen
import com.example.pokedexlite.presentation.detail.DetailScreen
import com.example.pokedexlite.presentation.main.MainScreen

@Composable
fun NavGraph(startDestination: String) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        // ── Auth ──────────────────────────────────────────────────────────────
        composable(Screen.Login.route) {
            LoginScreen(
                onNavigateToRegister = {
                    navController.navigate(Screen.Register.route)
                },
                onLoginSuccess = {
                    navController.navigate(Screen.Main.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.Register.route) {
            RegisterScreen(
                onNavigateToLogin = { navController.popBackStack() },
                onRegisterSuccess = {
                    navController.navigate(Screen.Main.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            )
        }

        // ── Main (tabbed) ─────────────────────────────────────────────────────
        composable(Screen.Main.route) {
            MainScreen(
                onPokemonClick = { name ->
                    navController.navigate(Screen.Detail.createRoute(name))
                },
                onLogout = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Main.route) { inclusive = true }
                    }
                }
            )
        }

        // ── Detail ────────────────────────────────────────────────────────────
        composable(
            route = Screen.Detail.route,
            arguments = listOf(
                navArgument("pokemonName") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val name = backStackEntry.arguments?.getString("pokemonName") ?: ""
            DetailScreen(
                pokemonName = name,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
