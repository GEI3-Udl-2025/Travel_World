package com.example.travelplanner.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.travelplanner.Trip
import java.util.Date

@Composable
fun ItinerariosScreen(navController: NavController) {
    var selectedTrip by remember { mutableStateOf<Trip?>(null) }
    // Lista de viajes guardados (puedes reemplazar esto con un ViewModel o almacenamiento real)
    val trips = remember {
        listOf(
            Trip(id = 1, destination = "Paris", startDate = Date(), endDate = Date(), price = 1200.0),
            Trip(id = 2, destination = "Tokyo", startDate = Date(), endDate = Date(),  price = 1500.0),
            Trip(id = 3, destination = "New York", startDate = Date(), endDate = Date(), price = 1000.0)
        )
    }


    Column(modifier = Modifier.padding(16.dp)) {
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(trips, key = { it.id }) { trip ->
                TripItem(
                    trip = trip,
                    onClick = {
                        navController.navigate("tripDetail/${trip.id}") // <-- Navega con el ID
                    }
                )
            }
        }
    }

}

@Composable
fun TripItem(trip: Trip, onClick: () -> Unit) {
    CustomCard(
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
fun CustomCard( // <- Nombre único
    modifier: Modifier = Modifier,
    elevation: Dp = 4.dp,
    content: @Composable () -> Unit
) {
    Card( // <- Ahora sí usa la Card de Material3
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = elevation)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            content()
        }
    }
}

@Preview
@Composable
fun ItinerarioScreenPrev() {
    ItinerariosScreen(navController = rememberNavController())
}

