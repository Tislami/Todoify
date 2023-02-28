package com.tis.todoify.presentation.ui.card

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.tis.todoify.R

@Composable
fun TodoifyFolderItem() {
    Surface(
        color = MaterialTheme.colors.surface.copy(alpha = 0.25f),
        contentColor = MaterialTheme.colors.onSurface,
        shape = MaterialTheme.shapes.large,
        modifier = Modifier
            .size(150.dp)
            .clickable { }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 4.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.folder_open_24),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            )
            Text(text = "Folder")
        }
    }
}