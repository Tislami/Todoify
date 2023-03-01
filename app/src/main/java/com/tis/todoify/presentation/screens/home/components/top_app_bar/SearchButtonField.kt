package com.tis.todoify.presentation.screens.home.components.top_app_bar

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tis.todoify.utils.onClick
import com.tis.todoify.utils.onValueChange


@Composable
fun SearchButtonField(
    query: String,
    onValueChange: onValueChange,
    onClick: onClick,
) {
    var height by remember { mutableStateOf(0.dp) }

    IconButton(onClick = {
        onClick()
        height = if (height == 150.dp) 0.dp else 150.dp
    }) {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = "Search",
            tint = MaterialTheme.colors.onBackground
        )
    }

    SearchTextField(
        modifier = Modifier
            .animateContentSize(animationSpec = tween(1000))
            .width(height),
        query = query,
        onValueChange = onValueChange,
        onSearch = { height = 0.dp }
    )
}

@Composable
private fun SearchTextField(
    modifier: Modifier,
    query: String,
    onValueChange: (String) -> Unit,
    onSearch: () -> Unit,
) {
    val focusManager = LocalFocusManager.current

    BasicTextField(
        value = query, onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search,
            capitalization = KeyboardCapitalization.Sentences
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearch()
                focusManager.clearFocus()
            }
        ),
        textStyle = TextStyle(
            color = MaterialTheme.colors.onSurface,
            fontSize = 14.sp,
        ),
        modifier = modifier
            .height(40.dp)
            .background(
                color = MaterialTheme.colors.background.copy(alpha = .25f),
                shape = CircleShape
            )
            .border(
                width = 1.dp,
                color = MaterialTheme.colors.primary,
                shape = CircleShape
            ),
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier.padding(start = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                innerTextField()
            }
        },
        cursorBrush = SolidColor(MaterialTheme.colors.primary)
    )
}
