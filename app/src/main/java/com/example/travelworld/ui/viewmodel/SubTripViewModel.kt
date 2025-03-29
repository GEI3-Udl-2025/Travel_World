package com.example.travelworld.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.travelworld.domain.model.SubTrip
import com.example.travelworld.domain.model.Trip

// viewmodels/SubTripViewModel.kt
class SubTripViewModel : ViewModel() {
    // Sub-trip fields
    var subTripTitle by mutableStateOf("")
        private set
    var subTripDate by mutableStateOf("")
        private set
    var subTripLocation by mutableStateOf("")
        private set
    var subTripNotes by mutableStateOf("")
        private set

    // Dialog state
    var showAddSubTripDialog by mutableStateOf(-1)
        private set

    fun showAddSubTripDialog(tripId: Int) {
        showAddSubTripDialog = tripId
    }

    fun dismissAddSubTripDialog() {
        showAddSubTripDialog = -1
        clearSubTripFields()
    }

    private fun clearSubTripFields() {
        subTripTitle = ""
        subTripDate = ""
        subTripLocation = ""
        subTripNotes = ""
    }

    fun updateSubTripTitle(value: String) { subTripTitle = value }
    fun updateSubTripDate(value: String) { subTripDate = value }
    fun updateSubTripLocation(value: String) { subTripLocation = value }
    fun updateSubTripNotes(value: String) { subTripNotes = value }

    fun addSubTrip(trips: List<Trip>, tripId: Int): List<Trip> {
        return trips.map { trip ->
            if (trip.id == tripId) {
                val newSubTrip = SubTrip(
                    id = (trip.subTrips.maxOfOrNull { it.id } ?: 0) + 1,
                    title = subTripTitle,
                    date = subTripDate,
                    location = subTripLocation,
                    notes = subTripNotes
                )
                trip.copy(subTrips = trip.subTrips + newSubTrip)
            } else trip
        }
    }

    fun startEditingSubTrip(trips: List<Trip>, tripId: Int, subTripId: Int): List<Trip> {
        return trips.map { trip ->
            if (trip.id == tripId) {
                trip.copy(subTrips = trip.subTrips.map { subTrip ->
                    if (subTrip.id == subTripId) subTrip.copy(isEditing = true)
                    else subTrip.copy(isEditing = false)
                })
            } else trip
        }
    }

    fun updateSubTrip(
        trips: List<Trip>,
        tripId: Int,
        subTripId: Int,
        newTitle: String,
        newDate: String,
        newLocation: String,
        newNotes: String
    ): List<Trip> {
        return trips.map { trip ->
            if (trip.id == tripId) {
                trip.copy(subTrips = trip.subTrips.map { subTrip ->
                    if (subTrip.id == subTripId) {
                        subTrip.copy(
                            title = newTitle,
                            date = newDate,
                            location = newLocation,
                            notes = newNotes,
                            isEditing = false
                        )
                    } else subTrip
                })
            } else trip
        }
    }

    fun deleteSubTrip(trips: List<Trip>, tripId: Int, subTrip: SubTrip): List<Trip> {
        return trips.map {
            if (it.id == tripId) {
                it.copy(subTrips = it.subTrips - subTrip)
            } else it
        }
    }
}