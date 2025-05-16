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
            val base = BuildConfig.HOTELS_API_URL.trimEnd('/')
            val hotelImg = base + (reserva.hotel.imageUrl ?: "")

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Row(
                    modifier = Modifier
                        .padding(12.dp)
                        .fillMaxWidth()
                        .height(IntrinsicSize.Min)
                ) {
                    // Imagen izquierda
                    AsyncImage(
                        model = hotelImg,
                        contentDescription = "Imagen del hotel",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .width(100.dp)
                            .fillMaxHeight()
                            .padding(end = 12.dp)
                    )

                    // Box: texto arriba, icono basura abajo derecha
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                    ) {
                        Column(
                            modifier = Modifier.align(Alignment.TopStart)
                        ) {
                            Text("Hotel: ${reserva.hotel.name}",
                                fontWeight = FontWeight.Bold
                            )
                            Text("Check-in: ${reserva.startDate}")
                            Text("Check-out: ${reserva.endDate}")
                            Text("Habitación: ${reserva.room.roomType}")
                        }
                        // Icono de basura abajo derecha
                        IconButton(
                            onClick = { reservaToCancel = reserva.id },
                            modifier = Modifier.align(Alignment.BottomEnd)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Cancelar reserva",
                                tint = MaterialTheme.colorScheme.error
                            )
                        }
                    }
                }

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
