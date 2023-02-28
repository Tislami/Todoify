package com.tis.todoify.presentation.screens.add

import NoteItem
import NoteModel
import TableItem
import TextFieldItem
import TodoItem
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tis.todoify.presentation.screens.add.components.*
import com.tis.todoify.presentation.ui.component.AppTextField

@Composable
fun AddScreen(
    addViewModel: AddViewModel = hiltViewModel()
) {
    val noteModelState = addViewModel.noteState.value
    Scaffold(
        topBar = {
            AddTopAppBar(
                addTextFieldItem = { addViewModel.addNoteItem(TextFieldItem()) },
                addTodoItem = { addViewModel.addNoteItem(TodoItem()) },
                addTable = {
                    addViewModel.addNoteItem(
                        TableItem(
                            columnCount = 3,
                            rowCount = 3,
                            tableValues = buildList {
                                repeat(3 * 3) { add("$it") }
                            }
                        )
                    )
                },
            )
        },
        floatingActionButton = { AddFab(onClick = {}) }
    ) { innerPadding ->
        AddContent(
            modifier = Modifier.padding(innerPadding),
            noteModel = noteModelState,
            setTitle = addViewModel::setTitle,
            addNoteItem = addViewModel::addNoteItem,
            updateNoteItem = addViewModel::updateNoteItem,
            deleteNoteItem = addViewModel::deleteNoteItem,
            updateTableCell = addViewModel::updateTableCell,
        )
    }
}

@Composable
fun AddContent(
    modifier: Modifier,
    noteModel: NoteModel,
    setTitle: (String) -> Unit,
    addNoteItem: (NoteItem) -> Unit,
    deleteNoteItem: (NoteItem) -> Unit,
    updateNoteItem: (NoteItem, NoteItem) -> Unit,
    updateTableCell: (TableItem, TableOperation) -> NoteItem,
) {
    val focusManager = LocalFocusManager.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {

        AppTextField(
            value = noteModel.title,
            onValueChange = setTitle,
            modifier = Modifier.padding(bottom = 8.dp),
            label = "Title",
            fonsSize = 24.sp,
            isFocused = false
        )

        Divider(color = MaterialTheme.colors.primary)

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(bottom = 8.dp)
        ) {

            noteModel.noteItemList.forEach { noteItem ->
                when (noteItem.state) {
                    NoteItemState.TodoItem -> {
                        TodoItemView(
                            todoItem = noteItem as TodoItem,
                            updateTodoItemText = { updateNoteItem(noteItem, noteItem.copy(text = it)) },
                            updateTodoItemValue = { updateNoteItem(noteItem, noteItem.copy(isComplete = it)) },
                            onBackspaceClick = {
                                deleteNoteItem(noteItem)
                                focusManager.moveFocus(FocusDirection.Left)
                            },
                            onDone = { addNoteItem(TextFieldItem()) }
                        )
                    }
                    NoteItemState.TextField -> {
                        AppTextField(
                            value = (noteItem as TextFieldItem).text,
                            onValueChange = { updateNoteItem(noteItem, noteItem.copy(text = it)) },
                            modifier = Modifier.padding(vertical = 8.dp),
                            onBackspaceClick = {
                                deleteNoteItem(noteItem)
                                focusManager.moveFocus(FocusDirection.Previous)
                            },
                            fonsSize = 18.sp,
                        )
                    }
                    NoteItemState.Table -> {
                        TableItemView(
                            tableItem = noteItem as TableItem,
                            updateTableItem = { index, value ->
                                updateNoteItem(noteItem,
                                    noteItem.copy(
                                        tableValues = noteItem.tableValues.toMutableList()
                                            .apply { set(index, value) }
                                    )
                                )
                            },
                            onDeleteRow = {
                                updateNoteItem(
                                    noteItem,
                                    updateTableCell(noteItem, TableOperation.DeleteRow)
                                )
                            },
                            onDeleteColumn = {
                                updateNoteItem(
                                    noteItem,
                                    updateTableCell(noteItem, TableOperation.DeleteColumn)
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}


//Todo: Hücrelerden yazıyı sildiğinde bir üst hücreyle birleştirmen
//Todo: Hücreler arası item ekleme
//Todo: Itemleri özelleştirme
//Todo: Table hücreleri verilerini düzgün ayarlama