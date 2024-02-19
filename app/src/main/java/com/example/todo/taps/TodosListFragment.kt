package com.example.todo.taps

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.todo.adapter.TodoListAdapter
import com.example.todo.database.TodoDatabase
import com.example.todo.databinding.FragmentTodosListBinding
import com.prolificinteractive.materialcalendarview.CalendarDay
import java.util.Calendar


class TodosListFragment : Fragment() {
    lateinit var viewBinding: FragmentTodosListBinding

    companion object {
        lateinit var  adapter :TodoListAdapter
    }

    var selectedDate = CalendarDay.today()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        viewBinding = FragmentTodosListBinding.inflate(inflater,container,false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = TodoListAdapter(null)
        viewBinding.calendarView.selectedDate = selectedDate
        refreshTodos()
        viewBinding.calendarView.setOnDateChangedListener { widget, date, selected ->
            selectedDate = date
            refreshTodos()
        }
        viewBinding.recyclerTodoList.adapter = adapter
        refreshTodos()
//        val items = TodoDatabase.getInstance(requireContext()).getTodoDao().getAllTodos()
//        adapter.updateData(items)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun refreshTodos() {
        val calendarDate = Calendar.getInstance()
        calendarDate.set(selectedDate.year,selectedDate.month-1,selectedDate.day)
        calendarDate.clear(Calendar.HOUR)
        calendarDate.clear(Calendar.MINUTE)
        calendarDate.clear(Calendar.SECOND)
        calendarDate.clear(Calendar.MILLISECOND)
        val todo = TodoDatabase
            .getInstance(requireContext()).getTodoDao().getTodosByDate(calendarDate.time)
        adapter.todoList = todo
        adapter.notifyDataSetChanged()
    }

}