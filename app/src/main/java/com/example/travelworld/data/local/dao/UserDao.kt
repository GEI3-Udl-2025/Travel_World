package com.example.travelworld.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.travelworld.data.local.entity.UserEntity
import com.google.type.Date

@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(user: UserEntity)

    @Query("SELECT * FROM users WHERE id = :userId")
    suspend fun getUser(userId: String): UserEntity?

    @Query("SELECT * FROM users WHERE username = :username")
    suspend fun getUserByUsername(username: String): UserEntity?

    @Query("SELECT * FROM users WHERE username = :username AND id != :currentUserId")
    suspend fun isUsernameAvailable(username: String, currentUserId: String): UserEntity?

    @Query("UPDATE users SET username = :username, birthDate = :birthDate, address = :address, country = :country, phoneNumber = :phoneNumber, acceptEmails = :acceptEmails WHERE id = :userId")
    suspend fun updateUserProfile(
        userId: String,
        username: String,
        birthDate: Date,
        address: String,
        country: String,
        phoneNumber: String,
        acceptEmails: Boolean
    )
}

