package com.tis.todoify.presentation.screens.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tis.todoify.data.local.repository.NoteRepository
import com.tis.todoify.domain.model.Note
import com.tis.todoify.domain.repository.NoteRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: NoteRepository
) : ViewModel() {

    var homeState = mutableStateOf(HomeState())
        private set


    init { getAllNote() }

    private fun getAllNote(){
        viewModelScope.launch {
            repository.getAllNote().collect{ noteList->
                homeState.value = homeState.value.copy(
                    noteList = noteList
                )
            }
        }


    }

    fun delete(note: Note){
        viewModelScope.launch {
            repository.delete(note)
        }
    }


    fun update(note: Note){
        viewModelScope.launch {
            repository.update(note)
        }
    }

    fun changeViewStyle(viewStyle: ViewStyle){
        homeState.value = homeState.value.copy(
            viewStyle = viewStyle
        )
    }

    fun query(value: String){
        homeState.value = homeState.value.copy(
            query = value
        )
    }
}