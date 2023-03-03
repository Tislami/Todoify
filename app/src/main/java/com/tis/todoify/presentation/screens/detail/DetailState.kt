package com.tis.todoify.presentation.screens.detail

import androidx.room.Update
import com.tis.todoify.domain.model.Note

data class DetailState(
    val note: Note = Note(),
    val isUpdate: Boolean = false,
)
