package com.tis.todoify.utils

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

class DateTypeConverter {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}
