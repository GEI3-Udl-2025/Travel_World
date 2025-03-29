package com.example.travelworld.domain.model

data class SubTask(
    val id: Int = 0,
    val parentTaskId: Int,
    val title: String,
    val description: String,
    val date: String,
    val isMandatory: Boolean,
    val isCompleted: Boolean = false
)