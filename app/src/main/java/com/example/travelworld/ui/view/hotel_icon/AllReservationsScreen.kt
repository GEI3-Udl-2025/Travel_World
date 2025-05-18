package com.example.travelworld.ui.view.hotel_icon

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Hotel
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
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
import com.example.travelworld.ui.components.ReservationRow


@Composable
fun AllReservationsApp(
    vm: AllReservationsViewModel = hiltViewModel()
) {
    val ui by vm.uiState.collectAsState()
    var hotelDialogImg by rememberSaveable { mutableStateOf<String?>(null) }
    var roomDialogImg by rememberSaveable { mutableStateOf<String?>(null) }

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
                ReservationRow(reserva = reserva)
            }
        }
    }
}
