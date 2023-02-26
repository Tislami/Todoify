package com.tis.todoify.presentation.screens.add.components

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import com.tis.todoify.presentation.ui.component.AppTextField

fun addTodoItem(
    contents: SnapshotStateList<@Composable () -> Unit>,
    focusManager: FocusManager,
    onSave : ()->Unit = {}
) {
    val index = contents.size
    contents.add(index = index) {
        TodoListItem(
            onDone = {
                val textFieldIndex = contents.size
                contents.add(index = textFieldIndex) {
                    AppTextField(
                        onBackspaceClick = {
                            focusManager.moveFocus(FocusDirection.Up)
                            contents.removeAt(textFieldIndex)
                        },
                    )
                }
            },
            onBackspaceClick = {
                focusManager.moveFocus(FocusDirection.Previous)
                contents.removeAt(index)
            },
        )
    }
}

fun addTable(
    contents: SnapshotStateList<@Composable () -> Unit>,
    focusManager: FocusManager
) {
    val index = contents.size
    contents.add(index = index) {
        TableItem()
    }
    val textFieldIndex = contents.size
    contents.add(index = textFieldIndex) {
        AppTextField(
            onBackspaceClick = {
                focusManager.moveFocus(FocusDirection.Up)
                contents.removeAt(textFieldIndex)
                contents.removeAt(index)
            },
        )
    }
}