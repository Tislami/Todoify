package com.tis.todoify.utils.extensions

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.tis.todoify.domain.model.BackgroundState

enum class BackgroundStyle {
    OneColor,
    VerticalGradient,
    HorizontalGradient,
    RadialGradient,
    LinearGradient
}

@Composable
fun Modifier.backgroundState(backgroundState: BackgroundState): Modifier = when (BackgroundStyle.valueOf(backgroundState.backgroundStyle)) {
    BackgroundStyle.OneColor -> background(Color(backgroundState.colors[0]))
    BackgroundStyle.VerticalGradient -> background(Brush.verticalGradient(backgroundState.colors.map { Color(it) }))
    BackgroundStyle.HorizontalGradient -> background(Brush.horizontalGradient(backgroundState.colors.map { Color(it) }))
    BackgroundStyle.RadialGradient -> background(Brush.radialGradient(backgroundState.colors.map { Color(it) }))
    BackgroundStyle.LinearGradient -> background(Brush.linearGradient(backgroundState.colors.map { Color(it) }))
}
