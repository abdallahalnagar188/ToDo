package com.example.todo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todo.database.dao.TodoDao
import com.example.todo.database.model.TodoModel

@Database(entities = [TodoModel::class], version = 1)
abstract class TodoDatabase private constructor():RoomDatabase() {

    abstract fun getTodoDao():TodoDao
    companion object{
        private const val DATABASE_NAME = "Todos Database"
        private var todoDatabaseInstance : TodoDatabase? = null
        fun getInstance(context:Context):TodoDatabase{
            if (todoDatabaseInstance == null)
                todoDatabaseInstance = Room.databaseBuilder(
                    context.applicationContext,
                    TodoDatabase::class.java,
                    DATABASE_NAME
                )
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            return todoDatabaseInstance!!
        }
    }

}