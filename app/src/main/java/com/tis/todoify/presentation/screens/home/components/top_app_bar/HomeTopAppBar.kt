package com.tis.todoify.presentation.screens.home.components.top_app_bar


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tis.todoify.presentation.ui.card.TagItemCard
import com.tis.todoify.utils.onClick
import com.tis.todoify.utils.onValueChange

@Composable
fun HomeTopAppBar(
    query: String,
    onValueChange: onValueChange,
    listView: onClick,
    folderView: onClick,
    gridView: onClick,
) {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.background,
        elevation = 1.dp,
        title = {
            Text(
                text = "Notes",
                color = MaterialTheme.colors.onBackground,
                maxLines = 1,
                softWrap = true,
                overflow = TextOverflow.Ellipsis
            )
        },
        actions = {

            ViewButtonField(gridView, listView, folderView)


            SearchButtonField(
                query = query,
                onValueChange = onValueChange,
                onClick = { }
            )


            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "More",
                    tint = MaterialTheme.colors.onBackground
                )
            }
        },
    )
}

