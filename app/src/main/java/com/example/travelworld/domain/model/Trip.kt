package com.example.travelworld.domain.model

// Trip.kt
data class Trip(
    val id: Int = 0,
    var title: String,
    var startDate: String,
    var endDate: String,
    var description: String,
    var isExpanded: Boolean = false,
    var subTrips: List<SubTrip> = emptyList(),
)
