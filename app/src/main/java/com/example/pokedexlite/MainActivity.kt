package com.example.pokedexlite

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.pokedexlite.data.local.SessionManager
import com.example.pokedexlite.presentation.navigation.NavGraph
import com.example.pokedexlite.presentation.navigation.Screen
import com.example.pokedexlite.presentation.theme.PokeDexLiteTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokeDexLiteTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val startDestination: Any = if (sessionManager.isLoggedIn()) {
                        Screen.Main
                    } else {
                        Screen.Login
                    }
                    NavGraph(startDestination = startDestination)
                }
            }
        }
    }
}
