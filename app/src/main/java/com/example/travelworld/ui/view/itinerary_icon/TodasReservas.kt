package com.example.travelworld.ui.view.itinerary_icon

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun TodasReservasScreen() {
    // Aquí puedes mostrar todas las reservas del sistema (vista tipo admin)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Todas las reservas", style = MaterialTheme.typography.titleLarge)
        // Mock lista de todas las reservas
        repeat(4) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Reserva global #${it + 1} - Hotel Global")
                    Text("Usuario: usuario${it + 1}@mail.com")
                    Text("Fechas: 02/06/2024 - 06/06/2024")
                }
            }
        }
    }
}
