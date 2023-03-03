package com.tis.todoify.data.local.repository

import com.tis.todoify.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    fun getAllNote() : Flow<List<Note>>
    fun findById(id: Int): Flow<Note?>
    suspend fun update(note: Note)
    suspend fun delete(note: Note)
    suspend fun insert(note: Note)
}