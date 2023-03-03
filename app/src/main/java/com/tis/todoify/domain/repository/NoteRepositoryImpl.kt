package com.tis.todoify.domain.repository

import com.tis.todoify.data.local.NoteDao
import com.tis.todoify.data.local.repository.NoteRepository
import com.tis.todoify.domain.model.*
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImpl(private val dao: NoteDao) : NoteRepository {
    override fun getAllNote(): Flow<List<Note>> {
        return dao.getAllNote()
    }

    override fun findById(id: Int): Flow<Note> {
        return dao.findById(id)
    }

    override suspend fun insert(note: Note){
        dao.insert(note)
    }

    override suspend fun delete(note: Note){
        dao.delete(note)
    }

    override suspend fun update(note: Note){
        dao.update(note)
    }
}