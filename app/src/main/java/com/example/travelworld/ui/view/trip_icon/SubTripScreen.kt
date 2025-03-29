package com.example.travelworld.ui.view.trip_icon

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.travelworld.domain.model.SubTrip
import com.example.travelworld.domain.model.Trip
import com.example.travelworld.ui.viewmodel.SubTripViewModel
import com.example.travelworld.ui.viewmodel.TripViewModel

@Composable
fun SubTripList(
    trip: Trip,
    subTripViewModel:
    SubTripViewModel = viewModel()) {
    val tripViewModel: TripViewModel = viewModel()

    LazyColumn {
        items(trip.subTrips) { subTrip ->
            SubTripItem(
                subTrip = subTrip,
                onEditClick = {
                    tripViewModel.trips = subTripViewModel.startEditingSubTrip(
                        trips = tripViewModel.trips,
                        tripId = trip.id,
                        subTripId = subTrip.id
                    )
                },
                onDeleteClick = {
                    tripViewModel.trips = subTripViewModel.deleteSubTrip(
                        trips = tripViewModel.trips,
                        tripId = trip.id,
                        subTrip = subTrip
                    )
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

@Composable
private fun SubTripItem(
    subTrip: SubTrip,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = subTrip.title,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
                Row {
                    Text(text = subTrip.date, style = MaterialTheme.typography.bodySmall)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = subTrip.location, style = MaterialTheme.typography.bodySmall)
                }
                if (subTrip.notes.isNotBlank()) {
                    Text(
                        text = subTrip.notes,
                        style = MaterialTheme.typography.bodySmall)
                }
            }
            IconButton(onClick = onEditClick) {
                Icon(Icons.Default.Edit, contentDescription = "Edit")
            }
            IconButton(onClick = onDeleteClick) {
                Icon(Icons.Default.Delete, contentDescription = "Delete")
            }
        }
    }
}

@Composable
fun AddSubTripDialog(
    tripViewModel: TripViewModel,
    subTripViewModel: SubTripViewModel
) {
    val trip = tripViewModel.trips.find { it.id == subTripViewModel.showAddSubTripDialog }

    AlertDialog(
        onDismissRequest = { subTripViewModel.dismissAddSubTripDialog() },
        title = { Text("Add Activity to ${trip?.destination ?: "Trip"}") },
        text = {
            Column {
                OutlinedTextField(
                    value = subTripViewModel.subTripTitle,
                    onValueChange = { subTripViewModel.updateSubTripTitle(it) },
                    label = { Text("Activity Title") }
                )
                OutlinedTextField(
                    value = subTripViewModel.subTripDate,
                    onValueChange = { subTripViewModel.updateSubTripDate(it) },
                    label = { Text("Date") }
                )
                OutlinedTextField(
                    value = subTripViewModel.subTripLocation,
                    onValueChange = { subTripViewModel.updateSubTripLocation(it) },
                    label = { Text("Location") }
                )
                OutlinedTextField(
                    value = subTripViewModel.subTripNotes,
                    onValueChange = { subTripViewModel.updateSubTripNotes(it) },
                    label = { Text("Notes") }
                )
            }
        },
        confirmButton = {
            Button(onClick = {
                val updatedTrips = subTripViewModel.addSubTrip(
                    trips = tripViewModel.trips,
                    tripId = subTripViewModel.showAddSubTripDialog
                )
                tripViewModel.trips = updatedTrips // Actualizar estado padre
                subTripViewModel.dismissAddSubTripDialog()
            }) {
                Text("Add Activity")
            }
        }
    )
}
