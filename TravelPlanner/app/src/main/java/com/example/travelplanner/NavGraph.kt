package com.example.travelplanner

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.travelplanner.view.AboutScreen
import com.example.travelplanner.view.ProfileScreen
import com.example.travelplanner.view.SettingsScreen
import com.example.travelplanner.view.TermsConditionsScreen

import com.example.travelplanner.ui.screens.TravelApp
import com.example.travelplanner.view.LoginScreen
import com.example.travelplanner.view.VersionScreen


@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginScreen(navController) }
        composable("main") { TravelApp(navController) }
        composable("about") { AboutScreen(navController) }
        composable("terms") { TermsConditionsScreen(navController) }
        composable("version") { VersionScreen(navController) }
        composable("settings") { SettingsScreen(navController) }
    }
}
