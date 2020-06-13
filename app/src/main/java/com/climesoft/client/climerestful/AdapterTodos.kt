package com.climesoft.client.climerestful

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class AdapterTodos(
    private val context: Context,
    private val list: List<Todo>
) : RecyclerView.Adapter<AdapterTodos.TodoHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoHolder {
        val v: View = LayoutInflater.from(context).inflate(R.layout.item_list, parent, false)
        return TodoHolder(v)
    }
    override fun onBindViewHolder(holder: TodoHolder, position: Int) {
        val todo = list[position]
        holder.textTitle.text = todo.title
    }

    override fun getItemCount() = list.size

    inner class TodoHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textTitle: TextView = itemView.findViewById(R.id.txtTitle)
    }

}