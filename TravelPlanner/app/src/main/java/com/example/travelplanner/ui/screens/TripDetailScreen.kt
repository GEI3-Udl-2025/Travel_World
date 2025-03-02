package com.example.travelplanner.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.travelplanner.Trip
import java.util.Date

@Composable
fun TripDetailScreen(tripId: String?) {
    // Obtén el viaje usando el ID (ejemplo básico)
    val resolvedTrip = remember(tripId) {
        getTripById(tripId)
    }

    Column(modifier = Modifier.padding(16.dp)) {
        if (resolvedTrip != null) {
            Text("Destino: ${resolvedTrip.destination}")
            Text("Precio: $${resolvedTrip.price}")
        } else {
            Text("Viaje no encontrado")
        }
    }
}

// Función de ejemplo mejorada
private fun getTripById(tripId: String?): Trip? {
    return when (tripId?.toIntOrNull()) {
        1 -> Trip(1, "Paris", Date(), Date(), 1200.0)
        2 -> Trip(2, "Tokyo", Date(), Date(), 1500.0)
        3 -> Trip(3, "New York", Date(), Date(), 1000.0)
        else -> null
    }
}
