package com.example.travelworld.ui.view.hotel_icon

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.travelworld.ui.viewmodel.AllReservationsViewModel

@Composable
fun AllReservationsApp(
    vm: AllReservationsViewModel = hiltViewModel()
) {
    val ui by vm.uiState.collectAsState()

    // Recarga automáticamente al entrar
    LaunchedEffect(Unit) { vm.load() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Todas las reservas", style = MaterialTheme.typography.titleLarge)
            IconButton(onClick = { vm.load() }) {
                Icon(Icons.Default.Refresh, contentDescription = "Recargar")
            }
        }

        if (ui.loading) CircularProgressIndicator()
        if (ui.message != null) Text(ui.message!!, color = MaterialTheme.colorScheme.error)

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(ui.reservations) { reserva ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Hotel: ${reserva.hotel.name}")
                        Text("Usuario: ${reserva.guestEmail}")
                        Text("Fechas: ${reserva.startDate} - ${reserva.endDate}")
                    }
                }
            }
        }
    }
}
