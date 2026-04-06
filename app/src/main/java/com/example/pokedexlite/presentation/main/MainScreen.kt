package com.example.pokedexlite.presentation.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CatchingPokemon
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.CatchingPokemon
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.pokedexlite.presentation.home.HomeScreen
import com.example.pokedexlite.presentation.profile.ProfileScreen
import kotlinx.serialization.Serializable

@Serializable
sealed interface MainRoute {
    @Serializable
    data object Home : MainRoute

    @Serializable
    data object Profile : MainRoute
}

sealed class BottomTab(
    val route: Any,
    val label: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
) {
    object Home : BottomTab(
        route = MainRoute.Home,
        label = "PokéDex",
        selectedIcon = Icons.Filled.CatchingPokemon,
        unselectedIcon = Icons.Outlined.CatchingPokemon
    )

    object Profile : BottomTab(
        route = MainRoute.Profile,
        label = "Profile",
        selectedIcon = Icons.Filled.Person,
        unselectedIcon = Icons.Outlined.Person
    )
}

@Composable
fun MainScreen(
    onPokemonClick: (String) -> Unit,
    onLogout: () -> Unit
) {
    val tabs = listOf(BottomTab.Home, BottomTab.Profile)
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        bottomBar = {
            NavigationBar {
                tabs.forEach { tab ->
                    val isSelected = currentDestination?.hasRoute(tab.route::class) ?: false
                    NavigationBarItem(
                        selected = isSelected,
                        onClick = {
                            navController.navigate(tab.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = if (isSelected)
                                    tab.selectedIcon else tab.unselectedIcon,
                                contentDescription = tab.label
                            )
                        },
                        label = { Text(tab.label) }
                    )
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = MainRoute.Home,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable<MainRoute.Home> {
                HomeScreen(onPokemonClick = onPokemonClick)
            }
            composable<MainRoute.Profile> {
                ProfileScreen(onLogout = onLogout)
            }
        }
    }
}
