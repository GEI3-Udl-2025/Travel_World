package com.example.travelworld.ui.view.hotel_icon

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Hotel
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.travelworld.ui.viewmodel.AllReservationsViewModel
import coil.compose.AsyncImage
import com.example.travelworld.BuildConfig


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
                    Row(
                        modifier = Modifier
                            .padding(12.dp)
                            .fillMaxWidth()
                            .height(IntrinsicSize.Min)
                    ) {
                        val base = BuildConfig.HOTELS_API_URL.trimEnd('/')
                        val hotelImg = base + (reserva.hotel.imageUrl ?: "")

                        AsyncImage(
                            model = hotelImg,
                            contentDescription = "Imagen del hotel",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .width(100.dp)
                                .fillMaxHeight()
                                .padding(end = 12.dp)
                        )

                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .align(Alignment.CenterVertically)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.Hotel,
                                    contentDescription = "Hotel",
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.size(20.dp)
                                )
                                Spacer(Modifier.width(6.dp))
                                Text(
                                    reserva.hotel.name,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.Person,
                                    contentDescription = "Usuario",
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.size(20.dp)
                                )
                                Spacer(Modifier.width(6.dp))
                                Text(
                                    reserva.guestEmail,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.CalendarToday,
                                    contentDescription = "Fecha entrada",
                                    tint = Color(0xFF4CAF50), // Verde
                                    modifier = Modifier.size(18.dp)
                                )
                                Spacer(Modifier.width(6.dp))
                                Text("Check-in: ${reserva.startDate}")
                            }
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.CalendarToday,
                                    contentDescription = "Fecha salida",
                                    tint = Color(0xFFF44336), // Rojo
                                    modifier = Modifier.size(18.dp)
                                )
                                Spacer(Modifier.width(6.dp))
                                Text("Check-out: ${reserva.endDate}")
                            }

                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.Hotel,
                                    contentDescription = "Habitación",
                                    tint = Color(0xFF2196F3), // Azul
                                    modifier = Modifier.size(18.dp)
                                )
                                Spacer(Modifier.width(6.dp))
                                Text("Habitación: ${reserva.room.roomType}")
                            }
                        }
                    }
                }
            }
        }

    }
}
