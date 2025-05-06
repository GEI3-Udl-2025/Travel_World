package com.example.travelworld.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelworld.domain.model.Trip
import com.example.travelworld.domain.repository.TripRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class TripViewModel @Inject constructor(
    private val repository: TripRepository
) : ViewModel() {

    private val _trips = MutableStateFlow<List<Trip>>(emptyList())
    val trips: StateFlow<List<Trip>> = _trips.asStateFlow()

    var lastSelectedTrip by mutableStateOf<Trip?>(null)
        private set

    fun setSelectedTrip(trip: Trip) {
        lastSelectedTrip = trip
    }

    init {
        loadTrips(userId)
    }

    fun loadTrips(userId: String) {
        viewModelScope.launch {
            repository.getTrips(userId).collect { tripsList ->
                _trips.value = tripsList
            }
        }
    }

    fun addTrip(trip: Trip, userId: String) {
        viewModelScope.launch {
            repository.addTrip(trip.copy(userId = userId))
            loadTrips(userId)
        }
    }

    fun deleteTrip(tripId: Int) {
        viewModelScope.launch {
            repository.deleteTrip(tripId)
            loadTrips(userId)
        }
    }

    fun updateTrip(trip: Trip) {
        viewModelScope.launch {
            repository.updateTrip(trip)
            loadTrips(userId)
        }
    }
}