package com.example.travelworld.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import com.example.travelworld.data.local.entity.UserAccessLogEntity

@Dao
interface UserAccessLogDao {
    @Insert
    suspend fun logAccess(userAccessLog: UserAccessLogEntity)
}