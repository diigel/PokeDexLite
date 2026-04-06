package com.example.pokedexlite.presentation.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val PokeRed = Color(0xFFCC0000)
private val PokeBlue = Color(0xFF3B5BA5)
private val PokeYellow = Color(0xFFFFCB05)
private val Surface = Color(0xFFF5F5F5)
private val OnSurface = Color(0xFF1C1B1F)

private val LightColors = lightColorScheme(
    primary = PokeRed,
    onPrimary = Color.White,
    primaryContainer = Color(0xFFFFDAD6),
    secondary = PokeBlue,
    onSecondary = Color.White,
    tertiary = PokeYellow,
    background = Surface,
    onBackground = OnSurface,
    surface = Color.White,
    onSurface = OnSurface
)

@Composable
fun PokeDexLiteTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColors,
        content = content
    )
}
