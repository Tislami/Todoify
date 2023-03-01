package com.tis.todoify.presentation.screens.home.components

import NoteModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tis.todoify.presentation.ui.card.FolderItemCard
import com.tis.todoify.utils.onClick

@Composable
fun FolderView(
    noteList: List<NoteModel>,
    onClick: onClick
) {
    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(32.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        items(4) {
            FolderItemCard(onClick = onClick)
        }
    }
}
