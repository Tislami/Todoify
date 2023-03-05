package com.tis.todoify.presentation.screens.add.components

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.tis.todoify.utils.onClick
import androidx.compose.material.IconButton
import androidx.compose.ui.graphics.toArgb
import com.tis.todoify.domain.model.*
import com.tis.todoify.presentation.ui.component.ColorPickerDialog
import com.tis.todoify.utils.extensions.BackgroundStyle

@Composable
fun AddTopAppBar(
    backgroundState: BackgroundState,
    backOnClick: onClick,
    addNoteItem: (NoteItem) -> Unit,
    onBackgroundStateChange: (BackgroundState) -> Unit,
) {
    val showColorPikerDialog = remember { mutableStateOf(false) }

    TopAppBar(
        backgroundColor = Color(backgroundState.colors[0]),
        elevation = 0.dp,
        title = {},
        navigationIcon = {
            IconButton(onClick = backOnClick) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = MaterialTheme.colors.onSurface
                )
            }
        },
        actions = {

            IconButton(onClick = { addNoteItem(TextFieldItem()) }) {
                Icon(
                    painter = painterResource(id = com.tis.todoify.R.drawable.text_fields_24),
                    contentDescription = "Add TextField",
                    tint = MaterialTheme.colors.onSurface
                )
            }
            IconButton(onClick = { addNoteItem(TodoItem()) }) {
                Icon(
                    painter = painterResource(id = com.tis.todoify.R.drawable.add_task_24),
                    contentDescription = "Add Task",
                    tint = MaterialTheme.colors.onSurface
                )
            }
            IconButton(onClick = {
                addNoteItem(TableItem(columnCount = 3, rowCount = 3, tableValues = buildList {
                    repeat(3 * 3) { add("$it") }
                }))
            }) {
                Icon(
                    painter = painterResource(id = com.tis.todoify.R.drawable.table_rows_24),
                    contentDescription = "Add Table",
                    tint = MaterialTheme.colors.onSurface
                )
            }

            MoreContent(showColorPikerDialog)

            if (showColorPikerDialog.value) {
                Dialog(onDismissRequest = { showColorPikerDialog.value = false }) {
                    ColorPickerDialog(
                        backgroundState = backgroundState,
                        onChangeBackgroundState = onBackgroundStateChange,
                        onSave = { showColorPikerDialog.value = false }
                    )
                }
            }
        },
    )
}

@Composable
private fun MoreContent(showColorPikerDialog: MutableState<Boolean>) {
    var showMore by remember { mutableStateOf(false) }

    IconButton(onClick = { showMore = true }) {
        Icon(
            imageVector = Icons.Default.MoreVert,
            contentDescription = "More",
            tint = MaterialTheme.colors.onSurface
        )
    }

    DropdownMenu(
        expanded = showMore,
        onDismissRequest = { showMore = false },

        ) {
        DropdownMenuItem(onClick = { showColorPikerDialog.value = true }) {
            Text(text = "Background style")
        }
    }
}
