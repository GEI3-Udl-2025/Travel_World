package com.example.travelworld.ui.view.trip_icon

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.travelworld.domain.model.Trip
import com.example.travelworld.ui.viewmodel.TripViewModel

@Composable
fun TripApp(
    navController: NavController,
    viewModel: TripViewModel = hiltViewModel()
) {
    val trips by viewModel.trips.collectAsState(initial = emptyList())
    val lastSelectedTrip = viewModel.lastSelectedTrip
    var showTripDialog by remember { mutableStateOf(false) }
    var isEditingTrip by remember { mutableStateOf(false) }
    var currentTripId by remember { mutableStateOf(0) }
    var tripTitle by remember { mutableStateOf("") }
    var tripDescription by remember { mutableStateOf("") }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    isEditingTrip = false
                    tripTitle = ""
                    tripDescription = ""
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
                                tripTitle = trip.title
                                tripDescription = trip.description
                                showTripDialog = true
                            },
                            onOpen = {
                                viewModel.setSelectedTrip(trip)
                                navController.navigate("subtrips/${trip.id}")
                            },
                            onDelete = {
                                viewModel.deleteTrip(trip.id)
                            }
                        )
                    }
                }
            }

            if (showTripDialog) {
                AlertDialog(
                    onDismissRequest = { showTripDialog = false },
                    title = { Text(text = if (isEditingTrip) "Edit Trip" else "New Trip") },
                    text = {
                        Column {
                            OutlinedTextField(
                                value = tripTitle,
                                onValueChange = { tripTitle = it },
                                label = { Text("Title") },
                                modifier = Modifier.fillMaxWidth()
                            )
                            OutlinedTextField(
                                value = tripDescription,
                                onValueChange = { tripDescription = it },
                                label = { Text("Description") },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 8.dp)
                            )
                        }
                    },
                    confirmButton = {
                        Button(
                            onClick = {
                                if (isEditingTrip) {
                                    viewModel.updateTrip(
                                        Trip(
                                            id = currentTripId,
                                            title = tripTitle,
                                            description = tripDescription
                                        )
                                    )
                                } else {
                                    viewModel.addTrip(
                                        Trip(
                                            title = tripTitle,
                                            description = tripDescription
                                        )
                                    )
                                }
                                showTripDialog = false
                            }
                        ) {
                            Text("Save")
                        }
                    },
                    dismissButton = {
                        Button(onClick = { showTripDialog = false }) {
                            Text("Cancel")
                        }
                    }
                )
            }
        }
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
                    text = trip.title,
                    style = MaterialTheme.typography.titleMedium
                )
                if (trip.description.isNotBlank()) {
                    Text(
                        text = trip.description,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }

            IconButton(onClick = onEdit) {
                Icon(Icons.Filled.Edit, "Edit Trip")
            }
            IconButton(onClick = onOpen) {
                Icon(Icons.Filled.ArrowForward, "Open Trip")
            }
            IconButton(onClick = onDelete) {
                Icon(Icons.Filled.Delete, "Delete Trip")
            }
        }
    }
}