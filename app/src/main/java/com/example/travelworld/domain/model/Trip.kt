package com.example.travelworld.domain.model

// Trip.kt
data class Trip(
    val id: Int = 0,
    var destination: String,
    var startDate: String,
    var endDate: String,
    var note: String,
    var subTrips: List<SubTrip> = emptyList(),
)
