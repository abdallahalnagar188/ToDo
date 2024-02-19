package com.example.todo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.todo.database.model.DateConverter
import com.example.todo.database.dao.TodoDao
import com.example.todo.database.model.TodoModel

@Database(entities = [TodoModel::class], version = 2)
@TypeConverters(DateConverter::class)
abstract class TodoDatabase :RoomDatabase() {

    abstract fun getTodoDao():TodoDao
    companion object{
        private const val DATABASE_NAME = "Todos Database"
        private var todoDatabaseInstance : TodoDatabase? = null
        fun getInstance(context:Context):TodoDatabase{
            if (todoDatabaseInstance == null) {
                todoDatabaseInstance = Room.databaseBuilder(
                    context.applicationContext,
                    TodoDatabase::class.java,
                    DATABASE_NAME
                )
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return todoDatabaseInstance!!
        }
    }
}