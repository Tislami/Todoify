package com.tis.todoify.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tis.todoify.domain.model.NoteItem

class NoteItemConverter {
    @TypeConverter
    fun fromJson(json: String): List<NoteItem> {
        val type = object : TypeToken<List<NoteItem>>() {}.type
        return Gson().fromJson(json, type)
    }

    @TypeConverter
    fun toJson(list: List<NoteItem>): String {
        return Gson().toJson(list)
    }
}