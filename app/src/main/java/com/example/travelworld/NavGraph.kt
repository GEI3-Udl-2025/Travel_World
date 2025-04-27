package com.example.travelworld


import SubTripApp
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.travelworld.ui.view.settings_option.AboutScreen
import com.example.travelworld.ui.view.settings_option.SettingsScreen
import com.example.travelworld.ui.view.settings_option.TermsConditionsScreen

import com.example.travelworld.ui.view.TravelApp
import com.example.travelworld.ui.view.auth_page.LoginScreen
import com.example.travelworld.ui.view.auth_page.SignUpScreen
import com.example.travelworld.ui.view.settings_option.ProfileScreen
import com.example.travelworld.ui.view.settings_option.VersionScreen
import com.example.travelworld.ui.viewmodel.AuthViewModel


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavGraph(navController: NavHostController, authViewModel: AuthViewModel) {


    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginScreen(navController, authViewModel) }
        composable("signup"){ SignUpScreen(navController,authViewModel) }
        composable("main") { TravelApp(navController) }
        composable(
            route = "subtrips/{tripId}",
            arguments = listOf(
                navArgument("tripId") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val tripId = backStackEntry.arguments?.getString("tripId")?.toIntOrNull() ?: 0
            SubTripApp(navController, tripId)
        }

        composable("about") { AboutScreen(navController) }
        composable("profile") { ProfileScreen(navController,authViewModel) }
        composable("terms") { TermsConditionsScreen(navController) }
        composable("version") { VersionScreen(navController) }
        composable("settings") { SettingsScreen(navController) }
    }
}
