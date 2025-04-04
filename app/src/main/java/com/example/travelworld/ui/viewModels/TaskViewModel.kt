package com.example.travelworld.ui.viewModels

import androidx.lifecycle.ViewModel
import com.example.travelworld.domain.repository.TaskRepository
import com.example.travelworld.domain.model.Task

/**
 * ViewModel para la pantalla que lista las tareas padres.
 */
class TaskViewModel(
    private val repository: TaskRepository = TaskRepository()
) : ViewModel() {

    // Obtenemos la lista de tareas (observables gracias a la lista mutableStateListOf).
    val tasks: List<Task>
        get() = repository.getTasks()

    fun addTask(task: Task) {
        repository.addTask(task)
    }

    fun updateTask(task: Task) {
        repository.updateTask(task)
    }

    fun deleteTask(task: Task) {
        repository.deleteTask(task)
    }

    fun getTaskById(taskId: Int): Task? {
        return repository.getTaskById(taskId)
    }
}
