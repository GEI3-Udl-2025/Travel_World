package com.example.travelworld.data.local

import androidx.room.TypeConverter
import java.util.*

class DateConverter {
    @TypeConverter fun fromTimestamp(value: Long?): Date? = value?.let { Date(it) }
    @TypeConverter fun dateToTimestamp(date: Date?): Long? = date?.time
}
