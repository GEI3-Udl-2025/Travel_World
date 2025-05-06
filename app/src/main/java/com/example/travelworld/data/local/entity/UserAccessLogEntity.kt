package com.example.travelworld.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "user_access_logs")
data class UserAccessLogEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val userId: String,
    val action: String, // "LOGIN" or "LOGOUT"
    val timestamp: Date = Date()
)