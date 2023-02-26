package com.tis.todoify.presentation.screens.add.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AddFab() {
    IconButton(onClick = { /*TODO*/ }) {
        Surface(
            elevation = 8.dp, shape = CircleShape, color = MaterialTheme.colors.primary
        ) {
            Icon(
                imageVector = Icons.Default.Done,
                contentDescription = null,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}