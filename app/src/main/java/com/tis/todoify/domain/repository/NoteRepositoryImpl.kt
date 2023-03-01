package com.tis.todoify.domain.repository

import com.tis.todoify.data.local.repository.NoteRepository
import com.tis.todoify.domain.model.Note
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImpl() : NoteRepository {
    /*override fun getAllNote(): Flow<List<Note>> {

    }

    override fun findByTitle(): Flow<Note?> {
        return null
    }*/

    override suspend fun insert(){

    }

    override suspend fun delete(){

    }

    override suspend fun update(){

    }
}