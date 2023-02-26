package com.tis.todoify.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = YellowDark,
    onPrimary = Black,
    onSurface = White,
    background = Black3,
    onBackground = White

)

private val LightColorPalette = lightColors(
    primary = YellowLight,
    onPrimary = White,
    onSurface = White,
    background = White,
    onBackground = Black
)

@Composable
fun TodoifyTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}