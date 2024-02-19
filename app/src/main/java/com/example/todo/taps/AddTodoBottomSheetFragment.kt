package com.example.todo.taps

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todo.R
import com.example.todo.database.TodoDatabase
import com.example.todo.database.model.TodoModel
import com.example.todo.databinding.FragmentAddTodoBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.Calendar

class AddTodoBottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentAddTodoBottomSheetBinding
    lateinit var calendar: Calendar
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentAddTodoBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
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
            addTodoToDatabase()
        }
    }

    private fun validateFields(): Boolean {
        var validate = true
        if (
            binding.titleEditText.text.toString().isEmpty() ||
            binding.titleEditText.text.toString().isBlank()
        ) {
            binding.titleEditText.error = getString(R.string.title_required)
            validate = false
        }
        if (
            binding.descriptionEditText.text.toString().isEmpty() ||
            binding.descriptionEditText.text.toString().isBlank()
        ) {
            binding.descriptionEditText.error = getString(R.string.description_required)
            validate = false
        }
        return validate
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun addTodoToDatabase() {
        calendar.clear(Calendar.HOUR)
        calendar.clear(Calendar.MINUTE)
        calendar.clear(Calendar.SECOND)
        calendar.clear(Calendar.MILLISECOND)
        if (validateFields()) {
            val title = binding.titleEditText.text.toString()
            val description = binding.descriptionEditText.text.toString()
            TodoDatabase.getInstance(requireContext()).getTodoDao().insertTodo(
                TodoModel(
                    title = title,
                    description = description,
                    isDone = false,
                    time = calendar.time
                )
            )
//            Log.e("calendar",calendar.time.toString())
            TodosListFragment.adapter.notifyDataSetChanged()
            dismiss()
        }
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