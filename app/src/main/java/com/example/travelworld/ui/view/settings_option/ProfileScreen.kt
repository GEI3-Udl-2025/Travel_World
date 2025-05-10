package com.example.travelworld.ui.view.settings_option

import android.app.DatePickerDialog
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.travelworld.R
import com.example.travelworld.data.local.entity.UserEntity
import com.example.travelworld.ui.viewmodel.ProfileViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    // Observables del ViewModel
    val user by viewModel.user.observeAsState()
    val error by viewModel.error.observeAsState()

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    // 1) Campos “saveable”
    var username by rememberSaveable { mutableStateOf("") }
    var birthdate by rememberSaveable { mutableStateOf(Date()) }
    var address by rememberSaveable { mutableStateOf("") }
    var country by rememberSaveable { mutableStateOf("") }
    var phone by rememberSaveable { mutableStateOf("") }
    var acceptEmails by rememberSaveable { mutableStateOf(false) }

    // estados de control
    var isEditing by rememberSaveable { mutableStateOf(false) }
    var readyToSave by rememberSaveable { mutableStateOf(false) }
    var showDatePicker by remember { mutableStateOf(false) }

    // 2) Sólo la PRIMERA vez que llega el usuario
    var initialUser by remember { mutableStateOf<UserEntity?>(null) }
    LaunchedEffect(user) {
        user?.let { u ->
            if (initialUser == null) {
                initialUser = u
                username = u.username
                birthdate = u.birthdate
                address = u.address
                country = u.country
                phone = u.phone
                acceptEmails = u.acceptEmails
                isEditing = false
            }
        }
    }

    // Calcula si hay cambios respecto al estado inicial
    val isDirty = initialUser?.let {
        username != it.username ||
                birthdate != it.birthdate ||
                address != it.address ||
                country != it.country ||
                phone != it.phone ||
                acceptEmails != it.acceptEmails
    } ?: false

    // Dialogo nativo para fecha
    if (showDatePicker) {
        val cal = Calendar.getInstance().apply { time = birthdate }
        DatePickerDialog(
            context,
            { _, y, m, d ->
                birthdate = Calendar.getInstance().apply { set(y, m, d) }.time
                showDatePicker = false
            },
            cal.get(Calendar.YEAR),
            cal.get(Calendar.MONTH),
            cal.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.profile_screen)) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = stringResource(R.string.back)
                        )
                    }
                },
                actions = {

                    // Si aún no estamos editando ni listos para guardar: lápiz
                    if (!isEditing && !readyToSave) {
                        IconButton(onClick = {
                            isEditing = true
                        }) {
                            Icon(
                                Icons.Default.Edit,
                                contentDescription = stringResource(R.string.edit)
                            )
                        }
                    }
                    // Si estamos en edición: check para confirmar cambios
                    else if (isEditing) {
                        IconButton(onClick = {
                            isEditing = false
                            readyToSave = true
                        }) {
                            Icon(
                                Icons.Default.Check,
                                contentDescription = stringResource(R.string.confirm)
                            )
                        }
                    }

                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Campo de texto reutilizable
            @Composable
            fun Field(
                label: Int,
                value: String,
                onValueChange: (String) -> Unit,
                readOnly: Boolean = false,
                trailing: @Composable (() -> Unit)? = null,
                onClick: (() -> Unit)? = null
            ) {
                OutlinedTextField(
                    value = value,
                    onValueChange = { if (!readOnly) onValueChange(it) },
                    readOnly = readOnly,
                    enabled = !readOnly,
                    label = { Text(stringResource(label)) },
                    trailingIcon = trailing,
                    modifier = Modifier
                        .fillMaxWidth()
                        .then(
                            if (onClick != null)
                                Modifier.clickable(enabled = !readOnly) { onClick() }
                            else Modifier
                        )
                )
            }

            val dateFmt = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

            Field(
                label = R.string.username,
                value = username,
                onValueChange = { username = it },
                readOnly = !isEditing,
                trailing = {
                    if (isEditing && initialUser?.username != username) {
                        Icon(
                            Icons.Default.Check,
                            contentDescription = stringResource(R.string.field_changed)
                        )
                    }
                }
            )

            Field(
                label = R.string.birthdate,
                value = dateFmt.format(birthdate),
                onValueChange = {},
                readOnly = true,
                onClick = { if (isEditing) showDatePicker = true },
                trailing = {
                    if (isEditing && initialUser?.birthdate != birthdate) {
                        Icon(
                            Icons.Default.Check,
                            contentDescription = stringResource(R.string.field_changed)
                        )
                    }
                }
            )

            Field(
                label = R.string.address,
                value = address,
                onValueChange = { address = it },
                readOnly = !isEditing,
                trailing = {
                    if (isEditing && initialUser?.address != address) {
                        Icon(
                            Icons.Default.Check,
                            contentDescription = stringResource(R.string.field_changed)
                        )
                    }
                }
            )

            Field(
                label = R.string.country,
                value = country,
                onValueChange = { country = it },
                readOnly = !isEditing,
                trailing = {
                    if (isEditing && initialUser?.country != country) {
                        Icon(
                            Icons.Default.Check,
                            contentDescription = stringResource(R.string.field_changed)
                        )
                    }
                }
            )

            Field(
                label = R.string.phone,
                value = phone,
                onValueChange = { phone = it },
                readOnly = !isEditing,
                trailing = {
                    if (isEditing && initialUser?.phone != phone) {
                        Icon(
                            Icons.Default.Check,
                            contentDescription = stringResource(R.string.field_changed)
                        )
                    }
                }
            )

            // Checkbox de emails
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = acceptEmails,
                    onCheckedChange = { if (isEditing) acceptEmails = it },
                    enabled = isEditing
                )
                Spacer(Modifier.width(8.dp))
                Text(stringResource(R.string.accept_emails))
                if (isEditing && initialUser?.acceptEmails != acceptEmails) {
                    Spacer(Modifier.width(4.dp))
                    Icon(
                        Icons.Default.Check,
                        contentDescription = stringResource(R.string.field_changed)
                    )
                }
            }

            // Mensaje de error bajo los campos
            error?.let {
                Text(it, color = MaterialTheme.colorScheme.error)
            }

            // Solo cuando readyToSave == true mostramos el botón final
            if (readyToSave) {
                Spacer(Modifier.height(24.dp))
                Button(
                    onClick = {
                        viewModel.save(username, birthdate, address, country, phone, acceptEmails)
                        // 3) actualizamos el punto inicial para la próxima vez
                        initialUser = initialUser?.copy(
                            username = username,
                            birthdate = birthdate,
                            address = address,
                            country = country,
                            phone = phone,
                            acceptEmails = acceptEmails
                        )
                        readyToSave = false
                        isEditing = false

                        coroutineScope.launch {
                            val msg = context.getString(R.string.profile_saved)
                            val okLabel = context.getString(R.string.ok)
                            snackbarHostState.showSnackbar(
                                message = msg,
                                actionLabel = okLabel
                            )
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(stringResource(R.string.save))
                }
            }
        }
    }
}
