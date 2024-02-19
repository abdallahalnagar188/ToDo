package com.example.todo.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.todo.database.model.TodoModel
import java.util.Date

@Dao
interface TodoDao {
    @Insert
    fun insertTodo(todo:TodoModel)
    @Delete
    fun deleteTodo(todo:TodoModel)
    @Update
    fun updateTodo(todo:TodoModel)
    @Query("Select * From TodoModel")
    fun getAllTodos() : List<TodoModel>
    @Query("Select * From TodoModel where time = :date")
    fun getTodosByDate(date: Date) : List<TodoModel>
}