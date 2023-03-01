package com.tis.todoify.presentation.screens.add.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tis.todoify.R
import com.tis.todoify.domain.model.TodoItem
import com.tis.todoify.presentation.ui.component.AppTextField

@Composable
fun TodoItemView(
    todoItem: TodoItem,
    updateTodoItemText : (String)->Unit,
    updateTodoItemValue : (Boolean)->Unit,
    onBackspaceClick: () -> Unit,
    onDone : () -> Unit,
    label : String? = null,
) {
    Row(
        modifier = Modifier.padding(vertical = 8.dp),
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
                .clip(CircleShape)
                .clickable { updateTodoItemValue(!todoItem.isComplete) },
            tint = if (todoItem.isComplete) MaterialTheme.colors.primary
            else Color.White,
        )

        AppTextField(
            value=todoItem.text,
            onValueChange = updateTodoItemText,
            imeAction = ImeAction.Done,
            onBackspaceClick = onBackspaceClick,
            onDone = onDone,
            label = label,
            fonsSize = 16.sp,
            textDecoration = if (todoItem.isComplete) TextDecoration.LineThrough
            else null
        )
    }
}