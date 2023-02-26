package com.tis.todoify.domain.model

import androidx.compose.ui.graphics.Color
import java.util.Date
import java.util.UUID

data class Note(
    val id: UUID = UUID.randomUUID(),
    val title: String = "",
    val description: String = "",
    val color: Color = Color.Unspecified,
    val date: Date = Date(),

    )


val defaultNote = Note(
    title = "Meet with Uqilo lead design",
    description = "Lorem ipsum dolor sit amet, consectetur adipisicing elit. A aliquam at, consequatur laborum quia quibusdam rem? Alias, consequuntur deleniti dolore illum inventore ipsum molestias natus nisi porro provident quibusdam, recusandae! ",
    color = Color(94, 53, 177, 255),
)
