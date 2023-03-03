package com.tis.todoify.domain.use_case

import com.tis.todoify.data.local.repository.NoteRepository
import com.tis.todoify.domain.model.Note

class Delete(private val repository: NoteRepository){

    suspend operator fun invoke(note: Note) = repository.delete(note)

}