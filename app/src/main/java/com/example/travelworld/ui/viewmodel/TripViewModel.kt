package com.example.travelworld.ui.viewmodel

// viewmodels/TripViewModel.kt
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.travelworld.domain.model.Trip

class TripViewModel : ViewModel() {
    var trips by mutableStateOf(listOf<Trip>())

    // Trip fields
    var destination by mutableStateOf("")
        private set
    var startDate by mutableStateOf("")
        private set
    var endDate by mutableStateOf("")
        private set
    var tripNotes by mutableStateOf("")
        private set

    // Dialog state
    var showAddTripDialog by mutableStateOf(false)
        private set

    fun showAddTripDialog() {
        showAddTripDialog = true
    }

    fun dismissAddTripDialog() {
        showAddTripDialog = false
        clearTripFields()
    }

    private fun clearTripFields() {
        destination = ""
        startDate = ""
        endDate = ""
        tripNotes = ""
    }

    fun updateDestination(value: String) { destination = value }
    fun updateStartDate(value: String) { startDate = value }
    fun updateEndDate(value: String) { endDate = value }
    fun updateTripNotes(value: String) { tripNotes = value }

    fun addTrip() {
        if (destination.isNotBlank()) {
            val newTrip = Trip(
                id = (trips.maxOfOrNull { it.id } ?: 0) + 1,
                destination = destination,
                startDate = startDate,
                endDate = endDate,
                notes = tripNotes
            )
            trips = trips + newTrip
            dismissAddTripDialog()
        }
    }

    fun deleteTrip(trip: Trip) {
        trips = trips - trip
    }

    fun toggleTripExpansion(tripId: Int) {
        trips = trips.map {
            if (it.id == tripId) it.copy(isExpanded = !it.isExpanded)
            else it
        }
    }

    fun startEditingTrip(tripId: Int) {
        trips = trips.map {
            if (it.id == tripId) it.copy(isEditing = true)
            else it.copy(isEditing = false)
        }
    }

    fun updateTrip(
        tripId: Int,
        newDestination: String,
        newStartDate: String,
        newEndDate: String,
        newNotes: String
    ) {
        trips = trips.map { trip ->
            if (trip.id == tripId) {
                trip.copy(
                    destination = newDestination,
                    startDate = newStartDate,
                    endDate = newEndDate,
                    notes = newNotes,
                    isEditing = false
                )
            } else trip
        }
    }
}