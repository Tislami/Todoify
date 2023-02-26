package com.tis.todoify

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.LinearGradient
import androidx.compose.ui.tooling.preview.Preview
import com.tis.todoify.presentation.screens.add.AddScreen
import com.tis.todoify.presentation.screens.detail.DetailScreen
import com.tis.todoify.presentation.screens.home.HomeScreen
import com.tis.todoify.presentation.ui.theme.TodoifyTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoifyTheme(darkTheme = true) {
                AddScreen()
            }
        }
    }
}