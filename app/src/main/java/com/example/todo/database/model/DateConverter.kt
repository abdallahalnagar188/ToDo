package com.example.todo.database.model

import androidx.room.TypeConverter
import java.util.Date

class DateConverter {
    @TypeConverter
    fun fromTimeToLong(date: Date) : Long{
        return date.time
    }
    @TypeConverter
    fun fromLongToTime(date:Long) : Date {
        return Date(date)
    }
}