package com.tis.todoify.presentation.ui.card

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tis.todoify.R

@Composable
fun TodoifyListItem() {
    Column(
        modifier =
        Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color(94, 53, 177, 255),
                        Color(57, 73, 171, 255),
                    )
                ),
                shape = MaterialTheme.shapes.large,
            )
            .clickable {  }
            .heightIn(min = 100.dp, max = 200.dp)
            .padding(vertical = 8.dp, horizontal = 16.dp)

        ,
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Title",
            color = MaterialTheme.colors.onSurface,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold
        )

        Text(
            text = stringResource(id = R.string.lorem),
            color = MaterialTheme.colors.onSurface,
            fontSize = 14.sp,
            maxLines = 2,
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
                    text = "Todo",
                    color = MaterialTheme.colors.onSurface,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                )
            }
        }
    }
}