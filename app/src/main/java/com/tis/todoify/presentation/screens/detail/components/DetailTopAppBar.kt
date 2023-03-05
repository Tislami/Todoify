package com.tis.todoify.presentation.screens.detail.components

import android.icu.text.CaseMap.Title
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.tis.todoify.utils.onClick


@Composable
fun DetailTopAppBar(
    color: Color,
    title: String,
    isTitleVisible: Boolean,
    navigationOnClick : onClick
) {
    TopAppBar(
        backgroundColor = color,
        elevation = 0.dp,
        title = {
            Text(
                text = if (isTitleVisible) "Notes" else title,
                color = MaterialTheme.colors.onSurface,
                maxLines = 1,
                softWrap = true,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            IconButton(onClick = navigationOnClick) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = MaterialTheme.colors.onSurface
                )
            }
        },
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = "Share",
                    tint = MaterialTheme.colors.onSurface
                )
            }
            TextButton(onClick = { /*TODO*/ }) {
                Text(
                    text = "Done",
                    color = MaterialTheme.colors.onSurface
                )
            }
        },
    )
}
