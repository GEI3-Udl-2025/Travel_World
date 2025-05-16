package com.example.travelworld.ui.view.itinerary_icon

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun ReservarHotelScreen() {
    // Aquí va la UI de búsqueda de hoteles, selección de fechas, listado y reserva
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Buscar hoteles", style = MaterialTheme.typography.titleLarge)
        // Aquí puedes poner Dropdown para ciudad, DatePickers para fechas, etc.
        Spacer(modifier = Modifier.height(12.dp))
        // Mock lista de hoteles
        repeat(3) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Hotel ${it + 1}", style = MaterialTheme.typography.titleMedium)
                    Text("Ciudad: Londres")
                    Text("Habitación: Doble - 80€/noche")
                    Button(
                        onClick = { /* Acción de reservar */ },
                        modifier = Modifier.padding(top = 8.dp)
                    ) {
                        Text("Reservar")
                    }
                }
            }
        }
    }
}
