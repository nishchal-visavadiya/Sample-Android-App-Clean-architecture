package com.simform.todo.presentation.ui.tasks

import androidx.lifecycle.*
import com.core.domain.Task.CompletionStatus
import com.core.domain.Task
import com.core.usecase.CompleteTask
import com.core.usecase.GetTasks
import com.core.usecase.MoveToBin
import com.simform.todo.presentation.utils.Event
import kotlinx.coroutines.launch

class TasksViewModel(
    getTasks: GetTasks,
    private val mCompleteTask: CompleteTask,
    private val mMoveToBin: MoveToBin
) : ViewModel() {

    val allTasks: LiveData<List<Task>> =
        getTasks.invoke().asLiveData()

    private val mTaskMoveToBin = MutableLiveData<Event<Task>>()
    val taskMovedToBin: LiveData<Event<Task>> get() = mTaskMoveToBin

    fun complete(task: Task, onSuccess: (Task) -> Unit) {
        if (task.completionStatus != CompletionStatus.InComplete) return
        viewModelScope.launch {
            mCompleteTask.invoke(task)
            onSuccess.invoke(task)
        }
    }

    fun moveToBin(task: Task) {
        viewModelScope.launch {
            mMoveToBin.invoke(task)
            mTaskMoveToBin.value = Event(task)
        }
    }
}