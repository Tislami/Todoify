package com.tis.todoify

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.tis.todoify.presentation.navigation.AppNavigation
import com.tis.todoify.presentation.ui.theme.TodoifyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoifyTheme(darkTheme = true) {
                AppNavigation()
                //ColorPicker()
            }
        }
    }
}