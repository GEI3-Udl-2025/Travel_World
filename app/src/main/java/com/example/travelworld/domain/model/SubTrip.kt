package com.example.travelworld.domain.model

data class SubTrip(
    val id: Int,
    var title: String,
    var date: String,
    var location: String,
    var notes: String,
    var isEditing: Boolean = false
)