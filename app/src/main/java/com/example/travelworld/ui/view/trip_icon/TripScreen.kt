package com.example.travelworld.ui.view.trip_icon

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.travelworld.domain.model.Trip
import com.example.travelworld.ui.viewmodel.TripViewModel
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TripApp(
    navController: NavController,
    viewModel: TripViewModel = hiltViewModel()
) {
    val trips = viewModel.trips
    var showTripDialog by remember { mutableStateOf(false) }
    var isEditingTrip by remember { mutableStateOf(false) }
    var currentTripId by remember { mutableIntStateOf(0) }
    var tripDestination by remember { mutableStateOf("") }
    var tripStartDate by remember { mutableStateOf("") }
    var tripEndDate by remember { mutableStateOf("") }
    var tripNote by remember { mutableStateOf("") }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    isEditingTrip = false
                    tripDestination = ""
                    tripStartDate = LocalDate.now().toString()
                    tripEndDate = LocalDate.now().plusDays(7).toString()
                    tripNote = ""
                    showTripDialog = true
                }
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Trip")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            if (trips.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("No trips available. Add your first trip!")
                }
            } else {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(trips) { trip ->
                        TripItem(
                            trip = trip,
                            onEdit = {
                                isEditingTrip = true
                                currentTripId = trip.id
                                tripDestination = trip.destination
                                tripStartDate = trip.startDate
                                tripEndDate = trip.endDate
                                tripNote = trip.note
                                showTripDialog = true
                            },
                            onOpen = {
                                navController.navigate("subtrips/${trip.id}")
                            },
                            onDelete = {
                                viewModel.deleteTrip(trip.id)
                            }
                        )
                    }
                }
            }


            //con validación de los campos
            if (showTripDialog) {
                var errorMessage by remember { mutableStateOf<String?>(null) }

                AlertDialog(
                    onDismissRequest = {
                        showTripDialog = false
                        errorMessage = null
                    },
                    title = { Text(text = if (isEditingTrip) "Editar Viaje" else "Nuevo Viaje") },
                    text = {
                        Column {
                            if (errorMessage != null) {
                                Text(
                                    text = errorMessage!!,
                                    color = MaterialTheme.colorScheme.error,
                                    modifier = Modifier.padding(bottom = 8.dp)
                                )
                            }

                            OutlinedTextField(
                                value = tripDestination,
                                onValueChange = { tripDestination = it },
                                label = { Text("Destino*") },
                                singleLine = true,
                                isError = errorMessage != null && tripDestination.isEmpty(),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp)
                            )

                            Row {
                                OutlinedTextField(
                                    value = tripStartDate,
                                    onValueChange = { tripStartDate = it },
                                    label = { Text("Fecha Inicio* (YYYY-MM-DD)") },
                                    singleLine = true,
                                    isError = errorMessage != null && (tripStartDate.isEmpty() || !isValidDate(tripStartDate)),
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(8.dp)
                                )

                                OutlinedTextField(
                                    value = tripEndDate,
                                    onValueChange = { tripEndDate = it },
                                    label = { Text("Fecha Fin* (YYYY-MM-DD)") },
                                    singleLine = true,
                                    isError = errorMessage != null && (tripEndDate.isEmpty() || !isValidDate(tripEndDate)),
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(8.dp)
                                )
                            }

                            OutlinedTextField(
                                value = tripNote,
                                onValueChange = { tripNote = it },
                                label = { Text("Notas") },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp)
                            )
                        }
                    },
                    confirmButton = {
                        Button(
                            onClick = {
                                when {
                                    tripDestination.isBlank() -> {
                                        errorMessage = "El destino es requerido"
                                    }
                                    tripStartDate.isBlank() -> {
                                        errorMessage = "La fecha de inicio es requerida"
                                    }
                                    !isValidDate(tripStartDate) -> {
                                        errorMessage = "Formato de fecha inicio inválido (YYYY-MM-DD)"
                                    }
                                    tripEndDate.isBlank() -> {
                                        errorMessage = "La fecha de fin es requerida"
                                    }
                                    !isValidDate(tripEndDate) -> {
                                        errorMessage = "Formato de fecha fin inválido (YYYY-MM-DD)"
                                    }
                                    !isEndDateAfterStartDate(tripStartDate, tripEndDate) -> {
                                        errorMessage = "La fecha fin debe ser posterior a la fecha inicio"
                                    }
                                    else -> {
                                        if (isEditingTrip) {
                                            viewModel.updateTrip(
                                                Trip(
                                                    id = currentTripId,
                                                    destination = tripDestination,
                                                    startDate = tripStartDate,
                                                    endDate = tripEndDate,
                                                    note = tripNote,
                                                )
                                            )
                                        } else {
                                            viewModel.addTrip(
                                                Trip(
                                                    destination = tripDestination,
                                                    startDate = tripStartDate,
                                                    endDate = tripEndDate,
                                                    note = tripNote,
                                                )
                                            )
                                        }
                                        showTripDialog = false
                                    }
                                }
                            }
                        ) {
                            Text(if (isEditingTrip) "Actualizar" else "Crear")
                        }
                    },
                    dismissButton = {
                        Button(onClick = {
                            showTripDialog = false
                            errorMessage = null
                        }) {
                            Text("Cancelar")
                        }
                    }
                )
            }
        }
    }
}
@RequiresApi(Build.VERSION_CODES.O)
private fun isValidDate(dateStr: String): Boolean {
    return try {
        LocalDate.parse(dateStr)
        true
    } catch (e: Exception) {
        false
    }
}

@RequiresApi(Build.VERSION_CODES.O)
private fun isEndDateAfterStartDate(startDateStr: String, endDateStr: String): Boolean {
    return try {
        val startDate = LocalDate.parse(startDateStr)
        val endDate = LocalDate.parse(endDateStr)
        endDate.isAfter(startDate) || endDate.isEqual(startDate)
    } catch (e: Exception) {
        false
    }
}
@Composable
fun TripItem(
    trip: Trip,
    onEdit: () -> Unit,
    onOpen: () -> Unit,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = trip.destination,
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                )
                Text(
                    text = "${trip.startDate} to ${trip.endDate}",
                    style = MaterialTheme.typography.bodyMedium,

                )
                if (trip.note.isNotBlank()) {
                    Text(
                        text = trip.note,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }

            IconButton(onClick = onOpen) {
                Icon(Icons.Filled.ArrowForward, "View Subtrips")
            }
            IconButton(onClick = onEdit) {
                Icon(Icons.Filled.Edit, "Edit Trip")
            }

            IconButton(onClick = onDelete) {
                Icon(Icons.Filled.Delete, "Delete Trip")
            }
        }
    }
}