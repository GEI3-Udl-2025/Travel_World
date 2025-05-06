package com.example.travelworld.ui.view.settings_option



import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.travelworld.R
import com.example.travelworld.ui.viewmodel.AuthState
import com.example.travelworld.ui.viewmodel.AuthViewModel
import com.example.travelworld.ui.viewmodel.UserProfileViewModel
import com.google.type.Date


@Composable
fun ProfileScreen(
    navController: NavController,
    authViewModel: AuthViewModel,
    profileViewModel: UserProfileViewModel = hiltViewModel()
) {
    val authState = authViewModel.authState.observeAsState()
    val userProfile by profileViewModel.userProfile.collectAsState()
    val usernameAvailable by profileViewModel.usernameAvailable.collectAsState()

    var username by remember { mutableStateOf("") }
    var birthDate by remember { mutableStateOf(Date()) }
    var address by remember { mutableStateOf("") }
    var country by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var acceptEmails by remember { mutableStateOf(false) }
    var showDatePicker by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        profileViewModel.loadUserProfile()
    }

    LaunchedEffect(userProfile) {
        userProfile?.let {
            username = it.username
            birthDate = it.birthDate
            address = it.address
            country = it.country
            phoneNumber = it.phoneNumber
            acceptEmails = it.acceptEmails
        }
    }

    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            onDateSelected = { date ->
                birthDate = date
                showDatePicker = false
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Profile", style = MaterialTheme.typography.headlineMedium)

        OutlinedTextField(
            value = username,
            onValueChange = {
                username = it
                profileViewModel.checkUsernameAvailability(it)
            },
            label = { Text("Username") },
            isError = !usernameAvailable,
            supportingText = {
                if (!usernameAvailable) {
                    Text("Username already taken")
                }
            }
        )

        OutlinedTextField(
            value = birthDate.toString(),
            onValueChange = {},
            label = { Text("Birth Date") },
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = { showDatePicker = true }) {
                    Icon(Icons.Default.CalendarToday, "Select date")
                }
            }
        )

        OutlinedTextField(
            value = address,
            onValueChange = { address = it },
            label = { Text("Address") }
        )

        OutlinedTextField(
            value = country,
            onValueChange = { country = it },
            label = { Text("Country") }
        )

        OutlinedTextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            label = { Text("Phone Number") }
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = acceptEmails,
                onCheckedChange = { acceptEmails = it }
            )
            Text("Accept marketing emails")
        }

        Button(
            onClick = {
                profileViewModel.updateUserProfile(
                    username = username,
                    birthDate = birthDate,
                    address = address,
                    country = country,
                    phoneNumber = phoneNumber,
                    acceptEmails = acceptEmails
                )
            },
            enabled = usernameAvailable && username.isNotBlank()
        ) {
            Text("Save Profile")
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(onClick = { authViewModel.signout() }) {
            Text("Sign Out")
        }
    }
}