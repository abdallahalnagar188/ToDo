package com.example.todo.taps.todoList

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import com.example.todo.database.TodoDatabase
import com.prolificinteractive.materialcalendarview.CalendarDay
import java.util.Calendar

class TodoListViewModel:ViewModel() {
    var selectedDate = CalendarDay.today()

    @SuppressLint("NotifyDataSetChanged")
    fun refreshTodos() {
        val calendarDate = Calendar.getInstance()
        calendarDate.set(selectedDate.year,selectedDate.month-1,selectedDate.day)
        calendarDate.clear(Calendar.HOUR)
        calendarDate.clear(Calendar.MINUTE)
        calendarDate.clear(Calendar.SECOND)
        calendarDate.clear(Calendar.MILLISECOND)
        val todo = TodoDatabase
            .getInstance().getTodoDao().getTodosByDate(calendarDate.time)
        TodosListFragment.adapter.todoList = todo
        TodosListFragment.adapter.notifyDataSetChanged()
    }
}