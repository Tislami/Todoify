package com.tis.todoify.presentation.ui.card

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tis.todoify.domain.model.Note
import com.tis.todoify.domain.model.TextFieldItem
import com.tis.todoify.presentation.ui.component.HorizontalSwipeAction
import com.tis.todoify.utils.onClick
import java.text.SimpleDateFormat
import java.util.*
import kotlin.random.Random

@Composable
fun ListItemCard(
    modifier: Modifier = Modifier,
    note: Note,
    onClick: onClick,
) {
     val color = Color(255, Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))
    val color1 = Color(255, Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))

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
                onClick = {}
            )
        },
        leadingContent = {
            SwipeContent(
                icon = Icons.Default.Edit,
                text = "Edit",
                onClick = {}
            )
        }
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(color,color1)
                    )
                )
                .clickable { onClick() }
                .heightIn(min = 50.dp, max = 500.dp)
                .padding(vertical = 4.dp, horizontal = 16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Head(note)
            Body(note)
        }
    }
}

@Composable
private fun Body(note: Note) {
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

@Composable
private fun Head(note: Note) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = note.title,
            color = MaterialTheme.colors.onSurface,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold
        )

        IconButton(
            modifier = Modifier.offset(x = 20.dp),
            onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Default.MoreVert, contentDescription = null)
        }
    }
}

@Composable
private fun SwipeContent(
    icon: ImageVector,
    text: String,
    onClick: onClick,
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(start = 8.dp)
            .clickable { onClick() },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(imageVector = icon, contentDescription = null)
        Text(text = text)
    }
}