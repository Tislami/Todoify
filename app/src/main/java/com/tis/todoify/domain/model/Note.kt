package com.tis.todoify.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.tis.todoify.utils.NoteItemConverter
import java.util.*



@Entity
@TypeConverters(NoteItemConverter::class)
data class Note(
    @PrimaryKey(autoGenerate = true) val id : Int = 0,
    val title: String = "",
    val description: String = "",
    val noteItemList: List<NoteItem> = emptyList(),
    val tag: String="Todo",
    //val date: Calendar = Calendar.getInstance()
)

sealed class NoteItem {
    abstract val state: NoteItemState
}

data class TodoItem(
    val text: String = "",
    val isComplete: Boolean = false,
) : NoteItem() {
    override val state = NoteItemState.TodoItem
}

data class TextFieldItem(
    val text: String = "",
) : NoteItem() {
    override val state = NoteItemState.TextField
}

data class TableItem(
    val columnCount: Int,
    val rowCount: Int,
    val tableValues: List<String> = listOf(),
) : NoteItem() {
    override val state = NoteItemState.Table
}

enum class NoteItemState{
    TodoItem,
    TextField,
    Table
}
