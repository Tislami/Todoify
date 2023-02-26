package com.tis.todoify.presentation.screens.add.components

import androidx.compose.foundation.background
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun AddTopAppBar(
    addTodoItem: () -> Unit,
    addTable: () -> Unit,
) {
    TopAppBar(
        modifier = Modifier,
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        title = {},
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = MaterialTheme.colors.onSurface
                )
            }
        },
        actions = {
            IconButton(onClick = addTodoItem) {
                Icon(
                    painter = painterResource(id = com.tis.todoify.R.drawable.add_task_24),
                    contentDescription = "Add Task",
                    tint = MaterialTheme.colors.onSurface
                )
            }

            IconButton(onClick = addTable) {
                Icon(
                    painter = painterResource(id = com.tis.todoify.R.drawable.table_rows_24),
                    contentDescription = "Add Table",
                    tint = MaterialTheme.colors.onSurface
                )
            }

            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "More",
                    tint = MaterialTheme.colors.onSurface
                )
            }
        },
    )
}
