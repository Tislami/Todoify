package com.tis.todoify.presentation.screens.home.components

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import com.tis.todoify.R
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import kotlin.math.min


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun HomeTopAppBar(
    listView: () -> Unit,
    folderView: () -> Unit,
    gridView: () -> Unit,
) {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.background,
        elevation = 0.dp,
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


            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    tint = MaterialTheme.colors.onBackground
                )
            }

            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "More",
                    tint = MaterialTheme.colors.onBackground
                )
            }
        },
    )
}

@Composable
private fun ViewButtonField(
    gridView: () -> Unit,
    listView: () -> Unit,
    folderView: () -> Unit
) {
    var width by remember { mutableStateOf(0.dp) }

    IconButton(
        modifier = Modifier,
        onClick = { width = if (width == 100.dp) 0.dp else 100.dp },
    ) {
        Icon(
            painterResource(id = R.drawable.view_quilt_24),
            contentDescription = "View",
            tint = MaterialTheme.colors.onBackground
        )
    }

    Row(
        modifier = Modifier
            .animateContentSize(animationSpec = tween(1000, easing = EaseOutBack))
            .height(40.dp)
            .padding(end = 4.dp)
            .width(width)
            .border(
                width = 1.dp,
                color = MaterialTheme.colors.primary,
                shape = MaterialTheme.shapes.small
            ),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ViewButton(
            icon = Icons.Default.List,
            action = listView
        )

        ViewButton(
            icon = painterResource(id = R.drawable.grid_view_24),
            action = gridView
        )

        ViewButton(
            icon = painterResource(id = R.drawable.folder_copy_24),
            action = folderView
        )
    }
}

@Composable
private fun ViewButton(
    icon: Painter,
    action: () -> Unit
) {
    IconButton(
        onClick = action,
        modifier = Modifier.size(25.dp)
    ) {
        Icon(
            painter = icon,
            contentDescription = null,
            tint = MaterialTheme.colors.onBackground,
        )
    }
}

@Composable
private fun ViewButton(
    icon: ImageVector,
    action: () -> Unit
) {
    IconButton(
        onClick = action,
        modifier = Modifier.size(25.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colors.onBackground,
        )
    }
}


