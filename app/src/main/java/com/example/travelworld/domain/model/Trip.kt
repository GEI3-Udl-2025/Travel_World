package com.example.travelworld.domain.model


data class Trip(
    val id: Int,
    var destination: String,
    var startDate: String,
    var endDate: String,
    var notes: String,
    var subTrips: List<SubTrip> = emptyList(),
    var isEditing: Boolean = false,
    var isExpanded: Boolean = false
)
