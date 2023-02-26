package com.tis.todoify.presentation.ui.card

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Folder() {
    Surface(
        color = MaterialTheme.colors.surface.copy(alpha = 0.25f),
        contentColor = MaterialTheme.colors.onSurface,
        shape = MaterialTheme.shapes.large,
        modifier = Modifier.size(150.dp)
            .clickable {  }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                imageVector = Icons.Default.ShoppingCart,
                contentDescription = null,
                modifier = Modifier.fillMaxSize().weight(1f)
            )
            Text(text = "Folder")
        }
    }
}