package com.tis.todoify.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
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

        composable(
            route = Screens.Add.route.plus("/{noteId}"),
            arguments = listOf(navArgument("noteId"){ defaultValue = -1 })
        ) { backStackEntry ->
            AddScreen(
                navHostController = navHostController,
                id = backStackEntry.arguments?.getInt("noteId")!!
            )
        }

        composable(
            route = Screens.Detail.route.plus("/{noteId}"),
            arguments = listOf(navArgument("noteId") { defaultValue = 0 })
        ) { backStackEntry ->
            DetailScreen(
                navHostController = navHostController,
                id = backStackEntry.arguments?.getInt("noteId")
            )
        }
    }
}


@Stable
sealed class Screens(val route: String) {
    object Home : Screens("home")
    object Add : Screens("add")
    object Detail : Screens("detail")
}


