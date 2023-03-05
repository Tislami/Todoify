package com.tis.todoify.utils

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.room.TypeConverter
import com.google.gson.*
import com.google.gson.reflect.TypeToken
import com.tis.todoify.domain.model.*
import java.sql.Date


class NoteItemTypeConverter {
    @TypeConverter
    fun fromNoteItemList(noteItemList: List<NoteItem>): String {
        val jsonArray = JsonArray()

        noteItemList.forEach {
            val jsonObject = JsonObject()
            jsonObject.addProperty("type", it.state.toString())
            when (it) {
                is TextFieldItem -> {
                    jsonObject.addProperty("text", it.text)
                }
                is TodoItem -> {
                    jsonObject.addProperty("text", it.text)
                    jsonObject.addProperty("isComplete", it.isComplete)
                }
                is TableItem -> {
                    jsonObject.addProperty("columnCount", it.columnCount)
                    jsonObject.addProperty("rowCount", it.rowCount)
                    jsonObject.add("tableValues", Gson().toJsonTree(it.tableValues))
                }
            }
            jsonArray.add(jsonObject)
        }
        return jsonArray.toString()
    }

    @TypeConverter
    fun toNoteItemList(noteItemString: String): List<NoteItem> {
        val noteItemList = mutableListOf<NoteItem>()
        val jsonArray = JsonParser.parseString(noteItemString).asJsonArray
        jsonArray.forEach {
            val jsonObject = it.asJsonObject
            when (NoteItemState.valueOf(jsonObject.get("type").asString)) {
                NoteItemState.TextField -> {
                    val text = jsonObject.get("text").asString
                    noteItemList.add(TextFieldItem(text))
                }
                NoteItemState.TodoItem -> {
                    val text = jsonObject.get("text").asString
                    val isComplete = jsonObject.get("isComplete").asBoolean
                    noteItemList.add(TodoItem(text, isComplete))
                }
                NoteItemState.Table -> {
                    val columnCount = jsonObject.get("columnCount").asInt
                    val rowCount = jsonObject.get("rowCount").asInt
                    val tableValues : List<String> = Gson().fromJson(
                        jsonObject.get("tableValues"),
                        object : TypeToken<List<String>>() {}.type
                    ) ?: emptyList()
                    noteItemList.add(TableItem(columnCount, rowCount, tableValues))
                }
            }
        }
        return noteItemList
    }
}


class BackgroundStateConverter {
    @TypeConverter
    fun fromBackgroundState(backgroundState: BackgroundState): String {
        return Gson().toJson(backgroundState)
    }

    @TypeConverter
    fun toBackgroundState(backgroundStateString: String): BackgroundState {
        return Gson().fromJson(backgroundStateString, BackgroundState::class.java)
    }
}