package com.example.todo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.todo.R
import com.example.todo.database.model.TodoModel
import com.example.todo.databinding.ItemTodoBinding

class TodoListAdapter(var todoList: List<TodoModel>? = null) :
    Adapter<TodoListAdapter.TodoListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoListViewHolder {
        val itemTodoBinding =
            ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent     , false)
        return TodoListViewHolder(itemTodoBinding)
    }

    override fun getItemCount(): Int {
        return todoList?.size ?: 0
    }

    override fun onBindViewHolder(holder: TodoListViewHolder, position: Int) {
        val item = todoList?.get(position)
        holder.bind(item ?: return)

    }

    fun updateData(todoList: List<TodoModel>?) {
        this.todoList = todoList
        notifyDataSetChanged()
    }

    class TodoListViewHolder(val itemTodoBinding: ItemTodoBinding) :
        ViewHolder(itemTodoBinding.root) {
        fun bind(todoModel: TodoModel) {
            itemTodoBinding.todoItem = todoModel
            itemTodoBinding.invalidateAll()
            itemTodoBinding.executePendingBindings()
        }
    }


}