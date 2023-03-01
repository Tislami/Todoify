package com.tis.todoify.domain.model

import java.util.*




data class Note(
    val title: String = "",
    val description: String = "",
    val noteItemList: List<NoteItem> = listOf(TextFieldItem("")),
    val tag: String="Todo",
    val id: UUID = UUID.randomUUID(),
    val date: Calendar = Calendar.getInstance()
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
