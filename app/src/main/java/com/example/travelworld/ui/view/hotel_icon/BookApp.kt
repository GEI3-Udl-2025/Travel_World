package com.example.travelworld.ui.view.hotel_icon

import android.app.DatePickerDialog
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.travelworld.domain.model.ReserveRequest
import com.example.travelworld.ui.viewmodel.BookViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookApp(
    viewModel: BookViewModel = hiltViewModel()
) {
    val ui by viewModel.uiState.collectAsState()
    val context = LocalContext.current
    val formatter = DateTimeFormatter.ISO_DATE

    var showDialog by remember { mutableStateOf(false) }
    var guestName by remember { mutableStateOf("XiaoLong") }
    var guestEmail by remember { mutableStateOf("xj3@udl.cat") }
    var roomToBook by remember { mutableStateOf<Triple<String, String, Float>?>(null) }
    var bookingMessage by remember { mutableStateOf<String?>(null) }

    var expanded by remember { mutableStateOf(true) }
    // Estado del scroll de la lista de hoteles
    val hotelListState = rememberLazyListState()

    // Si hace scroll (no está arriba del todo), colapsa la búsqueda
    LaunchedEffect(hotelListState.firstVisibleItemIndex, hotelListState.isScrollInProgress) {
        if (hotelListState.firstVisibleItemIndex > 0 && expanded) expanded = false
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Buscar hoteles", style = MaterialTheme.typography.titleLarge)

        ExposedDropdownMenuBox(
            expanded = ui.cityMenu,
            onExpandedChange = { viewModel.toggleCityMenu() }
        ) {
            OutlinedTextField(
                value = ui.city,
                onValueChange = {},
                label = { Text("Ciudad") },
                readOnly = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = ui.cityMenu,
                onDismissRequest = { viewModel.toggleCityMenu() }
            ) {
                viewModel.cities.forEach {
                    DropdownMenuItem(
                        text = { Text(it) },
                        onClick = { viewModel.selectCity(it) }
                    )
                }
            }
        }
        Spacer(Modifier.height(8.dp))

        // DatePickers
        Row {
            OutlinedTextField(
                value = ui.startDate?.format(formatter) ?: "",
                onValueChange = {},
                label = { Text("Fecha inicio") },
                readOnly = true,
                trailingIcon = {
                    IconButton(onClick = {
                        DatePickerDialog(context, { _, y, m, d ->
                            viewModel.pickStart(LocalDate.of(y, m + 1, d))
                        }, ui.startDate?.year ?: LocalDate.now().year, (ui.startDate?.monthValue ?: 1) - 1, ui.startDate?.dayOfMonth ?: 1).show()
                    }) {
                        Icon(Icons.Default.CalendarToday, contentDescription = "Elegir fecha inicio")
                    }
                },
                modifier = Modifier.weight(1f)
            )
            Spacer(Modifier.width(8.dp))
            OutlinedTextField(
                value = ui.endDate?.format(formatter) ?: "",
                onValueChange = {},
                label = { Text("Fecha fin") },
                readOnly = true,
                trailingIcon = {
                    IconButton(onClick = {
                        DatePickerDialog(context, { _, y, m, d ->
                            viewModel.pickEnd(LocalDate.of(y, m + 1, d))
                        }, ui.endDate?.year ?: LocalDate.now().year, (ui.endDate?.monthValue ?: 1) - 1, ui.endDate?.dayOfMonth ?: 1).show()
                    }) {
                        Icon(Icons.Default.CalendarToday, contentDescription = "Elegir fecha fin")
                    }
                },
                modifier = Modifier.weight(1f)
            )
        }
        Spacer(Modifier.height(8.dp))

        Button(
            onClick = { viewModel.search() },
            modifier = Modifier.fillMaxWidth()
        ) { Text("Buscar") }
        Spacer(Modifier.height(16.dp))

        if (ui.loading) CircularProgressIndicator()
        if (ui.message != null) Text(ui.message!!, color = MaterialTheme.colorScheme.error)
        if (bookingMessage != null) {
            Text(bookingMessage!!, color = MaterialTheme.colorScheme.primary, modifier = Modifier.padding(vertical = 8.dp))
        }

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(ui.hotels) { hotel ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(hotel.name, style = MaterialTheme.typography.titleMedium)
                        Text("Dirección: ${hotel.address}")
                        Text("Rating: ${hotel.rating}")
                        if (!hotel.rooms.isNullOrEmpty()) {
                            Text("Habitaciones disponibles:")
                            hotel.rooms.forEach { room ->
                                val nights = if (ui.startDate != null && ui.endDate != null)
                                    java.time.temporal.ChronoUnit.DAYS.between(ui.startDate, ui.endDate).toInt() else 1
                                val total = room.price * nights
                                Text("- ${room.roomType} - ${room.price}€/noche, Total: $total€")
                                Button(
                                    onClick = {
                                        roomToBook = Triple(hotel.id, room.id, total)
                                        showDialog = true
                                    },
                                    modifier = Modifier.padding(vertical = 4.dp)
                                ) {
                                    Text("Reservar esta habitación")
                                }
                            }
                        } else {
                            Text("No hay habitaciones disponibles", color = MaterialTheme.colorScheme.error)
                        }
                    }
                }
            }
        }
    }

    // Diálogo para datos de usuario y confirmación total
    if (showDialog && roomToBook != null) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {
                TextButton(onClick = {
                    showDialog = false
                    val (hotelId, roomId, total) = roomToBook!!
                    val req = ReserveRequest(
                        hotelId = hotelId,
                        roomId = roomId,
                        startDate = ui.startDate?.format(formatter) ?: "",
                        endDate = ui.endDate?.format(formatter) ?: "",
                        guestName = guestName,
                        guestEmail = guestEmail
                    )
                    viewModel.reserve(req) { res ->
                        bookingMessage = if (res != null) {
                            "¡Reserva completada en hotel ${res.hotel.name} para ${res.room.roomType}!\nTotal pagado: $total€"
                        } else {
                            "No se pudo realizar la reserva"
                        }
                    }
                }) { Text("Reservar") }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) { Text("Cancelar") }
            },
            title = { Text("Confirmar reserva") },
            text = {
                Column {
                    OutlinedTextField(
                        value = guestName,
                        onValueChange = { guestName = it },
                        label = { Text("Nombre") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = guestEmail,
                        onValueChange = { guestEmail = it },
                        label = { Text("Email") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Text("Total: ${roomToBook?.third ?: 0}€", modifier = Modifier.padding(top = 8.dp))
                }
            }
        )
    }
}
