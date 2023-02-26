package com.tis.todoify.presentation.ui.component

import android.inputmethodservice.Keyboard
import android.util.Log
import android.util.Size
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.onFocusedBoundsChanged
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.*
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.input.key.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontSynthesis
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.*
import androidx.core.text.buildSpannedString
import androidx.room.util.appendPlaceholders
import androidx.room.util.newStringBuilder
import com.tis.todoify.R
import com.tis.todoify.presentation.screens.add.components.TodoListItem

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AppTextField(
    modifier: Modifier = Modifier,
    label: String? = null,
    fonsSize: TextUnit = 18.sp,
    textDecoration: TextDecoration? = null,
    onBackspaceClick: (() -> Unit)? = null,
    imeAction: ImeAction = ImeAction.Default,
    onDone: (() -> Unit)? = null,
    isFocused: Boolean = true,
) {
    var value by remember { mutableStateOf("") }
    val focusRequester = FocusRequester()

    LaunchedEffect(key1 = Unit){
        if (isFocused) {
            focusRequester.requestFocus()
        }
    }

    BasicTextField(
        value = value,
        onValueChange = { value = it },
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
