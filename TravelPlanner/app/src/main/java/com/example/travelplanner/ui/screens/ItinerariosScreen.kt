package com.example.travelplanner.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.travelplanner.Trip

@Composable
fun ItinerariosScreen(navController: NavController) {
    // Lista de viajes guardados (puedes reemplazar esto con un ViewModel o almacenamiento real)
    val trips = remember {
        listOf(
            Trip(id = 1, destination = "Paris", startDate = java.util.Date(), endDate = java.util.Date(), description = "Un hermoso viaje a Paris", price = 1200.0),
            Trip(id = 2, destination = "Tokyo", startDate = java.util.Date(), endDate = java.util.Date(), description = "Explorando la cultura japonesa", price = 1500.0),
            Trip(id = 3, destination = "New York", startDate = java.util.Date(), endDate = java.util.Date(), description = "Visitando la gran manzana", price = 1000.0)
        )
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Itinerarios", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(16.dp))

        // Mostrar lista de viajes
        LazyColumn {
            items(trips.size) { index ->
                val trip = trips[index]
                TripItem(trip = trip, onClick = {
                    // Navegar a una pantalla de detalle si es necesario
                    navController.navigate("tripDetail/${trip.id}")
                })
            }
        }
    }
}

@Composable
fun TripItem(trip: Trip, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() },
        elevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = trip.destination, style = MaterialTheme.typography.titleMedium)
            Text(text = "Fecha de inicio: ${trip.startDate}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Precio: \$${trip.price}", style = MaterialTheme.typography.bodySmall)
        }
    }
}
@Composable
fun Card(
    modifier: Modifier = Modifier,
    elevation: Dp = 4.dp,
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier,
        elevation = elevation
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            content()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ItinerariosScreenPreview() {
    ItinerariosScreen(navController = rememberNavController())
}
