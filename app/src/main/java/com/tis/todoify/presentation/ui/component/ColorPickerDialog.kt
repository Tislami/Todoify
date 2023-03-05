package com.tis.todoify.presentation.ui.component

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.tis.todoify.R
import com.tis.todoify.domain.model.BackgroundState
import com.tis.todoify.presentation.screens.add.components.AddScreenPreview
import com.tis.todoify.presentation.ui.colors.ColorPalettes
import com.tis.todoify.utils.extensions.BackgroundStyle
import com.tis.todoify.utils.onClick


@Composable
fun ColorPickerDialog(
    backgroundState: BackgroundState,
    onChangeBackgroundState: (BackgroundState) -> Unit,
    onSave: onClick
) {

    var colorState by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .size(350.dp)
            .clip(MaterialTheme.shapes.medium)
            .border(1.dp, MaterialTheme.colors.primary, MaterialTheme.shapes.medium)
            .background(MaterialTheme.colors.background, MaterialTheme.shapes.medium),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        //Head
        Row(
            modifier = Modifier
                .height(200.dp)
                .padding(top = 8.dp),
        ) {
            ColorPicker(
                modifier = Modifier.weight(1f),
                onColorChanged = {
                    val newBackgroundState = backgroundState.copy(
                        colors = backgroundState.colors.toMutableList().apply {
                            set(colorState, it.toArgb())
                        })
                    onChangeBackgroundState(newBackgroundState)
                }
            )

            AddScreenPreview(
                modifier = Modifier
                    .padding(end = 8.dp)
                    .weight(1f),
                backgroundState = backgroundState,
                onBackgroundStateChange = onChangeBackgroundState,
                onSave = onSave
            )
        }

        MaterialsField(onSelect = {
            val newBackgroundState = backgroundState.copy(
                colors = backgroundState.colors.toMutableList().apply {
                    set(colorState, it.toArgb())
                }
            )
            onChangeBackgroundState(newBackgroundState)
        })

        Divider()

        if (backgroundState.backgroundStyle != BackgroundStyle.OneColor.name) {
            ColorsField(
                colors = backgroundState.colors.map { Color(it) },
                onSelect = { colorState = it },
                addColor = {
                    val newBackgroundState = backgroundState.copy(
                        colors = backgroundState.colors.toMutableList().apply {
                            if (size < 4) {
                                add(Color(62, 54, 63).toArgb())
                            }

                        }
                    )
                    onChangeBackgroundState(newBackgroundState)
                },
                removeColor = {
                    val newBackgroundState = backgroundState.copy(
                        colors = backgroundState.colors.toMutableList().apply {
                            if (size > 2) { removeLast() }
                        }
                    )
                    onChangeBackgroundState(newBackgroundState)
                }
            )
        }
    }
}

@Composable
private fun MaterialsField(onSelect: (Color) -> Unit) {
    val colorPalettes = ColorPalettes()

    var showMenu by remember { mutableStateOf(false) }
    var materialColors by remember { mutableStateOf(colorPalettes.list[0].colors) }
    var colorPaletteName by remember { mutableStateOf(colorPalettes.list[0].name) }

    Column(
        modifier = Modifier.padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        DropdownMenu(
            modifier = Modifier.fillMaxWidth(),
            expanded = showMenu,
            onDismissRequest = { showMenu = false }) {
            colorPalettes.list.forEach {
                DropdownMenuItem(onClick = {
                    showMenu = false
                    materialColors = it.colors
                    colorPaletteName = it.name
                }) {
                    Text(text = it.name)
                }
            }
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { showMenu = true },
        ) { Text(text = colorPaletteName) }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.Center,
        ) {
            materialColors.forEach {
                ColorButton(color = it) { onSelect(it) }
            }

        }
    }
}

@Composable
private fun ColorsField(
    colors: List<Color>,
    onSelect: (Int) -> Unit,
    addColor: () -> Unit,
    removeColor: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Colors",
                color = MaterialTheme.colors.onSurface,
                fontWeight = FontWeight.SemiBold,
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                colors.forEachIndexed { index: Int, color: Color ->
                    ColorButton(
                        color = color,
                        onClick = { onSelect(index) }
                    )
                }
            }
        }

        Row(modifier = Modifier.padding(end = 4.dp)) {
            Icon(
                painter = painterResource(id = R.drawable.add_circle_outline_24),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.clickable { addColor() }
            )

            Spacer(modifier = Modifier.width(2.dp))

            Icon(
                painter = painterResource(id = R.drawable.remove_circle_outline_24),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.clickable { removeColor() }
            )
        }
    }
}

@Composable
private fun ColorButton(
    color: Color,
    onClick: onClick,
) {
    IconButton(
        onClick = onClick
    ) {
        Surface(
            shape = CircleShape,
            border = BorderStroke(1.dp, Color.White)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.circle_fill_24),
                contentDescription = null,
                tint = color,
            )
        }
    }
}