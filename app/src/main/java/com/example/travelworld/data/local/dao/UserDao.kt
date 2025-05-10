package com.example.travelworld.data.local.dao

import androidx.room.*
import com.example.travelworld.data.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM users WHERE login = :login")
    fun getUser(login: String): Flow<UserEntity?>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(user: UserEntity)

    @Update
    suspend fun update(user: UserEntity)

    @Query("SELECT COUNT(*) FROM users WHERE username = :username AND login != :currentLogin")
    suspend fun countByUsername(username: String, currentLogin: String): Int
}
