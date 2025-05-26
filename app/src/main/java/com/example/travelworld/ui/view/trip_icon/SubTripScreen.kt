
import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerColors
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.travelworld.R
import com.example.travelworld.domain.model.SubTrip
import com.example.travelworld.ui.components.SubTripItem
import com.example.travelworld.ui.components.TimePickerDialog
import com.example.travelworld.ui.viewmodel.SubTripViewModel
import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.ui.draw.clip
import coil.compose.AsyncImage

@SuppressLint("DefaultLocale")
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
    var subTripDate by remember { mutableStateOf(LocalDate.now()) }
    var subTripTime by remember {
        mutableStateOf(
            LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"))
        )
    }
    var subTripLocation by remember { mutableStateOf("") }
    var subTripDescription by remember { mutableStateOf("") }

    var showDatePicker by remember { mutableStateOf(false) }
    var showTimePicker by remember { mutableStateOf(false) }

    //imagen
    var subTripPhotoUri by remember { mutableStateOf<Uri?>(null) }

    val pickSubTripImageLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            subTripPhotoUri = uri
        }

    if (isEditing) {
        subTripPhotoUri = subTrips.find { it.id == currentSubTripId }?.photoUri?.let { Uri.parse(it) }
    }

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
                    subTripDate = LocalDate.now()
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
                                subTripDate = LocalDate.now()
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
            title = {
                Text(
                    if (isEditing) stringResource(id = R.string.edit_subtrip) else stringResource(
                        id = R.string.new_subtrip
                    )
                )
            },
            text = {
                Column {
                    if (errorMessage != null) {
                        Text(
                            text = errorMessage!!,
                            color = MaterialTheme.colorScheme.error,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                    }
                    //imagen
                    // 1️⃣ Vista previa de la foto
                    subTripPhotoUri?.let { uri ->
                        AsyncImage(
                            model = uri,
                            contentDescription = "Foto de ${subTripTitle}",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(140.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .padding(bottom = 8.dp)
                        )
                    }

                    // 2️⃣ Botón para añadir/cambiar foto
                    OutlinedButton(
                        onClick = { pickSubTripImageLauncher.launch("image/*") },
                        modifier = Modifier.padding(vertical = 8.dp)
                    ) {
                        Icon(Icons.Default.CameraAlt, contentDescription = null)
                        Spacer(Modifier.width(6.dp))
                        Text(if (subTripPhotoUri == null) "Añadir foto" else "Cambiar foto")
                    }

                    OutlinedTextField(
                        value = subTripTitle,
                        onValueChange = { subTripTitle = it },
                        label = { Text(stringResource(id = R.string.title) + "*") },
                        singleLine = true,
                        isError = errorMessage != null && subTripTitle.isEmpty(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    )

                    Row(modifier = Modifier.fillMaxWidth()) {
                        // Fecha
                        OutlinedTextField(
                            value = subTripDate.format(DateTimeFormatter.ISO_LOCAL_DATE),
                            onValueChange = { /* no dejar que el usuario escriba manualmente */ },
                            label = { Text(stringResource(id = R.string.date) + "*") },
                            singleLine = true,
                            readOnly = true,
                            enabled = false,
                            isError = errorMessage != null && (
                                    subTripDate.toString().isEmpty() || !isValidDate(subTripDate.toString())
                                    ),
                            modifier = Modifier
                                .weight(1f)
                                .padding(8.dp)
                                .clickable { showDatePicker = true },
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                disabledBorderColor    = MaterialTheme.colorScheme.primary,
                                disabledLabelColor     = MaterialTheme.colorScheme.onSurfaceVariant,
                                disabledTextColor      = MaterialTheme.colorScheme.onSurface,
                                disabledPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant

                            )

                        )

                        // Hora
                        OutlinedTextField(
                            value = subTripTime,
                            onValueChange = { /* no dejar que el usuario escriba manualmente */ },
                            label = { Text(stringResource(id = R.string.time) + "*") },
                            singleLine = true,
                            readOnly = true,
                            enabled = false,
                            isError = errorMessage != null && (
                                    subTripTime.isEmpty() || !isValidTime(subTripTime)
                                    ),
                            modifier = Modifier
                                .weight(1f)
                                .padding(8.dp)
                                .clickable { showTimePicker = true },
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                disabledBorderColor    = MaterialTheme.colorScheme.primary,
                                disabledLabelColor     = MaterialTheme.colorScheme.onSurfaceVariant,
                                disabledTextColor      = MaterialTheme.colorScheme.onSurface,
                                disabledPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant

                            )

                        )
                    }


                    OutlinedTextField(
                        value = subTripLocation,
                        onValueChange = { subTripLocation = it },
                        label = { Text(stringResource(id = R.string.location) + "*") },
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

                            subTripDate.isBefore(LocalDate.now()) -> {
                                errorMessage = "The date cannot be in the past"
                            }

                            subTripTime.isBlank() -> {
                                errorMessage = "The time is required"
                            }

                            !isValidTime(subTripTime) -> {
                                errorMessage = "The time is invalid"
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
                                            date = subTripDate.toString(),
                                            time = subTripTime.toString(),
                                            location = subTripLocation,
                                            description = subTripDescription,
                                            photoUri     = subTripPhotoUri?.toString()
                                        )
                                    )
                                } else {
                                    viewModel.addSubTrip(
                                        SubTrip(
                                            parentTripId = tripId,
                                            title = subTripTitle,
                                            date = subTripDate.toString(),
                                            time = subTripTime.toString(),
                                            location = subTripLocation,
                                            description = subTripDescription,
                                            photoUri     = subTripPhotoUri?.toString()
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

    // DatePicker Dialog
    if (showDatePicker) {
        val datePickerState = rememberDatePickerState(
            initialSelectedDateMillis = subTripDate.atStartOfDay(ZoneId.systemDefault())
                .toInstant()
                .toEpochMilli()
        )

        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        datePickerState.selectedDateMillis?.let {
                            subTripDate = Instant.ofEpochMilli(it)
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate()
                        }
                        showDatePicker = false
                    }
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { showDatePicker = false }
                ) {
                    Text("Cancelar")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }


    // TimePicker Dialog
    if (showTimePicker) {
        // Parsear la hora actual para establecer los valores iniciales
        val timeParts = subTripTime.split(":")
        val initialHour = timeParts[0].toInt()
        val initialMinute = timeParts[1].toInt()

        val timePickerState = rememberTimePickerState(
            initialHour = initialHour,
            initialMinute = initialMinute,
            is24Hour = true // Usar formato 24 horas
        )

        TimePickerDialog(
            onDismissRequest = { showTimePicker = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        // Formatear la hora seleccionada como "HH:mm"
                        subTripTime =
                            String.format("%02d:%02d", timePickerState.hour, timePickerState.minute)
                        showTimePicker = false
                    }
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { showTimePicker = false }
                ) {
                    Text("Cancelar")
                }
            }
        ) {
            TimePicker(state = timePickerState)
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

//todo fecha no usar string, usar LocalDate
//todo revisar stateflow/mutableListOf en trip y subTrip
//todo datapicker y timepicker metologia de implementacion en codigo diferente entrp y subtrip

