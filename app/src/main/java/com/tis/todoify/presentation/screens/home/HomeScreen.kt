package com.tis.todoify.presentation.screens.home

import NoteModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.tis.todoify.domain.model.defaultNoteList
import com.tis.todoify.presentation.navigation.Screens
import com.tis.todoify.presentation.screens.home.components.FolderView
import com.tis.todoify.presentation.screens.home.components.GridView
import com.tis.todoify.presentation.screens.home.components.ListView
import com.tis.todoify.presentation.screens.home.components.top_app_bar.HomeTopAppBar
import com.tis.todoify.presentation.ui.card.TagItemCard
import com.tis.todoify.presentation.ui.component.AppFab

enum class ViewStyleState {
    List,
    Folder,
    Grid,
}

@Composable
fun HomeScreen(
    navHostController: NavHostController,
) {

    var viewStyleState by rememberSaveable { mutableStateOf(ViewStyleState.List) }
    val query = remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            HomeTopAppBar(
                query = query.value,
                onValueChange = { query.value = it },
                listView = { viewStyleState = ViewStyleState.List },
                folderView = { viewStyleState = ViewStyleState.Folder },
                gridView = { viewStyleState = ViewStyleState.Grid }
            )
        },
        content = { innerPadding ->
            HomeContent(
                modifier = Modifier.padding(innerPadding),
                viewStyleState = viewStyleState
            )
        },
        floatingActionButton = {
            AppFab(icon = Icons.Default.Add) {
                navHostController.navigate(Screens.Add.route)
            }
        }
    )
}

@Composable
private fun HomeContent(
    modifier: Modifier,
    viewStyleState: ViewStyleState,
    noteList: List<NoteModel> = defaultNoteList
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        LazyRow(
            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)){
            items(15){
                TagItemCard()
            }
        }
        when (viewStyleState) {
            ViewStyleState.List -> {
                ListView(noteList = noteList)
            }
            ViewStyleState.Folder -> {
                FolderView(
                    noteList = noteList,
                    onClick = {

                    }
                )
            }
            ViewStyleState.Grid -> {
                GridView(noteList = noteList)
            }
        }
    }
}





