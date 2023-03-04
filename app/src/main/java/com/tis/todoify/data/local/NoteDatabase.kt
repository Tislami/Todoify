package com.tis.todoify.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tis.todoify.domain.model.Note
import com.tis.todoify.utils.NoteItemTypeConverter

@Database(entities = [Note::class], version = 1)
@TypeConverters(NoteItemTypeConverter::class)
abstract class NoteDatabase : RoomDatabase() {
    abstract val noteDao : NoteDao
}
