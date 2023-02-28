package com.tis.todoify.presentation.screens.home

import android.graphics.DashPathEffect
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.tis.todoify.R
import com.tis.todoify.presentation.screens.home.components.HomeTopAppBar
import com.tis.todoify.presentation.ui.card.TodoifyFolderItem
import com.tis.todoify.presentation.ui.card.TodoifyGridItem
import com.tis.todoify.presentation.ui.card.TodoifyListItem

enum class ViewStyleState {
    List,
    Folder,
    Grid,
}

@Composable
fun HomeScreen() {

    var viewStyleState by rememberSaveable { mutableStateOf(ViewStyleState.List) }

    Scaffold(
        topBar = {
            HomeTopAppBar(
                listView = { viewStyleState = ViewStyleState.List },
                folderView = { viewStyleState = ViewStyleState.Folder },
                gridView = { viewStyleState = ViewStyleState.Grid }
            )
        },
        content = { innerPadding ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                color = MaterialTheme.colors.background,
            ) {
                when (viewStyleState) {
                    ViewStyleState.List -> {
                        ListView()
                    }
                    ViewStyleState.Folder -> {
                        FolderView()
                    }
                    ViewStyleState.Grid -> {
                        GridView()
                    }
                }
            }
        },
    )
}

@Composable
fun FolderView() {
    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(4) {
            TodoifyFolderItem()
        }

        item {

            val dashWidth = 10.dp
            val dashGap = 5.dp
            val paint = Paint().asFrameworkPaint().apply {
                isAntiAlias = true
                strokeWidth = 2f
                color = Color.Black.toArgb()
                pathEffect = DashPathEffect(floatArrayOf(dashWidth.value, dashGap.value), 0f)
            }


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
    }
}


@Composable
private fun GridView() {
    LazyVerticalGrid(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(16.dp),
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(7) { count ->
            TodoifyGridItem(
                description = "jsnfksnfknfdggdg".repeat(count)

            )
        }
    }
}


@Composable
fun ListView() {
    LazyColumn(
        contentPadding = PaddingValues(16.dp)
    ) {
        items(5) {
            TodoifyListItem()
            Spacer(modifier = Modifier.padding(vertical = 4.dp))
        }
    }
}
