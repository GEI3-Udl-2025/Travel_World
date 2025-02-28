// AddTripScreen.kt

package com.example.travelplanner.ui.screens

import android.app.DatePickerDialog
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.travelplanner.Trip
import java.util.*

@Composable
fun AddTripScreen(navController: NavHostController) {
    // Estado para los campos de texto y fecha
    var destination by remember { mutableStateOf("") }
    var price by remember { mutableStateOf(0.0) }
    var departureDate by remember { mutableStateOf<Date?>(null) }  // Fecha de ida

    val startDate = remember { mutableStateOf(Date()) }
    val endDate = remember { mutableStateOf(Date()) }
    val showDatePickerDialog = remember { mutableStateOf(false) }

    // Mostrar la fecha de ida
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Add New Trip", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(16.dp))

        // Campo para el destino
        TextField(
            value = destination,
            onValueChange = { destination = it },
            label = { Text("Destiny") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Campo para la fecha de ida
        Button(
            onClick = { showDatePickerDialog.value = true },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Select Departure Date")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Mostrar la fecha seleccionada, si hay alguna
        Text(
            text = "Departure date: ${departureDate?.let { it.toString() } ?: "Not selected"}",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Campo para el presupuesto
        TextField(
            value = price.toString(),
            onValueChange = { price = it.toDoubleOrNull() ?: 0.0 },
            label = { Text("Budget") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botón para guardar el viaje
        Button(
            onClick = {
                // Verificar que la fecha de ida esté seleccionada antes de continuar
                if (departureDate != null) {
                    val trip = Trip(
                        id = 0, // Aquí podrías agregar lógica para generar un ID único
                        destination = destination,
                        startDate = startDate.value,
                        endDate = endDate.value,
                        description = "Trip to $destination",  // Aquí conservamos un "description" opcional
                        price = price
                    )

                    // Aquí podrías hacer algo con el objeto trip, por ejemplo, guardarlo en una base de datos
                    Toast.makeText(navController.context, "Trip added: ${trip.destination}", Toast.LENGTH_SHORT).show()

                    // Después de agregar el viaje, puedes navegar a otra pantalla, por ejemplo, la pantalla de inicio
                    navController.navigate("home")
                } else {
                    Toast.makeText(navController.context, "Please, select a date of departure", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save Trip")
        }
    }

    // Muestra el DatePickerDialog si el valor de showDatePickerDialog es verdadero
    if (showDatePickerDialog.value) {
        DatePickerDialog(
            navController.context,
            { _, year, month, dayOfMonth ->
                // Establecer la fecha de ida seleccionada
                departureDate = Calendar.getInstance().apply {
                    set(year, month, dayOfMonth)
                }.time
                // Ocultar el dialogo una vez que se haya seleccionado la fecha
                showDatePickerDialog.value = false
            },
            Calendar.getInstance().get(Calendar.YEAR),
            Calendar.getInstance().get(Calendar.MONTH),
            Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        ).show()
    }
}

@Preview(showBackground = true)
@Composable
fun AddTripScreenPreview() {
    AddTripScreen(navController = rememberNavController())
}
