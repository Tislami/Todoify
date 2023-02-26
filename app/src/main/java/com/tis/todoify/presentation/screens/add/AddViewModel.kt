package com.tis.todoify.presentation.screens.add

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.tis.todoify.presentation.screens.add.components.TodoListItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor() : ViewModel(){

    val contents = mutableStateListOf<@Composable (() -> Unit)>()
    val descriptions = mutableStateListOf<MutableState<String>>()

    var todoItemStateList = mutableStateListOf<TodoItemState>()
        private set

    fun addTodoItem(){
        todoItemStateList.add(
            TodoItemState(false,"")
        )
    }

    fun setTodoItemText(index: Int,text: String){
        todoItemStateList[index].text = text
    }

    fun setTodoItemValue(index: Int, isComplete: Boolean){
        todoItemStateList[index].isComplete = isComplete
    }
}



data class TodoItemState(
    var isComplete: Boolean,
    var text: String
)