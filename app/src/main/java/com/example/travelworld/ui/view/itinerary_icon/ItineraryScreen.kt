package com.example.travelworld.ui.view.itinerary_icon

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
import androidx.compose.foundation.layout.width
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.travelworld.R
import java.time.LocalDate

data class SubTrip(
    val id: Int,
    var title: String,
    var date: String,
    var location: String,
    var notes: String,
    var isEditing: Boolean = false
)

data class Trip(
    val id: Int,
    var destination: String,
    var startDate: String,
    var endDate: String,
    var notes: String,
    var subTrips: List<SubTrip> = emptyList(),
    var isEditing: Boolean = false,
    var isExpanded: Boolean = false
)

@RequiresApi(Build.VERSION_CODES.O)

@Composable
fun TripAppv1() {
    var trips by remember { mutableStateOf(listOf<Trip>()) }
    var showAddTripDialog by remember { mutableStateOf(false) }
    var showAddSubTripDialog by remember { mutableIntStateOf(-1) } // -1 means not showing, otherwise trip ID

    // Main trip fields
    var destination by remember { mutableStateOf("") }
    var startDate by remember { mutableStateOf("") }
    var endDate by remember { mutableStateOf("") }
    var tripNotes by remember { mutableStateOf("") }

    // Sub-trip fields
    var subTripTitle by remember { mutableStateOf("") }
    var subTripDate by remember { mutableStateOf("") }
    var subTripLocation by remember { mutableStateOf("") }
    var subTripNotes by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Button(
            onClick = {
                startDate = LocalDate.now().toString()
                endDate = LocalDate.now().plusDays(7).toString()
                showAddTripDialog = true
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(stringResource(id = R.string.add_new_trip))
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(trips) { trip ->
                TripCard(
                    trip = trip,
                    onEditClick = {
                        trips = trips.map {
                            if (it.id == trip.id) it.copy(isEditing = true)
                            else it.copy(isEditing = false)
                        }
                    },
                    onDeleteClick = { trips = trips - trip },
                    onExpandClick = {
                        trips = trips.map {
                            if (it.id == trip.id) it.copy(isExpanded = !it.isExpanded)
                            else it
                        }
                    },
                    onAddSubTripClick = {
                        subTripDate = trip.startDate // Default to trip start date
                        showAddSubTripDialog = trip.id
                    },
                    onSubTripEdit = { subTrip ->
                        trips = trips.map { t ->
                            if (t.id == trip.id) {
                                t.copy(subTrips = t.subTrips.map {
                                    if (it.id == subTrip.id) it.copy(isEditing = true)
                                    else it.copy(isEditing = false)
                                })
                            } else t
                        }
                    },
                    onSubTripDelete = { subTrip ->
                        trips = trips.map {
                            if (it.id == trip.id) {
                                it.copy(subTrips = it.subTrips - subTrip)
                            } else it
                        }
                    },
                    onSubTripEditComplete = { subTrip, title, date, location, notes ->
                        trips = trips.map { t ->
                            if (t.id == trip.id) {
                                t.copy(subTrips = t.subTrips.map {
                                    if (it.id == subTrip.id) {
                                        it.copy(
                                            title = title,
                                            date = date,
                                            location = location,
                                            notes = notes,
                                            isEditing = false
                                        )
                                    } else it
                                })
                            } else t
                        }
                    }
                )
            }
        }
    }

    // Dialog for adding a new trip
    if (showAddTripDialog) {
        AlertDialog(
            onDismissRequest = { showAddTripDialog = false },
            title = { Text(stringResource(id = R.string.plan_a_new_trip))},
            text = {
                Column {
                    OutlinedTextField(
                        value = destination,
                        onValueChange = { destination = it },
                        label = { Text(stringResource(id = R.string.destination)) },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    )

                    Row {
                        OutlinedTextField(
                            value = startDate,
                            onValueChange = { startDate = it },
                            label = {Text(stringResource(id = R.string.start_date) + "(YYYY-MM-DD)") },
                            singleLine = true,
                            modifier = Modifier
                                .weight(1f)
                                .padding(8.dp)
                        )

                        OutlinedTextField(
                            value = endDate,
                            onValueChange = { endDate = it },
                            label = { Text(stringResource(id = R.string.end_date) + "(YYYY-MM-DD)")},
                            singleLine = true,
                            modifier = Modifier
                                .weight(1f)
                                .padding(8.dp)
                        )
                    }

                    OutlinedTextField(
                        value = tripNotes,
                        onValueChange = { tripNotes = it },
                        label = { Text(stringResource(id = R.string.trip_notes))},
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    )
                }
            },
            confirmButton = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = {
                            if (destination.isNotBlank()) {
                                val newTrip = Trip(
                                    id = (trips.maxOfOrNull { it.id } ?: 0) + 1,
                                    destination = destination,
                                    startDate = startDate,
                                    endDate = endDate,
                                    notes = tripNotes
                                )
                                trips = trips + newTrip
                                showAddTripDialog = false
                                destination = ""
                                startDate = ""
                                endDate = ""
                                tripNotes = ""
                            }
                        }
                    ) {
                        Text(stringResource(id = R.string.add_trip))
                    }
                    Button(
                        onClick = { showAddTripDialog = false }
                    ) {
                        Text(stringResource(id = R.string.cancel))
                    }
                }
            }
        )
    }

    // Dialog for adding a new sub-trip
    if (showAddSubTripDialog != -1) {
        val trip = trips.find { it.id == showAddSubTripDialog }
        AlertDialog(
            onDismissRequest = { showAddSubTripDialog = -1 },
            title = { Text(stringResource(id = R.string.add_activity_to) + " ${trip?.destination ?: stringResource(id = R.string.trip)}")},
            text = {
                Column {
                    OutlinedTextField(
                        value = subTripTitle,
                        onValueChange = { subTripTitle = it },
                        label = { Text(stringResource(id = R.string.actiivity_title))},
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    )

                    OutlinedTextField(
                        value = subTripDate,
                        onValueChange = { subTripDate = it },
                        label = { Text(stringResource(id = R.string.date) + " (YYYY-MM-DD)") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    )

                    OutlinedTextField(
                        value = subTripLocation,
                        onValueChange = { subTripLocation = it },
                        label = { Text(stringResource(id = R.string.location)) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    )

                    OutlinedTextField(
                        value = subTripNotes,
                        onValueChange = { subTripNotes = it },
                        label = { Text(stringResource(id = R.string.notes))},
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    )
                }
            },
            confirmButton = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = {
                            if (subTripTitle.isNotBlank()) {
                                trips = trips.map { t ->
                                    if (t.id == showAddSubTripDialog) {
                                        val newSubTrip = SubTrip(
                                            id = (t.subTrips.maxOfOrNull { it.id } ?: 0) + 1,
                                            title = subTripTitle,
                                            date = subTripDate,
                                            location = subTripLocation,
                                            notes = subTripNotes
                                        )
                                        t.copy(subTrips = t.subTrips + newSubTrip)
                                    } else t
                                }
                                showAddSubTripDialog = -1
                                subTripTitle = ""
                                subTripDate = ""
                                subTripLocation = ""
                                subTripNotes = ""
                            }
                        }
                    ) {
                        Text(stringResource(id = R.string.add_activity))
                    }
                    Button(
                        onClick = { showAddSubTripDialog = -1 }
                    ) {
                        Text(stringResource(id = R.string.cancel))
                    }
                }
            }
        )
    }
}

