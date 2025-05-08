package com.example.travelworld.ui.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelworld.domain.model.SubTrip
import com.example.travelworld.domain.repository.TripRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SubTripViewModel @Inject constructor(
    private val repository: TripRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _subTrips = mutableStateListOf<SubTrip>()

    val subTrips: List<SubTrip> get() = _subTrips

    val tripId: Int = try {
        savedStateHandle.get<String>("tripId")?.toIntOrNull() ?: 0
    } catch (e: Exception) {
        0
    }

    init {
        loadSubTrips()
    }

    private fun loadSubTrips() {
        viewModelScope.launch {
            _subTrips.clear()
            _subTrips.addAll(repository.getSubTripsForTrip(tripId))
        }
    }

    fun addSubTrip(subTrip: SubTrip) {
        viewModelScope.launch {
            repository.addSubTrip(subTrip)
            _subTrips.add(subTrip)
        }
    }

    fun deleteSubTrip(subTripId: Int) {
        viewModelScope.launch {
            repository.deleteSubTrip(subTripId)
            _subTrips.removeIf { it.id == subTripId }
        }
    }

    fun updateSubTrip(updatedSubTrip: SubTrip) {
        viewModelScope.launch {
            repository.updateSubTrip(updatedSubTrip)
            val index = _subTrips.indexOfFirst { it.id == updatedSubTrip.id }
            if (index != -1) {
                _subTrips[index] = updatedSubTrip
            }
        }
    }

    fun toggleSubTripExpansion(subTripId: Int) {
        val index = _subTrips.indexOfFirst { it.id == subTripId }
        if (index != -1) {
            _subTrips[index] = _subTrips[index].copy(isExpanded = !_subTrips[index].isExpanded)
        }
    }
}