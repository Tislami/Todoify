package com.tis.todoify.presentation.screens.home.components


import androidx.compose.animation.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tis.todoify.domain.model.Note
import com.tis.todoify.presentation.ui.card.NoteItemCard

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListView(
    noteList: List<Note>,
    onItemClick: (Int) -> Unit,
    onDelete: (Note) -> Unit,
    onEdit: (Int) -> Unit,
   // moreOnClick: () -> Unit
) {

    val lazyListState = rememberLazyListState()
    val grouped: Map<String, List<Note>> = noteList.groupBy { it.tag }

    LazyColumn(
        state = lazyListState,
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {

        grouped.forEach { (initial, noteList) ->
            stickyHeader {
                Header(value = initial)
            }
            items(
                items = noteList,
                key = { it.id!! }
            ) { note ->
                NoteItemCard(
                    modifier = Modifier.heightIn(min = 50.dp, max = 500.dp),
                    note = note,
                    onClick = { onItemClick(note.id!!) },
                    onDelete = { onDelete(note) },
                    onEdit = { onEdit(note.id!!) },
                    //moreOnClick = { moreOnClick() }
                )
            }
        }
    }
}


@Composable
fun Header(value: String) {
    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = value,
            textAlign = TextAlign.End,
            color = MaterialTheme.colors.onSurface,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(end = 16.dp)
        )
    }
}