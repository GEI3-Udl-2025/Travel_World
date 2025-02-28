package com.example.travelplanner

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.travelplanner.ui.screens.AddTripScreen
import com.example.travelplanner.ui.screens.Content1
import com.example.travelplanner.ui.screens.HomeScreenScaffold2
import com.example.travelplanner.ui.screens.ItinerariosScreen
import com.example.travelplanner.ui.screens.LoginScreen2
import com.example.travelplanner.ui.screens.ProfileScreen
import com.example.travelplanner.ui.screens.TripDetailScreen


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
        composable("addTrip") {
            AddTripScreen(navController)
        }



        // Ruta para los detalles del viaje (opcional)
        composable("tripDetail/{id}") { backStackEntry ->
            val tripId = backStackEntry.arguments?.getString("id")
            TripDetailScreen(tripId = tripId)
        }
    }
}
