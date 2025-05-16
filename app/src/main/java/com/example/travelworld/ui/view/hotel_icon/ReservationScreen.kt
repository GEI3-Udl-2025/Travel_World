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
import com.example.travelworld.ui.viewmodel.ReservationsViewModel

@Composable
fun ReservationApp(
    vm: ReservationsViewModel = hiltViewModel()
) {
    // TODO: pon el email real del usuario autenticado
    val userEmail = "usuario@email.com"
    val ui by vm.uiState.collectAsState()
    var reservaToCancel by remember { mutableStateOf<String?>(null) }

    // Recarga al entrar
    LaunchedEffect(Unit) { vm.load(userEmail) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Mis reservas", style = MaterialTheme.typography.titleLarge)
            IconButton(onClick = { vm.load(userEmail) }) {
                Icon(Icons.Default.Refresh, contentDescription = "Recargar")
            }
        }

        if (ui.loading) CircularProgressIndicator()
        if (ui.message != null) Text(ui.message!!, color = MaterialTheme.colorScheme.primary)

        if (ui.reservations.isEmpty() && !ui.loading) Text("No tienes reservas.")

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
                        Text("Check-in: ${reserva.startDate}")
                        Text("Check-out: ${reserva.endDate}")
                        Text("Habitación: ${reserva.room.roomType}")
                        Button(
                            onClick = { reservaToCancel = reserva.id },
                            modifier = Modifier.padding(top = 8.dp)
                        ) {
                            Text("Cancelar reserva")
                        }
                    }
                }
            }
        }
    }

    // Diálogo de confirmación de cancelación
    if (reservaToCancel != null) {
        AlertDialog(
            onDismissRequest = { reservaToCancel = null },
            confirmButton = {
                TextButton(onClick = {
                    reservaToCancel?.let { resId ->
                        vm.cancelReservationById(resId, userEmail)
                    }
                    reservaToCancel = null
                }) { Text("Confirmar") }
            },
            dismissButton = {
                TextButton(onClick = { reservaToCancel = null }) { Text("Cancelar") }
            },
            title = { Text("¿Cancelar reserva?") },
            text = { Text("¿Estás seguro de que deseas cancelar esta reserva?") }
        )
    }
}
