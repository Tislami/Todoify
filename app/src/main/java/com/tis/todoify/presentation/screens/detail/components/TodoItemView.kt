package com.tis.todoify.presentation.screens.detail.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tis.todoify.R
import com.tis.todoify.domain.model.Note
import com.tis.todoify.domain.model.TodoItem
import com.tis.todoify.presentation.ui.component.AppTextField
import com.tis.todoify.utils.onClick

@Composable
fun TodoItemView(
    todoItem: TodoItem,
    onClick: (Boolean) -> Unit,
) {
    Row(
        modifier = Modifier
            .clickable { onClick(!todoItem.isComplete) }
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.Top
    ) {

        Icon(
            painter =
            if (todoItem.isComplete) painterResource(id = R.drawable.check_circle_24)
            else painterResource(id = R.drawable.outline_circle_24),
            contentDescription = null,
            modifier = Modifier
                .padding(end = 8.dp)
                .size(20.dp)
                .clip(CircleShape),
            tint = if (todoItem.isComplete) MaterialTheme.colors.primary
            else Color.White,
        )

        Text(
            text = todoItem.text,
            color = MaterialTheme.colors.onSurface,
            fontSize = 16.sp,
            textDecoration = if (todoItem.isComplete) TextDecoration.LineThrough
            else null
        )
    }
}