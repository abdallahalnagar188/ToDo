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

class TodoListAdapter( var todoList: List<TodoModel>? = null):
    Adapter<TodoListAdapter.TodoListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_todo,parent,false)
        return TodoListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return todoList?.size?:0
    }

    override fun onBindViewHolder(holder: TodoListViewHolder, position: Int) {
        val item = todoList?.get(position)
        holder.title.text = item?.title
        holder.time.text = item?.time.toString()

    }
    fun updateData(todoList: List<TodoModel>?){
        this.todoList= todoList
        notifyDataSetChanged()
    }

    class TodoListViewHolder(view: View) : ViewHolder(view){
        val title :TextView = view.findViewById(R.id.todo_title)
        val time :TextView = view.findViewById(R.id.todo_time_tv)
        val imageCheck :ImageView = view.findViewById(R.id.todo_check)
    }


}