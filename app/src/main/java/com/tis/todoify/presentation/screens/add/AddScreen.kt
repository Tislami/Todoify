package com.tis.todoify.presentation.screens.add

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tis.todoify.presentation.screens.add.components.AddFab
import com.tis.todoify.presentation.screens.add.components.AddTopAppBar
import com.tis.todoify.presentation.screens.add.components.TodoListItem
import com.tis.todoify.presentation.ui.component.AppTextField
import com.tis.todoify.utils.increase

@Composable
fun AddScreen(
    addViewModel: AddViewModel = hiltViewModel()
) {
    val focusManager = LocalFocusManager.current

    val contents = remember { mutableStateListOf<@Composable (() -> Unit)>() }

    val contentState =
        remember { mutableStateMapOf<String, MutableList<@Composable (() -> Unit)>>() }


    Scaffold(
        topBar = {
            AddTopAppBar(
                addTodoItem = {
                    val index = contents.size
                    contents.add(index = index) {
                        TodoListItem(
                            onDone = {
                                val index1 = contents.size
                                contents.add(index = index1) {
                                    AppTextField(
                                        onBackspaceClick = {
                                            focusManager.moveFocus(FocusDirection.Up)
                                            contents.removeAt(index1)
                                        },
                                    )
                                }
                            },
                            onBackspaceClick = {
                                focusManager.moveFocus(FocusDirection.Previous)
                                contents.removeAt(index)
                            },
                        )
                    }
                    contentState.set(key = "TodoListItem", value = contents)
                },
                addTextField = {

                },
            )
        },
        floatingActionButton = { AddFab() }
    ) { innerPadding ->
        AddContent(
            modifier = Modifier.padding(innerPadding),
            contents = contents
        )
    }
}


@Composable
fun AddContent(
    modifier: Modifier,
    contents: List<@Composable (() -> Unit)>? = null
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {

        AppTextField(
            modifier = Modifier.padding(bottom = 8.dp),
            label = "Title",
            fonsSize = 24.sp,
            isFocused = false
        )

        Divider(color = MaterialTheme.colors.primary)

        AppTextField(
            modifier = Modifier.padding(vertical = 8.dp),
            label = "Description",
            fonsSize = 18.sp,
            isFocused = false
        )

        if (contents != null) {
            for (content in contents) {
                content()

            }
        }
    }
}
