package com.tis.todoify.presentation.screens.add.components

import android.util.Log
import androidx.compose.animation.core.*
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tis.todoify.R
import com.tis.todoify.domain.model.BackgroundState
import com.tis.todoify.utils.extensions.BackgroundStyle
import com.tis.todoify.utils.extensions.backgroundState
import com.tis.todoify.utils.onClick


@Composable
fun AddScreenPreview(
    modifier: Modifier,
    backgroundState: BackgroundState,
    onBackgroundStateChange: (BackgroundState) -> Unit,
    onSave: onClick,
) {
    Column(
        modifier = modifier
            .clip(MaterialTheme.shapes.medium)
            .backgroundState(backgroundState)
            .border(0.5.dp, MaterialTheme.colors.primary, MaterialTheme.shapes.medium)
            .height(200.dp)
            .padding(horizontal = 4.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Title",
                modifier = Modifier.padding(bottom = 8.dp),
                fontSize = 20.sp,
                color = MaterialTheme.colors.onSurface
            )

            MoreContent(
                onClick = {
                    val newBackgroundState = backgroundState.copy(backgroundStyle = it.name)
                    onBackgroundStateChange(newBackgroundState)
                }
            )
        }

        Divider(color = MaterialTheme.colors.primary)

        Text(
            text = backgroundState.backgroundStyle,
            modifier = Modifier.padding(bottom = 8.dp),
            fontSize = 20.sp,
            color = MaterialTheme.colors.onSurface
        )

        PreviewTodoItem(false)
        PreviewTodoItem(true)
        SaveButton(onSave)

    }
}

@Composable
private fun PreviewTodoItem(isComplete: Boolean) {
    Row(
        modifier = Modifier.padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = if (isComplete) painterResource(id = R.drawable.check_circle_24)
            else painterResource(id = R.drawable.outline_circle_24),
            contentDescription = null,
            modifier = Modifier
                .padding(end = 8.dp)
                .size(14.dp)
                .clip(CircleShape),
            tint = if (isComplete) MaterialTheme.colors.primary
            else Color.White
        )
        Text(
            text = "Todo item",
            fontSize = 14.sp,
            color = MaterialTheme.colors.onSurface,
            textDecoration = if (isComplete) TextDecoration.LineThrough
            else null,
        )
    }
}


@Composable
private fun MoreContent(onClick: (BackgroundStyle) -> Unit) {
    val showMore = remember { mutableStateOf(false) }

    val scale = remember { Animatable(1.5f) }
    LaunchedEffect(Unit) {
        scale.animateTo(
            targetValue = 1f,
            animationSpec = repeatable(
                iterations = 7,
                animation = tween(750),
                repeatMode = RepeatMode.Reverse
            )
        )
    }

    DropdownMenu(
        expanded = showMore.value,
        modifier = Modifier
            .background(MaterialTheme.colors.background)
            .border(1.dp, MaterialTheme.colors.primary, MaterialTheme.shapes.medium),
        onDismissRequest = { showMore.value = false }
    ) {

        BackgroundStyle.values().forEach {
            DropdownMenuItem(onClick = {
                showMore.value = false
                onClick(it)
            }) {
                Text(text = it.name)
            }
        }
    }

    IconButton(
        modifier = Modifier
            .offset(x = (10).dp)
            .scale(scale.value),
        onClick = { showMore.value = true }
    ) {
        Icon(
            imageVector = Icons.Default.MoreVert,
            contentDescription = null,
            tint = Color.White
        )
    }
}


@Composable
private fun SaveButton(onSave: onClick) {
    val scale = remember { Animatable(1.25f) }

    LaunchedEffect(Unit) {
        scale.animateTo(
            targetValue = 1f,
            animationSpec = repeatable(
                iterations = 7,
                animation = tween(750),
                repeatMode = RepeatMode.Reverse
            )
        )
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, end = 4.dp),
        contentAlignment = Alignment.CenterEnd
    ) {
        IconButton(
            modifier = Modifier
                .scale(scale.value)
                .background(
                    color = MaterialTheme.colors.primary,
                    shape = CircleShape
                )
                .size(24.dp),
            onClick = onSave,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.save_24),
                contentDescription = null,
                modifier = Modifier.size(18.dp),
                tint = Color.White
            )
        }
    }
}