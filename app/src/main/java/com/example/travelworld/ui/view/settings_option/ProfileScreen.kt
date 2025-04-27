package com.example.travelworld.ui.view.settings_option



import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.travelworld.R
import com.example.travelworld.ui.viewmodel.AuthState
import com.example.travelworld.ui.viewmodel.AuthViewModel


@Composable
fun ProfileScreen(navController: NavController, authViewModel: AuthViewModel) {
    val authState = authViewModel.authState.observeAsState()
    LaunchedEffect(authState.value) {
        when (authState.value) {
            is AuthState.Unauthenticated -> navController.navigate("login")
            else -> Unit
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = stringResource(id = R.string.profile_screen) + " - UserId: 1")
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {
            // Navigate back to Home
            navController.navigate("home") {
                popUpTo("home") { inclusive = true }
            }
        }) {
            Text(stringResource(id = R.string.back_to_home))
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {
            // SignOut
            authViewModel.signout()
        }) {
            Text(stringResource(id = R.string.SignOut))
        }

    }
}