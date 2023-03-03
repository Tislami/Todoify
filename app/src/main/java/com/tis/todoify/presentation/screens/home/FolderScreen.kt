package com.tis.todoify.presentation.screens.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tis.todoify.domain.model.FolderModel
import com.tis.todoify.domain.model.Note
import com.tis.todoify.domain.model.defaultFolderModel
import com.tis.todoify.presentation.screens.home.components.top_app_bar.FolderTopAppBar
import com.tis.todoify.presentation.ui.card.ListItemCard

@Composable
fun FolderScreen(
    folderModel: FolderModel = defaultFolderModel,
) {

    var viewStyleState by rememberSaveable { mutableStateOf(ViewStyle.Folder) }
    val query = remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            FolderTopAppBar(
                title = folderModel.name,
                query = query.value,
                onValueChange = { query.value = it },
                listView = { viewStyleState = ViewStyle.List },
                gridView = { viewStyleState = ViewStyle.Grid }
            )
        }

    ) { innerPadding ->
        FolderContent(
            modifier = Modifier.padding(innerPadding),
            noteList = folderModel.noteList
        )
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun FolderContent(
    modifier: Modifier,
    noteList : List<Note>
) {

    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ){
        items(noteList){note->
            ListItemCard(
                modifier= Modifier.animateItemPlacement(),
                note = note,
                onClick = {},
                onDelete = {},
                onEdit = { }
            )
        }
    }
}