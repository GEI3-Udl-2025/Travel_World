package com.example.travelworld.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelworld.domain.model.Trip
import com.example.travelworld.domain.repo.TripRepository
import com.google.firebase.auth.FirebaseAuth
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

    // StateFlow para la lista de viajes :contentReference[oaicite:0]{index=0}:contentReference[oaicite:1]{index=1}
    private val _trips = MutableStateFlow<List<Trip>>(emptyList())
    val trips: StateFlow<List<Trip>> = _trips.asStateFlow()

    // Para retener el último viaje seleccionado en la UI
    var lastSelectedTrip by mutableStateOf<Trip?>(null)
        private set

    init {
        loadTrips()
    }

    // ① Usa repository.getTrips(), no getTripsForUser
    private fun loadTrips() {
        viewModelScope.launch {
            repository.getTrips().collect { tripsList ->
                _trips.value = tripsList
            }
        }
    }

    fun setSelectedTrip(trip: Trip) {
        lastSelectedTrip = trip
    }

    // ② Llama al repo sin pasar login como parámetro
    fun addTrip(trip: Trip) = viewModelScope.launch {
        repository.addTrip(trip)
        loadTrips()
    }

    fun updateTrip(trip: Trip) = viewModelScope.launch {
        repository.updateTrip(trip)
        loadTrips()
    }

    fun deleteTrip(tripId: Int) = viewModelScope.launch {
        repository.deleteTrip(tripId)
        loadTrips()
    }

    // ③ Lógica de expansión inalterada
    fun toggleTripExpansion(tripId: Int) {
        _trips.value = _trips.value.map { trip ->
            if (trip.id == tripId) trip.copy(isExpanded = !trip.isExpanded)
            else trip
        }
    }
}
