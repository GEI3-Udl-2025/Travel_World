package com.example.travelworld

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.travelworld.ui.view.AboutScreen
import com.example.travelworld.ui.view.SettingsScreen
import com.example.travelworld.ui.view.TermsConditionsScreen

import com.example.travelworld.ui.view.TravelApp
import com.example.travelworld.ui.view.LoginScreen
import com.example.travelworld.ui.view.VersionScreen


@Composable
fun NavGraph(navController: NavHostController) {

/*
    val sharedRepository = remember { TaskRepository() }
    val taskViewModel = remember { TaskViewModel(sharedRepository) }
    val subTaskViewModel = remember { SubTaskViewModel(sharedRepository) }
*/

    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginScreen(navController) }
        composable("main") {
            TravelApp(
                navController,
/*                taskViewModel,
                subTaskViewModel*/
            )
        }

        composable("about") { AboutScreen(navController) }
        composable("terms") { TermsConditionsScreen(navController) }
        composable("version") { VersionScreen(navController) }
        composable("settings") { SettingsScreen(navController) }
    }
}
