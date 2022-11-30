package com.simform.todo.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.core.domain.Task
import com.simform.todo.databinding.ItemTaskListBinding

class TaskListAdapter(
    private val mItemClickListener: (Task) -> Unit
) : RecyclerView.Adapter<TaskListAdapter.ViewHolder>() {

    private val mTasks = mutableListOf<Task>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemViewBinding =
            ItemTaskListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemViewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task = mTasks[position]
        holder.binding.task = task
        holder.binding.root.setOnClickListener {
            mItemClickListener.invoke(task)
        }
    }

    override fun getItemCount(): Int = mTasks.size

    @SuppressLint("NotifyDataSetChanged")
    fun addAll(tasks: List<Task>) = with(mTasks) {
        clear()
        addAll(tasks)
        notifyDataSetChanged()
    }

    fun deleteItemAt(position: Int): Task =
        mTasks.removeAt(position).also { notifyItemRemoved(position) }

    fun getItemAt(position: Int): Task = mTasks[position]

    inner class ViewHolder(val binding: ItemTaskListBinding) :
        RecyclerView.ViewHolder(binding.root)
}