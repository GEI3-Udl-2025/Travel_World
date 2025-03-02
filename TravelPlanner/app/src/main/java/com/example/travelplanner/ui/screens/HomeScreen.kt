// HomeScreen.kt
package com.example.travelplanner.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Home Screen")
        Spacer(modifier = Modifier.height(20.dp))

        // Botón para navegar a Profile
        Button(onClick = {
            val userId = 12345
            navController.navigate("profile/$userId")
        }) {
            Text(text = "Go to Profile")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Nuevo botón para navegar a AddTripScreen
        Button(onClick = {
            navController.navigate("addTrip") // Usa la misma ruta definida en tu NavGraph
        }) {
            Text(text = "Add New Trip")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Nuevo botón para navegar a AddTripScreen
        Button(onClick = {
            navController.navigate("itinerarios") // Usa la misma ruta definida en tu NavGraph
        }) {
            Text(text = "Ver Itinerarios")
        }
    }
}
