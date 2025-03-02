package com.example.travelplanner

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.travelplanner.ui.screens.AboutScreen
import com.example.travelplanner.ui.screens.AddTripScreen
import com.example.travelplanner.ui.screens.Content1
import com.example.travelplanner.ui.screens.HomeScreen
import com.example.travelplanner.ui.screens.HomeScreenScaffold2
import com.example.travelplanner.ui.screens.ItinerariosScreen
import com.example.travelplanner.ui.screens.LoginScreen2
import com.example.travelplanner.ui.screens.ProfileScreen
import com.example.travelplanner.ui.screens.SettingsScreen
import com.example.travelplanner.ui.screens.TermsConditionsScreen
import com.example.travelplanner.ui.screens.TripDetailScreen
import com.example.travelplanner.ui.screens.VersionScreen


@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginScreen2(navController) }
        composable("home") { HomeScreenScaffold2(navController) }
        composable("profile") { ProfileScreen(navController, null) }
        composable("profile/{id}",
            arguments = listOf(navArgument("id"){
                type = NavType.IntType
            })
        ) {
            var userId = it.arguments?.getInt("id") ?: -1
            ProfileScreen(navController, userId)
        }
        composable("profileMenu") { ProfileScreen(navController, null) }
        composable("addTrip") { AddTripScreen(navController) }


        // Ruta para los detalles del viaje (opcional)
        composable("tripDetail/{id}",
            arguments = listOf(navArgument("id") {
                type = NavType.IntType // <-- Usa IntType en lugar de String
            })
        ) { backStackEntry ->
            val tripId = backStackEntry.arguments?.getInt("id") ?: -1
            TripDetailScreen(tripId = tripId.toString()) // <-- Pasa el ID como String si es necesario
        }
        composable("about") { AboutScreen(navController) }
        composable("terms") { TermsConditionsScreen(navController) }
        composable("version") { VersionScreen(navController) }
        composable("settings") { SettingsScreen(navController) }
        composable("itinerarios") { ItinerariosScreen(navController) }
    }
}
