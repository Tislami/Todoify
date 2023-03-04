package com.tis.todoify.presentation.screens.detail

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.tis.todoify.R
import com.tis.todoify.domain.model.*
import com.tis.todoify.presentation.navigation.Screens
import com.tis.todoify.presentation.screens.detail.components.DetailAlertDialog
import com.tis.todoify.presentation.screens.detail.components.DetailTopAppBar
import com.tis.todoify.presentation.screens.detail.components.TableItemView
import com.tis.todoify.presentation.screens.detail.components.TodoItemView
import com.tis.todoify.utils.onClick

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    detailViewModel: DetailViewModel = hiltViewModel(),
    id: Int?,
) {

    val detailState = detailViewModel.detailState.value
    var showAlertDialog by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = Unit) {
        if (id != null) detailViewModel.getNote(id)
        else navHostController.popBackStack()
    }

    val isTitleVisible = remember { mutableStateOf(true) }
    val colors = listOf(
        Color(0, 172, 193, 255),
        Color(3, 155, 229, 255),
    )


    if (showAlertDialog)
        DetailAlertDialog(
            onDismissRequest = {
                navHostController.popBackStack()
                showAlertDialog = false
            },
            submitOnclick = {
                detailViewModel.update()
                navHostController.popBackStack()
            },
            cancelOnClick = {
                navHostController.popBackStack()
            }
        )

    Scaffold(
        topBar = {
            DetailTopAppBar(
                navigationOnClick = {
                    if (!detailState.isUpdate) navHostController.navigate(Screens.Home.route)
                    else showAlertDialog = true
                },
                colors = colors,
                title = "defaultDenemeNote.title",
                isTitleVisible = isTitleVisible.value
            )
        },
        content = { innerPadding ->
            DetailContent(
                modifier = Modifier.padding(innerPadding),
                detailState = detailState,
                todoItemOnclick = detailViewModel::updateNoteItem,
                colors = colors,
                isTitleVisible = isTitleVisible
            )
        }
    )
}


@Composable
fun DetailContent(
    modifier: Modifier,
    detailState: DetailState,
    todoItemOnclick: (NoteItem, NoteItem) -> Unit,
    isTitleVisible: MutableState<Boolean>,
    colors: List<Color>
) {

    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.horizontalGradient(
                    colors = colors,
                )
            )
            .padding(horizontal = 16.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Head(
            note = detailState.note,
            isTitleVisible = isTitleVisible
        )

        Divider()

        Column(
            // shape = RoundedCornerShape(topEnd = 64.dp),
            //color = MaterialTheme.colors.background,
            modifier = Modifier
                .fillMaxSize()
        ) {
            detailState.note.noteItemList.forEach { noteItem ->
                when (noteItem.state) {
                    NoteItemState.TodoItem -> {
                        TodoItemView(
                            todoItem = noteItem as TodoItem,
                            onClick = {
                                todoItemOnclick(
                                    noteItem,
                                    noteItem.copy(isComplete = it)
                                )
                            }
                        )
                    }
                    NoteItemState.TextField -> {
                        Text(
                            text = (noteItem as TextFieldItem).text,
                            color = MaterialTheme.colors.onSurface,
                            fontSize = 18.sp,
                        )
                    }
                    NoteItemState.Table -> {
                        TableItemView(tableItem = noteItem as TableItem)
                    }
                }
            }
        }
    }
}

@Composable
private fun Head(
    note: Note,
    isTitleVisible: MutableState<Boolean>,
) {

    var isEditMode by remember { mutableStateOf(false) }

    val textScrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        BasicTextField(
            value = note.title,
            maxLines = 2,
            readOnly = true,
            onValueChange = { },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { isEditMode = false }),
            textStyle = TextStyle(
                color = MaterialTheme.colors.onSurface,
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold
            )
        )
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = "Tuesday",
            color = MaterialTheme.colors.onSurface,
            fontSize = 12.sp,
        )

        Canvas(
            modifier = Modifier
                .padding(horizontal = 4.dp)
                .size(5.dp)
        ) {
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
