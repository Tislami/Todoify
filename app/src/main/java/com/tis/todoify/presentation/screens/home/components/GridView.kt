package com.tis.todoify.presentation.screens.home.components

import androidx.compose.animation.core.EaseOutBack
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import com.tis.todoify.domain.model.Note
import com.tis.todoify.presentation.ui.card.GridItemCard
import com.tis.todoify.utils.onClick

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GridView(
    noteList: List<Note>,
    onItemClick: (Int)-> Unit,
    onDelete: (Note)-> Unit,
    onEdit: (Int)-> Unit,
) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(noteList,key = { it.id!! }) { note ->
            GridItemCard(
                modifier = Modifier.animateItemPlacement(
                    tween(1000, easing = EaseOutBack)
                ),
                note = note,
                onClick = { onItemClick(note.id!!) },
                onDelete = { onDelete(note) },
                onEdit = { onEdit(note.id!!) }
            )
        }
    }
}