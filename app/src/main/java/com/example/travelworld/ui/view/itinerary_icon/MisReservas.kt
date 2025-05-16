package com.example.travelworld.ui.view.itinerary_icon

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
fun MisReservasScreen() {
    // Aquí se listarán las reservas guardadas localmente por el usuario
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Mis reservas", style = MaterialTheme.typography.titleLarge)
        // Mock lista de reservas
        repeat(2) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Reserva #${it + 1} - Hotel Central")
                    Text("Check-in: 01/06/2024")
                    Text("Check-out: 05/06/2024")
                    Button(
                        onClick = { /* Acción de cancelar */ },
                        modifier = Modifier.padding(top = 8.dp)
                    ) {
                        Text("Cancelar reserva")
                    }
                }
            }
        }
    }
}
