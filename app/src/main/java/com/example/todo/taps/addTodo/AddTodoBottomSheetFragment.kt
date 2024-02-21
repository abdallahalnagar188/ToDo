package com.example.todo.taps.addTodo

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.todo.R
import com.example.todo.database.TodoDatabase
import com.example.todo.database.model.TodoModel
import com.example.todo.databinding.FragmentAddTodoBottomSheetBinding
import com.example.todo.taps.todoList.TodosListFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.Calendar

class AddTodoBottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentAddTodoBottomSheetBinding
    lateinit var calendar: Calendar
    lateinit var viewModel:AddTodoViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentAddTodoBottomSheetBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(AddTodoViewModel::class.java)
        binding.viewModel = viewModel
        return binding.root
    }

    @SuppressLint("SetTextI18n", "NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        calendar = Calendar.getInstance()
        binding.selectTimeValue.text =
            "${calendar.get(Calendar.DAY_OF_MONTH)}/ " + "${calendar.get(Calendar.MONTH) + 1}/" + "${
                calendar.get(Calendar.YEAR)
            }"
        binding.selectTimeValue.setOnClickListener {
            showDatePicker()
        }
        binding.btnAddTodo.setOnClickListener {
            viewModel.addTodoToDatabase(calendar = calendar) {
                TodosListFragment.adapter.notifyDataSetChanged()
                dismiss()
            }
        }
        subscribeToLiveData()
    }

    private fun subscribeToLiveData() {
        viewModel.title.observe(this,object :Observer<String>{
            override fun onChanged(value: String) {
                Log.e("TAG","onChanged: $value")
            }

        })
    }



    @SuppressLint("SetTextI18n")
    private fun showDatePicker() {
        val datePicker = DatePickerDialog(requireContext())
        datePicker.show()
        datePicker.setOnDateSetListener { view, year, month, dayOfMonth ->
            binding.selectTimeValue.text = "$dayOfMonth/${month + 1}/$year"
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        }
    }
}