package com.raywenderlich.retrofittutorial

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.raywenderlich.retrofittutorial.databinding.ItemNoteBinding

class TodoAdaptor: RecyclerView.Adapter<TodoAdaptor.TodoAdaptorViewHolder>() {

    inner class TodoAdaptorViewHolder(val binding:ItemNoteBinding): RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object: DiffUtil.ItemCallback<Todo>(){
        override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return newItem.id == oldItem.id
        }

        override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
           return newItem == oldItem
        }

    }

    private val differ = AsyncListDiffer(this,diffCallback)

    var todos: List<Todo>
    get() = differ.currentList
    set(value) {
        differ.submitList(value)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoAdaptorViewHolder {
        return TodoAdaptorViewHolder(ItemNoteBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: TodoAdaptorViewHolder, position: Int) {
        holder.binding.apply {
            val todo = todos[position]
            checkBox.isChecked = todos[position].completed
            textView.text = todos[position].title
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }
}