package com.tis.todoify.domain.model

data class FolderModel(
    val name: String = "",
    val noteList: List<Note> = emptyList()
)


val defaultFolderModel = FolderModel(
    name = "My Folder",
    noteList = listOf()
)


val defaultNoteList = listOf<Note>()