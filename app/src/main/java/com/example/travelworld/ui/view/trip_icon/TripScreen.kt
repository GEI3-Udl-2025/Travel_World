package com.example.travelworld.ui.view.trip_icon

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.travelworld.domain.model.Trip
import com.example.travelworld.ui.viewmodel.TripViewModel
import com.example.travelworld.ui.viewmodel.SubTripViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TripApp(
    tripViewModel: TripViewModel = viewModel(),
    subTripViewModel: SubTripViewModel = viewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Button(
            onClick = { tripViewModel.showAddTripDialog() },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Add New Trip")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(tripViewModel.trips) { trip ->
                TripCard(
                    trip = trip,
                    onEditClick = { tripViewModel.startEditingTrip(trip.id) },
                    onDeleteClick = { tripViewModel.deleteTrip(trip) },
                    onExpandClick = { tripViewModel.toggleTripExpansion(trip.id) },
                    onAddSubTripClick = { subTripViewModel.showAddSubTripDialog(trip.id) },
                    onTripEditComplete = { dest, start, end, notes ->
                        tripViewModel.updateTrip(trip.id, dest, start, end, notes)
                    }
                )
            }
        }

        if (subTripViewModel.showAddSubTripDialog != -1) {
            AddSubTripDialog(
                tripViewModel = tripViewModel,
                subTripViewModel = subTripViewModel
            )
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TripCard(
    trip: Trip,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onExpandClick: () -> Unit,
    onAddSubTripClick: () -> Unit,
    onTripEditComplete: (String, String, String, String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            if (trip.isEditing) {
                TripEditor(trip = trip, onEditComplete = onTripEditComplete)
            } else {
                TripHeader(
                    trip = trip,
                    onEditClick = onEditClick,
                    onDeleteClick = onDeleteClick,
                    onExpandClick = onExpandClick
                )

                if (trip.isExpanded) {
                    TripExpandedContent(
                        trip = trip,
                        onAddSubTripClick = onAddSubTripClick
                    )
                }
            }
        }
    }
}

@Composable
private fun TripHeader(
    trip: Trip,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onExpandClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = trip.destination,
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
            )
            Text(
                text = "${trip.startDate} to ${trip.endDate}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
        IconButton(onClick = onExpandClick) {
            Icon(
                imageVector = if (trip.isExpanded) Icons.Default.ArrowDropUp else Icons.Default.ArrowDropDown,
                contentDescription = if (trip.isExpanded) "Collapse" else "Expand"
            )
        }
        IconButton(onClick = onEditClick) {
            Icon(Icons.Default.Edit, contentDescription = "Edit")
        }
        IconButton(onClick = onDeleteClick) {
            Icon(Icons.Default.Delete, contentDescription = "Delete")
        }
    }
}

@Composable
private fun TripExpandedContent(trip: Trip, onAddSubTripClick: () -> Unit) {
    Column {
        Button(
            onClick = onAddSubTripClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(Icons.Default.Add, contentDescription = "Add")
            Text("Add Activity")
        }
        SubTripList(trip = trip)
    }
}

@Composable
private fun TripEditor(trip: Trip, onEditComplete: (String, String, String, String) -> Unit) {
    var destination by remember { mutableStateOf(trip.destination) }
    var startDate by remember { mutableStateOf(trip.startDate) }
    var endDate by remember { mutableStateOf(trip.endDate) }
    var notes by remember { mutableStateOf(trip.notes) }

    Column {
        OutlinedTextField(
            value = destination,
            onValueChange = { destination = it },
            label = { Text("Destination") },
            modifier = Modifier.fillMaxWidth()
        )
        // En TripEditor y AddTripDialog hay errores en el bloque Row
        Row {
            OutlinedTextField(
                value = startDate,
                onValueChange = { startDate = it },
                label = { Text("Start Date") },
                modifier = Modifier.weight(1f)
            )

            OutlinedTextField(
                value = endDate,
                onValueChange = { endDate = it },
                label = { Text("End Date") },
                modifier = Modifier.weight(1f)
            )
        }
        OutlinedTextField(
            value = notes,
            onValueChange = { notes = it },
            label = { Text("Notes") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = { onEditComplete(destination, startDate, endDate, notes) },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Save Changes")
        }
    }
}

@Composable
private fun AddTripDialog(viewModel: TripViewModel) {
    AlertDialog(
        onDismissRequest = { viewModel.dismissAddTripDialog() },
        title = { Text("Plan a New Trip") },
        text = {
            Column {
                OutlinedTextField(
                    value = viewModel.destination,
                    onValueChange = { viewModel.updateDestination(it) },
                    label = { Text("Destination") }
                )
                Row {
                    OutlinedTextField(
                        value = viewModel.startDate,
                        onValueChange = { viewModel.updateStartDate(it) },
                        label = { Text("Start Date") },
                        modifier = Modifier.weight(1f)
                    )

                    OutlinedTextField (
                        value = viewModel.endDate,
                        onValueChange = { viewModel.updateEndDate(it) },
                        label = { Text("End Date") },
                        modifier = Modifier.weight(1f)
                    )
                }
                OutlinedTextField(
                    value = viewModel.tripNotes,
                    onValueChange = { viewModel.updateTripNotes(it) },
                    label = { Text("Notes") }
                )
            }
        },
        confirmButton = {
            Button(onClick = { viewModel.addTrip() }) {
                Text("Add Trip")
            }
        }
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun TripPreview() {
    TripApp()
}