package com.tis.todoify.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.tis.todoify.domain.model.Note
import com.tis.todoify.presentation.navigation.Screens
import com.tis.todoify.presentation.screens.home.components.FolderView
import com.tis.todoify.presentation.screens.home.components.GridView
import com.tis.todoify.presentation.screens.home.components.ListView
import com.tis.todoify.presentation.screens.home.components.top_app_bar.HomeTopAppBar
import com.tis.todoify.presentation.ui.card.TagItemCard
import com.tis.todoify.presentation.ui.component.AppFab

@Composable
fun HomeScreen(
    navHostController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel(),
) {

    val homeState = viewModel.homeState.value

    Scaffold(
        topBar = {
            HomeTopAppBar(
                query = homeState.query,
                onValueChange = viewModel::query,
                listView = { viewModel.changeViewStyle(ViewStyle.List) },
                folderView = { viewModel.changeViewStyle(ViewStyle.Folder) },
                gridView = { viewModel.changeViewStyle(ViewStyle.Grid) },
            )
        },
        content = { innerPadding ->
            HomeContent(
                modifier = Modifier.padding(innerPadding),
                homeState = homeState,
                onItemClick = { navHostController.navigate(Screens.Detail.route + "/${it}") },
                onItemDelete = viewModel::delete,
                onItemEdit = { navHostController.navigate(Screens.Add.route + "/${it}") }
            )
        },
        floatingActionButton = {
            AppFab(icon = Icons.Default.Add) {
                navHostController.navigate(Screens.Add.route+ "/-1")
            }
        }
    )
}

@Composable
private fun HomeContent(
    modifier: Modifier,
    homeState: HomeState,
    onItemClick: (Int) -> Unit,
    onItemEdit: (Int) -> Unit,
    onItemDelete: (Note) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        LazyRow(
            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(15) {
                TagItemCard()
            }
        }
        when (homeState.viewStyle) {
            ViewStyle.List -> {
                ListView(
                    noteList = homeState.noteList,
                    onItemClick = onItemClick,
                    onDelete = onItemDelete,
                    onEdit = onItemEdit
                )
            }
            ViewStyle.Folder -> {
                FolderView(
                    noteList = homeState.noteList,
                    onClick = {

                    },
                )
            }
            ViewStyle.Grid -> {
                GridView(
                    noteList = homeState.noteList,
                    onItemClick = onItemClick,
                    onDelete = onItemDelete,
                    onEdit = onItemEdit
                )
            }
        }
    }
}





