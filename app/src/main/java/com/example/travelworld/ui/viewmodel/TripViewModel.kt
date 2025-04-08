package com.example.travelworld.ui.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelworld.domain.model.Trip
import com.example.travelworld.domain.repository.TripRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TripViewModel @Inject constructor(
    private val repository: TripRepository
) : ViewModel() {

    // Estado mutable para mantener el listado de tareas
    private val _trips = mutableStateListOf<Trip>()
    val trips: List<Trip> get() = _trips

    init {
        loadTrips()
    }

    private fun loadTrips() {
        _trips.clear()
        _trips.addAll(repository.getTrips())
    }

    fun addTrip(Trip: Trip) {
        viewModelScope.launch {
            repository.addTrip(Trip)
            loadTrips()
        }
    }

    fun deleteTrip(TripId: Int) {
        viewModelScope.launch {
            repository.deleteTrip(TripId)
            loadTrips()
        }
    }

    fun updateTrip(Trip: Trip) {
        viewModelScope.launch {
            repository.updateTrip(Trip)
            loadTrips()
        }
    }


}