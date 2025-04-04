package com.example.travelworld.ui.viewModels

import androidx.lifecycle.ViewModel
import com.example.travelworld.domain.repository.TaskRepository
import com.example.travelworld.domain.model.SubTask

/**
 * ViewModel para la pantalla que maneja las subtareas de una tarea padre concreta.
 */
class SubTaskViewModel(
    private val repository: TaskRepository = TaskRepository()
) : ViewModel() {

    fun addSubTask(parentTaskId: Int, subTask: SubTask) {
        repository.addSubTask(parentTaskId, subTask)
    }

    fun updateSubTask(parentTaskId: Int, subTask: SubTask) {
        repository.updateSubTask(parentTaskId, subTask)
    }

    fun deleteSubTask(parentTaskId: Int, subTask: SubTask) {
        repository.deleteSubTask(parentTaskId, subTask)
    }
}
