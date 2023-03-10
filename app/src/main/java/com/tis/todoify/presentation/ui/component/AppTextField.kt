package com.tis.todoify.presentation.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.*
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.input.key.*
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.*
import com.tis.todoify.utils.onClick
import com.tis.todoify.utils.onValueChange

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AppTextField(
    value: String,
    onValueChange : onValueChange,
    modifier: Modifier = Modifier,
    label: String? = null,
    fonsSize: TextUnit = 18.sp,
    textDecoration: TextDecoration? = null,
    onBackspaceClick: (onClick)? = null,
    imeAction: ImeAction = ImeAction.Default,
    onDone: (onClick)? = null,
    isFocused: Boolean = false,
) {
    val focusRequester = FocusRequester()

    LaunchedEffect(key1 = Unit){
        if (isFocused) {
            focusRequester.requestFocus()
        }
    }

    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .focusRequester(focusRequester)
            .fillMaxWidth()
            .onKeyEvent { keyEvent ->
                if (value.isEmpty()) {
                    if (keyEvent.key.keyCode == Key.Backspace.keyCode) {
                        if (onBackspaceClick != null) {
                            onBackspaceClick()
                        }
                    }
                    return@onKeyEvent true
                }
                false
            },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = imeAction,
            capitalization = KeyboardCapitalization.Sentences
        ),
        keyboardActions= KeyboardActions(
            onDone = {
                if (onDone != null) {
                    onDone()
                }
            }
        ),
        textStyle = TextStyle(
            color = MaterialTheme.colors.onSurface,
            fontSize = fonsSize,
            textDecoration = textDecoration
        ),
        decorationBox = { inner ->
            if (value.isEmpty() && label != null) {
                Text(
                    text = label,
                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.5f),
                    fontSize = fonsSize,
                    fontWeight = FontWeight.SemiBold,
                )
            }
            inner()
        },
        cursorBrush = SolidColor(MaterialTheme.colors.primary)
    )
}
