package com.tis.todoify.presentation.ui.card

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.*
import com.tis.todoify.domain.model.Note
import com.tis.todoify.domain.model.TextFieldItem
import com.tis.todoify.presentation.ui.component.HorizontalSwipeAction
import com.tis.todoify.presentation.ui.component.SwipeContent
import com.tis.todoify.utils.onClick
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import kotlin.time.DurationUnit
import kotlin.time.toDuration


@Composable
fun NoteItemCard(
    modifier: Modifier = Modifier,
    note: Note,
    onClick: onClick,
    onDelete: onClick,
    onEdit: onClick,
) {

    val coroutineScope = rememberCoroutineScope()
    val animatedVisibility = remember { mutableStateOf(true) }
    val duration by remember { mutableStateOf(500) }

    AnimatedVisibility(
        visible = animatedVisibility.value,
        exit = slideOut(animationSpec = tween(duration)) { IntOffset(-1000, 0) }
    ) {
        HorizontalSwipeAction(
            modifier = Modifier.clip(MaterialTheme.shapes.medium),
            trailingContentBackgroundColor = Color.Red,
            leadingContentBackgroundColor = MaterialTheme.colors.primary,
            trailingContentSize = 60.dp,
            leadingContentSize = 50.dp,
            trailingContent = {
                SwipeContent(
                    icon = Icons.Default.Delete,
                    text = "Delete",
                    onClick = {
                        delete(coroutineScope, animatedVisibility, duration, onDelete)
                    }
                )
            },
            leadingContent = {
                SwipeContent(
                    icon = Icons.Default.Edit,
                    text = "Edit",
                    onClick = onEdit
                )
            }
        ) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .background(color = Color(note.color))
                    .clickable { onClick() }
                    .padding(vertical = 4.dp, horizontal = 16.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                Head(
                    note = note,
                    onDelete = { delete(coroutineScope, animatedVisibility, duration, onDelete) },
                    onEdit = onEdit,
                    onLock = {},
                    onShare = {}

                )
                Body(
                    modifier = modifier,
                    note = note)
            }
        }
    }
}

@Composable
private fun Body(
    modifier: Modifier,
    note: Note) {

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = (note.noteItemList[0] as TextFieldItem).text,
            color = MaterialTheme.colors.onSurface,
            fontSize = 14.sp,
            maxLines = 5,
            overflow = TextOverflow.Ellipsis,
            softWrap = true
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val format = SimpleDateFormat("dd/MM/yy hh:mm a", Locale.US)
            val date = format.parse(note.date)
            val day = SimpleDateFormat("dd").format(date)
            val month = SimpleDateFormat("MMMM", Locale.US).format(date)
            Text(
                text = "$day $month",
                color = MaterialTheme.colors.onSurface,
                fontSize = 10.sp,
                maxLines = 1,
            )
            Surface(
                shape = RoundedCornerShape(25),
                color = Color.Gray.copy(alpha = .75f)
            ) {
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = note.tag,
                    color = MaterialTheme.colors.onSurface,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                )
            }
        }
    }
}

@Composable
private fun Head(
    note: Note,
    onDelete: onClick,
    onEdit: onClick,
    onShare: onClick,
    onLock: onClick,
) {

    val showMore = remember { mutableStateOf(false) }
    val contentWidth = remember { mutableStateOf(Dp.Unspecified) }
    val density = LocalDensity.current

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = note.title,
            color = MaterialTheme.colors.onSurface,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            maxLines = 1,
            modifier = Modifier
                .onGloballyPositioned {
                    contentWidth.value = with(density) { it.size.width.toDp() - 80.dp }
                }
                .fillMaxWidth()
                .weight(1f),
            overflow = TextOverflow.Ellipsis
        )

        IconButton(
            modifier = Modifier.offset(x = 20.dp),
            onClick = { showMore.value = true }
        ) {
            Icon(imageVector = Icons.Default.MoreVert, contentDescription = null)
        }

        MoreContent(contentWidth, showMore, onEdit, onDelete, onShare, onLock)
    }
}

@Composable
private fun MoreContent(
    contentWidth: MutableState<Dp>,
    showMore: MutableState<Boolean>,
    onEdit: onClick,
    onDelete: onClick,
    onShare: onClick,
    onLock: onClick
) {
    DropdownMenu(
        modifier = Modifier.border(
            1.dp, MaterialTheme.colors.primary, MaterialTheme.shapes.medium
        ),
        offset = DpOffset(x = contentWidth.value, y = (-40).dp),
        expanded = showMore.value,
        onDismissRequest = { showMore.value = false }
    ) {
        DropdownMenuItem(onClick = {
            showMore.value = false
            onEdit()
        }) {
            Icon(imageVector = Icons.Default.Edit, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Edit")
        }
        DropdownMenuItem(onClick = {
            showMore.value = false
            onDelete()
        }) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Delete")
        }

        DropdownMenuItem(onClick = onShare) {
            Icon(imageVector = Icons.Default.Share, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Share")
        }

        DropdownMenuItem(onClick = onLock) {
            Icon(imageVector = Icons.Default.Lock, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Lock")
        }
    }
}


private fun delete(
    coroutineScope: CoroutineScope,
    animatedVisibility: MutableState<Boolean>,
    duration: Int,
    onDelete: onClick
) {
    coroutineScope.launch {
        animatedVisibility.value = false
        delay(duration.toDuration(DurationUnit.MILLISECONDS))
        onDelete()
    }
}