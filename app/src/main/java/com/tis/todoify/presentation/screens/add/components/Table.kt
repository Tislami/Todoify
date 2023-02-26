package com.tis.todoify.presentation.screens.add.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TableItem(
    columnCount: Int = 4,
    rowCount: Int = 4,
) {

    var columnCountState by remember { mutableStateOf(columnCount) }
    var rowCountState by remember { mutableStateOf(rowCount) }

    LazyColumn(
        modifier = Modifier
            .horizontalScroll(rememberScrollState())
            .padding(top = 8.dp)
            .heightIn(max = 500.dp)
    ) {
        items(columnCountState) { column ->
            Row(verticalAlignment = Alignment.Bottom) {
                for (i in 0 until rowCountState) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        if (column == 0 && i == rowCountState - 1) {
                            IconButton(onClick = { rowCountState -= 1 }) {
                                Icon(imageVector = Icons.Default.Close, contentDescription = null)
                            }
                        }
                        TableCell(label = "$column  $i")
                    }
                }

                if (column == columnCountState - 1 && rowCountState>0) {
                    IconButton(onClick = { columnCountState -= 1 }) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = null)
                    }
                }
            }
        }
    }
}

@Composable
fun TableCell(
    backgroundColor: Color = Color.Unspecified,
    label: String? = null
) {

    var value by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    BasicTextField(
        value = value + label,
        onValueChange = { value = it },
        maxLines = 1,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next,
            capitalization = KeyboardCapitalization.Sentences
        ),
        keyboardActions = KeyboardActions(
            onNext = {
                focusManager.moveFocus(FocusDirection.Next)
            }
        ),
        textStyle = TextStyle(
            color = MaterialTheme.colors.onSurface,
            fontSize = 16.sp,
        ),
        cursorBrush = SolidColor(MaterialTheme.colors.primary),
        modifier = Modifier
            .border(1.dp, Color.Gray, RoundedCornerShape(20))
            .background(backgroundColor, shape = RoundedCornerShape(20))
            .padding(8.dp)
            .height(30.dp)
            .width(100.dp)
    )
}