package com.tis.todoify.presentation.screens.home

import com.tis.todoify.domain.model.Note


data class HomeState(
    val noteList: List<Note> = emptyList(),
    val viewStyle : ViewStyle = ViewStyle.List,
    val query: String = "",

    )



enum class ViewStyle {
    List,
    Folder,
    Grid,
}