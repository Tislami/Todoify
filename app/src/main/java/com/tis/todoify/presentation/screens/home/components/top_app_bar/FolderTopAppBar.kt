package com.tis.todoify.presentation.screens.home.components.top_app_bar

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.tis.todoify.utils.onClick
import com.tis.todoify.utils.onValueChange

@Composable
fun FolderTopAppBar(
    title: String,
    query: String,
    onValueChange: onValueChange,
    listView: onClick,
    gridView: onClick,
) {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.background,
       // modifier = Modifier.border(BorderStroke(1.dp, Color.Cyan)),
        elevation = 0.dp,
        title = {
            Text(
                text = title,
                color = MaterialTheme.colors.onBackground,
                maxLines = 1,
                softWrap = true,
                overflow = TextOverflow.Ellipsis
            )
        },
        actions = {

            ViewButtonField(gridView, listView)


            SearchButtonField(
                query = query,
                onValueChange= onValueChange,
                onClick = { }
            )


            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "More",
                    tint = MaterialTheme.colors.onBackground
                )
            }
        },
        navigationIcon = {

            IconButton(
                onClick = { /*TODO*/ },

            ) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
            }
        }
    )
}