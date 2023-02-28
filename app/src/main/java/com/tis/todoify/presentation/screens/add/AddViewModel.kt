package com.tis.todoify.presentation.screens.add

import NoteItem
import NoteModel
import TableItem
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor() : ViewModel() {

    val noteState = mutableStateOf(NoteModel())

    fun setTitle(value: String) {
        noteState.value = noteState.value.copy(title = value)
    }

    fun addNoteItem(noteItem: NoteItem) {
        val newList = noteState.value.noteItemList.toMutableList()
            .apply { add(noteItem) }
        noteState.value = noteState.value.copy(noteItemList = newList)
    }

    fun deleteNoteItem(noteItem: NoteItem) {
        val newList = noteState.value.noteItemList.toMutableList()
            .apply {
                if (size > 1) remove(noteItem)
            }
        noteState.value = noteState.value.copy(noteItemList = newList)
    }

    fun updateNoteItem(oldNoteItem: NoteItem, newNoteItem: NoteItem) {
        val index = noteState.value.noteItemList.indexOf(oldNoteItem)
        if (index >= 0) {
            noteState.value = noteState.value.copy(
                noteItemList = noteState.value.noteItemList.toMutableList()
                    .apply { set(index, newNoteItem) }
            )
        }
    }

    fun updateTableCell(noteItem: TableItem, operation: TableOperation): NoteItem {
        return when (operation) {
            TableOperation.DeleteRow -> {
                val newRowCount = noteItem.rowCount - 1
                val newTableValues = noteItem.tableValues.filterIndexed { index, _ ->
                    index < newRowCount * noteItem.columnCount
                }
                noteItem.copy(
                    rowCount = newRowCount,
                    tableValues = newTableValues
                )
            }
            TableOperation.DeleteColumn -> {
                val newColumnCount = noteItem.columnCount - 1
                val newTableValues =
                    noteItem.tableValues.filterIndexed { index, _ ->
                        (index + 1) % noteItem.columnCount != 0
                    }
                noteItem.copy(
                    columnCount = newColumnCount,
                    tableValues = newTableValues
                )
            }
        }
    }
}


enum class TableOperation {
    DeleteRow,
    DeleteColumn
}