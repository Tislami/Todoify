package com.tis.todoify.presentation.screens.detail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tis.todoify.data.local.repository.NoteRepository
import com.tis.todoify.domain.model.NoteItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: NoteRepository
) : ViewModel() {

    var detailState = mutableStateOf(DetailState())
        private set

    fun getNote(id: Int){
        viewModelScope.launch {
            repository.findById(id).collect{ note->
                detailState.value = detailState.value.copy(
                    note = note!!
                )
            }
        }
    }

    fun updateNoteItem(oldNoteItem: NoteItem, newNoteItem: NoteItem) {
        val index = detailState.value.note.noteItemList.indexOf(oldNoteItem)
        if (index >= 0) {
            detailState.value = detailState.value.copy(
                isUpdate = true,
                note = detailState.value.note.copy(
                    noteItemList = detailState.value.note.noteItemList.toMutableList()
                        .apply { set(index, newNoteItem) }
                )
            )
        }
    }

    fun update(){
        viewModelScope.launch {
            val note = detailState.value.note.copy(
                date = SimpleDateFormat().format(Date())
            )
            repository.update(note)
        }
    }
}