package com.example.travelworld.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey val login: String,            // email o UID de Firebase
    val username: String,
    val birthdate: Date,
    val address: String,
    val country: String,
    val phone: String,
    val acceptEmails: Boolean
)