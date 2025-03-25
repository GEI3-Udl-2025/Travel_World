package com.example.travelworld.domain.model

data class Task(
    val id: Int = 0,
    val title: String,
    val description: String,
    val date: String,
    val isMandatory: Boolean,
    val isCompleted: Boolean = false,
    val subTasks: List<SubTask> = emptyList()
)
