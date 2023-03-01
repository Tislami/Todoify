package com.tis.todoify.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tis.todoify.presentation.screens.add.AddScreen
import com.tis.todoify.presentation.screens.detail.DetailScreen
import com.tis.todoify.presentation.screens.home.HomeScreen

@Composable
fun AppNavigation() {
    val navHostController = rememberNavController()

    NavHost(
        navController = navHostController,
        startDestination = Screens.Home.route
    ) {
        composable(route = Screens.Home.route) {
            HomeScreen(
                navHostController = navHostController
            )
        }

        composable(route = Screens.Add.route) {
            AddScreen(navHostController = navHostController)
        }

        composable(route = Screens.Detail.route) {
            DetailScreen(navHostController = navHostController)
        }
    }
}


@Stable
sealed class Screens(val route: String) {
    object Home : Screens("home")
    object Add : Screens("add")
    object Detail : Screens("detail")
}


