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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.travelworld.R
import com.example.travelworld.domain.model.Trip
import com.example.travelworld.ui.components.TripItem
import com.example.travelworld.ui.viewmodel.TripViewModel
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TripApp(
    navController: NavController,
    viewModel: TripViewModel = hiltViewModel()
) {
    val trips by viewModel.trips.collectAsState(initial = emptyList())

    var showTripDialog by remember { mutableStateOf(false) }
    var isEditingTrip by remember { mutableStateOf(false) }
    var currentTripId by remember { mutableStateOf(0) }

    var tripTitle by remember { mutableStateOf("") }
    var tripStartDate by remember { mutableStateOf("") }
    var tripEndDate by remember { mutableStateOf("") }
    var tripDescription by remember { mutableStateOf("") }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    isEditingTrip = false
                    tripTitle = ""
                    tripStartDate = LocalDate.now().toString()
                    tripEndDate = LocalDate.now().plusDays(7).toString()
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
                    Text(text = stringResource(id = R.string.no_trips))
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
                                tripStartDate = trip.startDate
                                tripEndDate = trip.endDate
                                tripDescription = trip.description
                                showTripDialog = true
                            },
                            onOpen = {
                                viewModel.setSelectedTrip(trip)
                                navController.navigate("subtrips/${trip.id}")
                            },
                            onDelete = {
                                viewModel.deleteTrip(trip.id)
                            },
                            onExpandClick = {
                                viewModel.toggleTripExpansion(trip.id)
                            }
                        )
                    }
                }
            }

            if (showTripDialog) {
                var errorMessage by remember { mutableStateOf<String?>(null) }

                AlertDialog(
                    onDismissRequest = {
                        showTripDialog = false
                        errorMessage = null
                    },
                    title = { Text(text = if (isEditingTrip) stringResource(id = R.string.edit_trip) else stringResource(id = R.string.new_trip)) },
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
                                value = tripTitle,
                                onValueChange = { tripTitle = it },
                                label = { Text(stringResource(id = R.string.destination) + "*") },
                                singleLine = true,
                                isError = errorMessage != null && tripTitle.isBlank(),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp)
                            )
                             Row{
                                OutlinedTextField(
                                    value = tripStartDate,
                                    onValueChange = { tripStartDate = it },
                                    label = { Text(stringResource(id = R.string.start_date) + "*") },
                                    singleLine = true,
                                    isError = errorMessage != null && tripStartDate.isBlank() || !isValidDate(tripStartDate),
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(8.dp)
                                )
                                OutlinedTextField(
                                    value = tripEndDate,
                                    onValueChange = { tripEndDate = it },
                                    label = { Text(stringResource(id = R.string.end_date) + "*") },
                                    singleLine = true,
                                    isError = errorMessage != null && tripEndDate.isBlank() || !isValidDate(tripEndDate),
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(8.dp)
                                )
                             }

                            OutlinedTextField(
                                value = tripDescription,
                                onValueChange = { tripDescription = it },
                                label = { Text(stringResource(id = R.string.description)) },
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
                                    tripTitle.isBlank() -> {
                                        errorMessage = "The destination is required"
                                    }
                                    tripStartDate.isBlank() -> {
                                        errorMessage = "The start date is required"
                                    }
                                    !isValidDate(tripStartDate) -> {
                                        errorMessage = "Invalid start date format (YYYY-MM-DD)"
                                    }
                                    tripEndDate.isBlank() -> {
                                        errorMessage = "The end date is required"
                                    }
                                    !isValidDate(tripEndDate) -> {
                                        errorMessage = "Invalid end date format (YYYY-MM-DD)"
                                    }
                                    !isEndDateAfterStartDate(tripStartDate, tripEndDate) -> {
                                        errorMessage = "The end date must be after the start date"
                                    }
                                    else -> {
                                        if (isEditingTrip) {
                                            viewModel.updateTrip(
                                                Trip(
                                                    id = currentTripId,
                                                    title = tripTitle,
                                                    startDate = tripStartDate,
                                                    endDate = tripEndDate,
                                                    description = tripDescription,
                                                )
                                            )
                                        } else {
                                            viewModel.addTrip(
                                                Trip(
                                                    title = tripTitle,
                                                    startDate = tripStartDate,
                                                    endDate = tripEndDate,
                                                    description = tripDescription,
                                                )
                                            )
                                        }
                                        showTripDialog = false
                                    }
                                }
                            }
                        ) {
                            Text(if (isEditingTrip) stringResource(id = R.string.refresh) else stringResource(id = R.string.create))
                        }
                    },
                    dismissButton = {
                        Button(onClick = {
                            showTripDialog = false
                            errorMessage = null
                        }) {
                            Text(stringResource(id = R.string.cancel))
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