package com.example.todo

import android.app.Application
import com.example.todo.database.TodoDatabase

class TodoApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        TodoDatabase.init(this)
    }
}