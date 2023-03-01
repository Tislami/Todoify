package com.tis.todoify.presentation.ui.card

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TagItemCard(
    tag:String = "Todo",
) {
    Surface(
        shape = MaterialTheme.shapes.small,
        color = Color.Gray.copy(alpha = .75f),
        modifier = Modifier
            .clip(MaterialTheme.shapes.small)
            .clickable {  },
    ) {
        Text(
            modifier = Modifier.padding(4.dp),
            text = tag,
            color = MaterialTheme.colors.onSurface,
            letterSpacing = 2.sp,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
        )
    }
}