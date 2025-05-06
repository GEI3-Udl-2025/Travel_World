package com.example.travelworld.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey
    val id: String, // Firebase UID or your own ID system
    val email: String,
    val username: String,
    val birthDate: Date,
    val address: String,
    val country: String,
    val phoneNumber: String,
    val acceptEmails: Boolean,
    val createdAt: Date = Date()
)