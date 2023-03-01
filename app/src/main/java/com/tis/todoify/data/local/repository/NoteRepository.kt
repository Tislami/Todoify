package com.tis.todoify.data.local.repository

import com.tis.todoify.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    /*fun getAllNote() : Flow<List<Note>>
    fun findByTitle() : Flow<Note?>*/
    suspend fun insert()
    suspend fun delete()
    suspend fun update()
}