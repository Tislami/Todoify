package com.tis.todoify

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.LinearGradient
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.tis.todoify.presentation.screens.add.AddScreen
import com.tis.todoify.presentation.screens.detail.DetailScreen
import com.tis.todoify.presentation.screens.home.HomeScreen
import com.tis.todoify.presentation.ui.component.AppTextField
import com.tis.todoify.presentation.ui.theme.TodoifyTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoifyTheme(darkTheme = true) {

                /* val mainViewModel = MainViewModel()

            val itemList = mainViewModel.itemList*/
/*

                Column(modifier = Modifier.background(Color.Gray)) {

                    for (item in itemList) {
                        TodoListItem(
                           noteItem =  item,
                            onValueChange = {
                                mainViewModel.updateTextItem(item,it)
                            }
                        )
                    }
                }*/
                AddScreen()
            }
        }
    }
}

/*

@Composable
private fun TodoListItem(
    noteItem: NoteItem,
    onValueChange: (String)-> Unit,
) {

    Row(
        modifier = Modifier.padding(vertical = 8.dp),
        verticalAlignment = Alignment.Top
    ) {

        Icon(
            painter =
            if (noteItem.value) painterResource(id = R.drawable.check_circle_24)
            else painterResource(id = R.drawable.outline_circle_24),
            contentDescription = null,
            modifier = Modifier
                .padding(end = 8.dp)
                .size(20.dp)
                .clip(CircleShape)
                .clickable { noteItem.value = !noteItem.value },
            tint = if (noteItem.value) MaterialTheme.colors.primary
            else Color.White,
        )

        TextField(value = noteItem.note.text, onValueChange = onValueChange)
    }
}


data class NoteItem(
    var note: Note2 = Note2(""),
    var value: Boolean = false
)

data class Note2(val text: String)


class MainViewModel() : ViewModel() {

    private val _itemList = mutableStateListOf<NoteItem>()
    val itemList: List<NoteItem> = _itemList


    init {
        getItem()
    }

    fun getItem() {

        val item = NoteItem(Note2("1"), false)
        val item1 = NoteItem(Note2("2"), false)
        val item2 = NoteItem(Note2("3"), true)


        _itemList.add(item)
        _itemList.add(item1)
        _itemList.add(item2)
    }


    fun updateTextItem(item: NoteItem, newVal: String) {
        val index = _itemList.indexOf(item)
        if (index >= 0) { // Öğe bulundu
            _itemList[index] =
                _itemList[index].copy(
                    note = _itemList[index].note.copy(
                        text = newVal
                )

            )
        } else { // Öğe bulunamadı
            Log.d("MainViewModel", "updateTextItem: Item not found")
        }
    }
}*/
