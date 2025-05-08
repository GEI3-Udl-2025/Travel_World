package com.example.travelworld.domain.model

// Subtrip.kt
data class SubTrip(
    val id: Int = 0,
    val parentTripId: Int,
    var title: String,
    var date: String,
    var time: String,
    var location: String,
    var description: String,
    var isExpanded: Boolean = false
)