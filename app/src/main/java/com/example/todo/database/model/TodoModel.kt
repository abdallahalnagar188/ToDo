package com.example.todo.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class TodoModel(
    @ColumnInfo
    @PrimaryKey(autoGenerate = true)
    val id:Int? = null,

    @ColumnInfo
    val title:String? = null,

    @ColumnInfo
    val description: String? = null,

    @ColumnInfo
    val isDone: Boolean? = false,

    @ColumnInfo
    val time: Date? = null
)
