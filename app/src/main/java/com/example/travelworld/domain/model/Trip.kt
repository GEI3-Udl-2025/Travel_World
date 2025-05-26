package com.example.travelworld.domain.model

// Trip.kt
data class Trip(
    val id: Int = 0,
    val title: String,
    val description: String,
    val startDate: String,
    val endDate: String,
    val subTrips: List<SubTrip> = emptyList(),
    var photoUri: String? = null,        // ← nuevo
    var isExpanded: Boolean = false
)
