package com.example.travelplanner

import java.util.Date

data class Trip(
    val id: Int,
    var destination: String,
    var startDate: Date,
    var endDate: Date,
    var price: Double
)
