import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.travelworld.R
import com.example.travelworld.domain.model.SubTrip
import com.example.travelworld.ui.components.SubTripItem
import com.example.travelworld.ui.viewmodel.SubTripViewModel
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubTripApp(
    navController: NavController,
    tripId: Int,
    viewModel: SubTripViewModel = hiltViewModel()
) {
    val subTrips = viewModel.subTrips

    // Estados para el diálogo de edición/creación
    var showDialog by remember { mutableStateOf(false) }
    var isEditing by remember { mutableStateOf(false) }
    var currentSubTripId by remember { mutableIntStateOf(0) }

    var subTripTitle by remember { mutableStateOf("") }
    var subTripDate by remember { mutableStateOf("") }
    var subTripTime by remember { mutableStateOf("") }
    var subTripLocation by remember { mutableStateOf("") }
    var subTripDescription by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { stringResource(id = R.string.SubTrip) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }

                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    isEditing = false
                    subTripTitle = ""
                    subTripDate= LocalDate.now().toString()
                    subTripTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"))
                    subTripDescription = ""
                    showDialog = true
                }
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Añadir Subtarea")
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            if (subTrips.isEmpty()) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(stringResource(id = R.string.no_subtrips))
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(subTrips) { subTrip ->
                        SubTripItem(
                            subTrip = subTrip,
                            onEdit = {
                                isEditing = true
                                currentSubTripId = subTrip.id
                                subTripTitle = subTrip.title
                                subTripDate = subTrip.date
                                subTripTime = subTrip.time
                                subTripLocation = subTrip.location
                                subTripDescription = subTrip.description
                                showDialog = true
                            },
                            onDelete = {
                                viewModel.deleteSubTrip(subTrip.id)
                                       },
                            onExpandClick = {
                                viewModel.toggleSubTripExpansion(subTrip.id)
                            }
                        )
                    }
                }
            }
        }
    }

    if (showDialog) {
        var errorMessage by remember { mutableStateOf<String?>(null) }

        AlertDialog(
            onDismissRequest = {
                showDialog = false
                errorMessage = null
            },
            title = { Text(if (isEditing) stringResource(id = R.string.edit_subtrip) else stringResource(id = R.string.new_subtrip)) },
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
                        value = subTripTitle,
                        onValueChange = { subTripTitle = it },
                        label = { Text(stringResource(id = R.string.title)+"*" )},
                        singleLine = true,
                        isError = errorMessage != null && subTripTitle.isEmpty(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    )

                    Row {
                        OutlinedTextField(
                            value = subTripDate,
                            onValueChange = { subTripDate = it },
                            label = {Text(stringResource(id = R.string.date) + "* (YYYY-MM-DD)" )},
                            singleLine = true,
                            isError = errorMessage != null && (subTripDate.isEmpty() || !isValidDate(subTripDate)),
                            modifier = Modifier
                                .weight(1f)
                                .padding(8.dp)
                        )

                        OutlinedTextField(
                            value = subTripTime,
                            onValueChange = { subTripTime = it },
                            label = { Text(stringResource(id = R.string.time) + "* (HH:MM)")},
                            singleLine = true,
                            isError = errorMessage != null && (subTripTime.isEmpty() || !isValidTime(subTripTime)),
                            modifier = Modifier
                                .weight(1f)
                                .padding(8.dp)
                        )
                    }

                    OutlinedTextField(
                        value = subTripLocation,
                        onValueChange = { subTripLocation = it },
                        label = { Text(stringResource(id = R.string.location) + "*")},
                        singleLine = true,
                        isError = errorMessage != null && subTripLocation.isEmpty(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    )

                    OutlinedTextField(
                        value = subTripDescription,
                        onValueChange = { subTripDescription = it },
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
                            subTripTitle.isBlank() -> {
                                errorMessage = "The title is required"
                            }
                            subTripDate.isBlank() -> {
                                errorMessage = "The date is required"
                            }
                            !isValidDate(subTripDate) -> {
                                errorMessage = "Date format is invalid (YYYY-MM-DD)"
                            }
                            subTripTime.isBlank() -> {
                                errorMessage = "The time is required"
                            }
                            !isValidTime(subTripTime) -> {
                                errorMessage = "Time format is invalid (HH:MM)"
                            }
                            subTripLocation.isBlank() -> {
                                errorMessage = "The location is required"
                            }
                            else -> {
                                if (isEditing) {
                                    viewModel.updateSubTrip(
                                        SubTrip(
                                            id = currentSubTripId,
                                            parentTripId = tripId,
                                            title = subTripTitle,
                                            date = subTripDate,
                                            time = subTripTime,
                                            location = subTripLocation,
                                            description = subTripDescription
                                        )
                                    )
                                } else {
                                    viewModel.addSubTrip(
                                        SubTrip(
                                            parentTripId = tripId,
                                            title = subTripTitle,
                                            date = subTripDate,
                                            time = subTripTime,
                                            location = subTripLocation,
                                            description = subTripDescription
                                        )
                                    )
                                }
                                showDialog = false
                            }
                        }
                    }
                ) {
                    Text(stringResource(id = R.string.save))
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        showDialog = false
                        errorMessage = null
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                ) {
                    Text(stringResource(id = R.string.cancel))
                }
            }
        )
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

private fun isValidTime(timeStr: String): Boolean {
    return try {
        val parts = timeStr.split(":")
        if (parts.size != 2) return false
        val hours = parts[0].toInt()
        val minutes = parts[1].toInt()
        hours in 0..23 && minutes in 0..59
    } catch (e: Exception) {
        false
    }
}