package com.tis.todoify.presentation.screens.add

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tis.todoify.presentation.screens.add.components.*
import com.tis.todoify.presentation.ui.component.AppTextField

@Composable
fun AddScreen(
    addViewModel: AddViewModel = hiltViewModel()
) {
    val focusManager = LocalFocusManager.current
    val contents = remember { mutableStateListOf<@Composable (() -> Unit)>() }

    Scaffold(
        topBar = {
            AddTopAppBar(
                addTodoItem = { addTodoItem(contents, focusManager) },
                addTable = { addTable(contents, focusManager) }
            )
        },
        floatingActionButton = {
            AddFab(
                onClick = {

                }
            )
        }
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

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(bottom = 8.dp)
        ) {
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
}


//Todo: item ilave ettikten sonra ortalardan bir itemi silende ve sonrasında bir sonrakını silende crush olur. Bunun sebebi bir sonrakı item'in indexi artıq liste içinde olmur. Listeden set çevir