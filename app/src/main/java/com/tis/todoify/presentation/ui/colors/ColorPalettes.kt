package com.tis.todoify.presentation.ui.colors

import androidx.compose.ui.graphics.Color

data class ColorPalettes(
    val list: List<ColorPalette> = listOf(
        ColorPalette200(),
        ColorPalette400(),
        ColorPaletteA100(),
    )
)

interface ColorPalette{
    val name: String
    val colors : List<Color>
}