package com.example.todo.taps.addTodo

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todo.database.TodoDatabase
import com.example.todo.database.model.TodoModel
import java.util.Calendar

class AddTodoViewModel : ViewModel() {

    val title = MutableLiveData<String>()
    val titleError = MutableLiveData<String>()
    val description = MutableLiveData<String>()
    val descriptionError = MutableLiveData<String>()
    fun validateFields(): Boolean {
        var validate = true
        if (
            title.value.isNullOrEmpty() ||
            title.value.isNullOrBlank()
        ) {
            titleError.value = "Title Required"
            validate = false
        }
        if (
            description.value.isNullOrEmpty() ||
            description.value.isNullOrEmpty()
        ) {
            descriptionError.value = "Description Required"
            validate = false
        }
        return validate
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addTodoToDatabase(calendar: Calendar, onTodoAddedSuccess: () -> Unit) {
        calendar.clear(Calendar.HOUR)
        calendar.clear(Calendar.MINUTE)
        calendar.clear(Calendar.SECOND)
        calendar.clear(Calendar.MILLISECOND)
        if (validateFields()) {
            TodoDatabase.getInstance()
                .getTodoDao()
                .insertTodo(
                    TodoModel(
                        title = title.value,
                        description = description.value,
                        isDone = false,
                        time = calendar.time
                    )
                )
            onTodoAddedSuccess()
        }
    }
}