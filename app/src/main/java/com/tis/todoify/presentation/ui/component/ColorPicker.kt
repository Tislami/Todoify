package com.tis.todoify.presentation.ui.component

import com.godaddy.android.colorpicker.HsvColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.godaddy.android.colorpicker.harmony.ColorHarmonyMode
import com.godaddy.android.colorpicker.harmony.HarmonyColorPicker

@Composable
fun ColorPicker(
    modifier: Modifier,
    onColorChanged : (Color)->Unit,
) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colors.background,
    ) {
        HarmonyColorPicker(
            harmonyMode = ColorHarmonyMode.NONE,
            color = Color.White,
            onColorChanged = { color : HsvColor->
                onColorChanged(color.toColor())
            })
    }
}