@Composable
fun TripCard(
    trip: Trip,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onExpandClick: () -> Unit,
    onAddSubTripClick: () -> Unit,
    onSubTripEdit: (SubTrip) -> Unit,
    onSubTripDelete: (SubTrip) -> Unit,
    onSubTripEditComplete: (SubTrip, String, String, String, String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
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
                    Icon(Icons.Default.Edit, contentDescription = stringResource(id = R.string.edit_trip))
                }

                IconButton(onClick = onDeleteClick) {
                    Icon(Icons.Default.Delete, contentDescription = stringResource(id = R.string.delete_trip))
                }
            }

            if (trip.notes.isNotBlank()) {
                Text(
                    text = trip.notes,
                    modifier = Modifier.padding(top = 8.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            if (trip.isExpanded) {
                // Add sub-trip button
                Button(
                    onClick = onAddSubTripClick,
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth()
                ) {
                    Icon(Icons.Default.Add, contentDescription = stringResource(id = R.string.add),
                        modifier = Modifier.padding(end = 8.dp))
                    Text(stringResource(id = R.string.add_activity))
                }

                // List of sub-trips
                Column(modifier = Modifier.padding(top = 8.dp)) {
                    trip.subTrips.forEach { subTrip ->
                        if (subTrip.isEditing) {
                            SubTripEditor(
                                subTrip = subTrip,
                                onEditComplete = { title, date, location, notes ->
                                    onSubTripEditComplete(subTrip, title, date, location, notes)
                                }
                            )
                        } else {
                            SubTripCard(
                                subTrip = subTrip,
                                onEditClick = { onSubTripEdit(subTrip) },
                                onDeleteClick = { onSubTripDelete(subTrip) }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SubTripCard(
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
                        modifier = Modifier.padding(top = 4.dp),
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }

            IconButton(onClick = onEditClick) {
                Icon(Icons.Default.Edit, contentDescription = "Edit", tint = MaterialTheme.colorScheme.primary)
            }

            IconButton(onClick = onDeleteClick) {
                Icon(Icons.Default.Delete, contentDescription = "Delete", tint = MaterialTheme.colorScheme.error)
            }
        }
    }
}

@Composable
fun SubTripEditor(
    subTrip: SubTrip,
    onEditComplete: (String, String, String, String) -> Unit
) {
    var title by remember { mutableStateOf(subTrip.title) }
    var date by remember { mutableStateOf(subTrip.date) }
    var location by remember { mutableStateOf(subTrip.location) }
    var notes by remember { mutableStateOf(subTrip.notes) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text(stringResource(id = R.string.title))},
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = date,
                onValueChange = { date = it },
                label = { Text(stringResource(id = R.string.date)) },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = location,
                onValueChange = { location = it },
                label = { Text(stringResource(id = R.string.location)) },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = notes,
                onValueChange = { notes = it },
                label = { Text(stringResource(id = R.string.notes)) },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = { onEditComplete(title, date, location, notes) },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(stringResource(id = R.string.save))
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun TripPreviewv1() {
    TripAppv1()
}