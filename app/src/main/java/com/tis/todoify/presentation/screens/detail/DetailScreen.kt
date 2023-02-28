package com.tis.todoify.presentation.screens.detail

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tis.todoify.R
import com.tis.todoify.domain.model.DenemeNote
import com.tis.todoify.domain.model.defaultDenemeNote
import com.tis.todoify.presentation.screens.detail.components.DetailTopAppBar

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
) {

    val isTitleVisible = remember { mutableStateOf(true) }
    val colors = listOf(
        Color(0, 172, 193, 255),
        Color(3, 155, 229, 255),
    )

    Scaffold(
        topBar = {
            DetailTopAppBar(
                colors,
                title = defaultDenemeNote.title,
                isTitleVisible = isTitleVisible.value
            )
        },
        content = { innerPadding ->
            DetailContent(
                modifier = Modifier
                    .padding(innerPadding),
                colors = colors,
                isTitleVisible = isTitleVisible
            )
        }
    )
}

@Composable
fun DetailContent(
    modifier: Modifier,
    denemeNote: DenemeNote = defaultDenemeNote,
    isTitleVisible: MutableState<Boolean>,
    colors: List<Color>
) {

    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(
                Brush.horizontalGradient(
                    colors = colors,
                )
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Head(denemeNote, isTitleVisible = isTitleVisible)

        Surface(
            shape = RoundedCornerShape(topEnd = 64.dp),
            color = MaterialTheme.colors.background,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                modifier = Modifier.padding(16.dp),
                //letterSpacing = TextUnit(2f, TextUnitType.Sp),
                text = stringResource(id = R.string.lorem),
            )
        }
    }
}

@Composable
private fun Head(
    denemeNote: DenemeNote,
    isTitleVisible: MutableState<Boolean>,
) {

    var ti = remember {
        mutableStateOf(denemeNote.title)
    }
    var isEditMode by remember { mutableStateOf(false) }

    val textScrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(bottom = 32.dp, top = 16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        BasicTextField(
            value = ti.value,
            maxLines = 2,
            readOnly = true,
            onValueChange = { ti.value = it },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {
                isEditMode = false
            }),
            textStyle = TextStyle(
                color = MaterialTheme.colors.onSurface,
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold
            )
        )
    }

    Spacer(modifier = Modifier.height(4.dp))

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = "Tuesday",
            color = MaterialTheme.colors.onSurface,
            fontSize = 12.sp,
        )

        Canvas(modifier = Modifier.size(5.dp)) {
            drawCircle(color = Color.White)
        }

        Row {
            Text(
                text = "14 June 2022, ",
                color = MaterialTheme.colors.onSurface,
                fontSize = 12.sp,
            )

            Text(
                text = "09:45",
                color = MaterialTheme.colors.onSurface,
                fontSize = 12.sp,
            )
        }
    }
}
