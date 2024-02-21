package com.example.todo.taps.todoList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.todo.adapter.TodoListAdapter
import com.example.todo.databinding.FragmentTodosListBinding
import com.prolificinteractive.materialcalendarview.CalendarDay


class TodosListFragment : Fragment() {
    lateinit var viewBinding: FragmentTodosListBinding
    lateinit var viewModel: TodoListViewModel

    companion object {
        lateinit var adapter: TodoListAdapter
    }

    var selectedDate = CalendarDay.today()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        viewBinding = FragmentTodosListBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(TodoListViewModel::class.java)
        viewBinding.viewModel = viewModel
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = TodoListAdapter(null)
        viewBinding.calendarView.selectedDate = selectedDate
        viewModel.refreshTodos()
        viewBinding.calendarView.setOnDateChangedListener { widget, date, selected ->
            selectedDate = date
            viewModel.refreshTodos()
        }
        viewBinding.recyclerTodoList.adapter = adapter
        viewModel.refreshTodos()

    }


}