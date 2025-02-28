package com.example.travelplanner.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.travelplanner.Trip
import java.util.Date

@Composable
fun TripDetailScreen(tripId: String?) {
    // Obtener los detalles del viaje usando el tripId (esto puede ser consultado desde un ViewModel o base de datos)
    val trip = Trip(id = 1, destination = "Paris", startDate = Date(), endDate = Date(), description = "Un hermoso viaje a Paris", price = 1200.0)

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Detalles del Viaje", style = MaterialTheme.typography.titleLarge)
        Text("Destino: ${trip.destination}")
        Text("Descripción: ${trip.description}")
        Text("Precio: \$${trip.price}")
        // Agregar más detalles si es necesario
    }
}
