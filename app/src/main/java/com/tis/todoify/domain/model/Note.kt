package com.tis.todoify.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.tis.todoify.utils.NoteItemTypeConverter


@Entity
@TypeConverters(NoteItemTypeConverter::class)
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val title: String = "",
    val noteItemList: List<NoteItem> = listOf(TextFieldItem("")),
    val tag: String = "Todo",
    val date: String = "",
    val color: Int = 0,
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
    val tableValues: List<String>,
) : NoteItem() {
    override val state = NoteItemState.Table
}

enum class NoteItemState {
    TodoItem,
    TextField,
    Table
}