package com.tis.todoify.domain.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.tis.todoify.utils.BackgroundStateConverter
import com.tis.todoify.utils.NoteItemTypeConverter
import com.tis.todoify.utils.extensions.BackgroundStyle


@Entity
@TypeConverters(NoteItemTypeConverter::class, BackgroundStateConverter::class)
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val title: String = "",
    val noteItemList: List<NoteItem> = listOf(TextFieldItem("")),
    val backgroundState: BackgroundState = BackgroundState(),
    val tag: String = "Todo",
    val date: String = "",
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

data class BackgroundState(
    val backgroundStyle: String = BackgroundStyle.OneColor.name,
    val colors: List<Int> = listOf(
        Color(62, 54, 63).toArgb(),
        Color(62, 54, 63).toArgb()
    )
)

enum class NoteItemState {
    TodoItem,
    TextField,
    Table
}