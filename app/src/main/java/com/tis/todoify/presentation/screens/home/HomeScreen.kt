package com.tis.todoify.presentation.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tis.todoify.presentation.screens.home.components.HomeTopAppBar
import com.tis.todoify.presentation.ui.card.Folder
import com.tis.todoify.presentation.ui.card.TodoifyGridItem
import com.tis.todoify.presentation.ui.card.TodoifyListItem
import com.tis.todoify.presentation.ui.theme.TodoifyTheme

@Composable
fun HomeScreen() {

    Scaffold(
        topBar = { HomeTopAppBar()},
        content = { innerPadding -> 
            HomeContent(modifier = Modifier.padding(innerPadding))
        },
        floatingActionButton = {
            Button(onClick = { /*TODO*/ }) {


                /*
                draggable(
            orientation = Orientation.Horizontal,
            state = rememberDraggableState { delta ->
                offsetX += delta
            }
        ),
                 */
            }
        }
    ) 
}

@Composable
fun HomeContent(modifier: Modifier) {
    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        items(4) {
            Folder()
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
        items(4) {
            TodoifyGridItem()
        }
    }
}


@Composable
fun ListView() {
    LazyColumn(
        contentPadding = PaddingValues(16.dp)
    ){
        items(5){
            TodoifyListItem()
            Spacer(modifier =  Modifier.padding(18.dp))
        }
    }
}
