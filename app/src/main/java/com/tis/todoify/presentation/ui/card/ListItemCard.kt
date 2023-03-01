package com.tis.todoify.presentation.ui.card

import NoteModel
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tis.todoify.R
import kotlin.random.Random

@Composable
fun ListItemCard(
    note: NoteModel,
) {


    val color = Color (255, Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))
    val color1 = Color (255, Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))


    Column(
        modifier =
        Modifier
            .clip(MaterialTheme.shapes.medium)
            .fillMaxWidth()
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        color1,
                        color
                    )
                ),
            )

            .heightIn(min = 50.dp, max = 500.dp)
            .clickable { }
            .padding(vertical = 4.dp, horizontal = 16.dp)

        ,
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
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


        Text(
            text = note.description,
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
            Text(
                text = "19 June",
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