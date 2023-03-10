package com.tis.todoify.presentation.screens.add

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tis.todoify.data.local.repository.NoteRepository
import com.tis.todoify.domain.model.BackgroundState
import com.tis.todoify.domain.model.Note
import com.tis.todoify.domain.model.NoteItem
import com.tis.todoify.domain.model.TableItem
import com.tis.todoify.utils.extensions.BackgroundStyle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(
    private val repository: NoteRepository
) : ViewModel() {

    val noteState = mutableStateOf(Note())

    private val _eventFlow = MutableSharedFlow<AddUiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()


    fun setTitle(value: String) {
        noteState.value = noteState.value.copy(title = value)
    }

    fun updateBackgroundState(backgroundState: BackgroundState) {
        Log.d("ColorPickerTagVM", "updateBackgroundState: $backgroundState")
        noteState.value = noteState.value.copy(
            backgroundState = backgroundState
        )
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

    fun getNoteById(id: Int){
        viewModelScope.launch {
            repository.findById(id).collect{
                noteState.value = it
            }
        }
    }
    fun update(){
        viewModelScope.launch {
            repository.update(noteState.value)
        }
    }
    fun save(){
        viewModelScope.launch {
            noteState.value= noteState.value.copy(
                date = SimpleDateFormat().format(Date()),
            )
            repository.insert(noteState.value)
        }
    }
}


sealed class AddUiEvent(){
    object Save : AddUiEvent()
    data class Error(val message: String) : AddUiEvent()
}


enum class TableOperation {
    DeleteRow,
    DeleteColumn
}