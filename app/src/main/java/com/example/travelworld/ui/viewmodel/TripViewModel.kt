package com.example.travelworld.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelworld.domain.model.Trip
import com.example.travelworld.domain.repo.TripRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TripViewModel @Inject constructor(
    private val repository: TripRepository
) : ViewModel() {

    // Cambia a StateFlow para notificar cambios
    private val _trips = MutableStateFlow<List<Trip>>(emptyList())
    val trips: StateFlow<List<Trip>> = _trips.asStateFlow()

    var lastSelectedTrip by mutableStateOf<Trip?>(null)
        private set

    fun setSelectedTrip(trip: Trip) {
        lastSelectedTrip = trip
    }

    init {
        loadTrips()
    }

    private fun loadTrips() {
        viewModelScope.launch {
            repository.getTrips()
                .collect { tripsList ->
                    _trips.value = tripsList
                }
        }
    }

    fun addTrip(trip: Trip) {
        viewModelScope.launch {
            repository.addTrip(trip)
            loadTrips() // Recargar después de añadir
        }
    }

    fun deleteTrip(tripId: Int) {
        viewModelScope.launch {
            repository.deleteTrip(tripId)
            loadTrips()
        }
    }

    fun updateTrip(trip: Trip) {
        viewModelScope.launch {
            repository.updateTrip(trip)
            loadTrips()
        }
    }
    fun toggleTripExpansion(tripId: Int) {
        viewModelScope.launch {
            _trips.value = _trips.value.map { trip ->
                if (trip.id == tripId) {
                    trip.copy(isExpanded = !trip.isExpanded)
                } else {
                    trip
                }
            }
        }
    }
}