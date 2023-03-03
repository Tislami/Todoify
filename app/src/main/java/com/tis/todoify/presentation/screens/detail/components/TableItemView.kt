package com.tis.todoify.presentation.screens.detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tis.todoify.domain.model.TableItem


@Composable
fun TableItemView(
    tableItem: TableItem,
) {
    LazyColumn(
        modifier = Modifier
            .horizontalScroll(rememberScrollState())
            .padding(top = 8.dp)
            .heightIn(max = 500.dp)
    ) {
        items(tableItem.columnCount) { column ->
            Row(verticalAlignment = Alignment.Bottom) {
                for (i in 0 until tableItem.rowCount) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        TableCell(
                            value = tableItem.tableValues[tableItem.rowCount * column + i],
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun TableCell(
    value: String,
    backgroundColor: Color = Color.Unspecified,
) {
    Text(
        text = value,
        maxLines = 1,
        color = MaterialTheme.colors.onSurface,
        fontSize = 16.sp,
        modifier = Modifier
            .border(1.dp, Color.Gray, RoundedCornerShape(20))
            .background(backgroundColor, shape = RoundedCornerShape(20))
            .padding(8.dp)
            .height(30.dp)
            .width(100.dp)
    )
}