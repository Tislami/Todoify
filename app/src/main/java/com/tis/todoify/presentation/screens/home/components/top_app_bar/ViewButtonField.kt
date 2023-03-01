package com.tis.todoify.presentation.screens.home.components.top_app_bar

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.EaseOutBack
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.tis.todoify.R
import com.tis.todoify.utils.onClick


@Composable
fun ViewButtonField(
    gridView: onClick? = null,
    listView: onClick? = null,
    folderView: onClick? = null,
) {
    var width by remember { mutableStateOf(100.dp) }

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
            .width(width)
            .border(
                width = 1.dp,
                color = MaterialTheme.colors.primary,
                shape = MaterialTheme.shapes.small
            ),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {

        if (listView != null) {
            ViewButton(
                icon = Icons.Default.List,
                action = listView
            )
        }

        if (gridView != null) {
            ViewButton(
                icon = painterResource(id = R.drawable.grid_view_24),
                action = gridView
            )
        }

        if (folderView != null) {
            ViewButton(
                icon = painterResource(id = R.drawable.folder_copy_24),
                action = folderView
            )
        }
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