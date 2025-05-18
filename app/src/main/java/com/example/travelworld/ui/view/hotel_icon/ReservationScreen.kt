package com.example.travelworld.ui.view.hotel_icon

import androidx.compose.foundation.layout.*

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.travelworld.ui.viewmodel.ReservationsViewModel
import coil.compose.AsyncImage
import com.example.travelworld.BuildConfig
import com.example.travelworld.ui.components.ReservationRow


@Composable
fun ReservationApp(
    vm: ReservationsViewModel = hiltViewModel()
) {
    val userEmail = "xj3@udl.cat"
    val ui by vm.uiState.collectAsState()
    var reservaToCancel by remember { mutableStateOf<String?>(null) }

    // Recarga al entrar
    LaunchedEffect(Unit) { vm.load(userEmail) }

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(ui.reservations) { reserva ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                ReservationRow(
                    reserva = reserva,
                    onDelete = { reservaToCancel = reserva.id }
                )
            }
        }
    }



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
