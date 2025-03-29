package com.example.travelworld.domain.repository

import androidx.compose.runtime.mutableStateListOf
import com.example.travelworld.domain.model.SubTask
import com.example.travelworld.domain.model.Task

/**
 * Repositorio simulado donde almacenamos y gestionamos las tareas y subtareas en memoria.
 * Usaremos en el futuro Room, Retrofit, etc.
 */
class TaskRepository {

    // Usamos una lista observable para que Compose detecte cambios.
    private val taskList = mutableStateListOf<Task>()

    init {
        // Aquí agregamos datos iniciales de ejemplo
        taskList.addAll(
            listOf(
                Task(
                    id = 1,
                    title = "Tarea Padre 1",
                    description = "Descripción de la tarea 1",
                    date = "2025-03-07",
                    isMandatory = true,
                    isCompleted = false,
                    subTasks = listOf(
                        SubTask(
                            id = 101,
                            parentTaskId = 1,
                            title = "Subtarea 1.1",
                            description = "Descripción subtarea 1.1",
                            date = "2025-03-07",
                            isMandatory = false
                        ),
                        SubTask(
                            id = 102,
                            parentTaskId = 1,
                            title = "Subtarea 1.2",
                            description = "Descripción subtarea 1.2",
                            date = "2025-03-08",
                            isMandatory = false
                        )
                    )
                ),
                Task(
                    id = 2,
                    title = "Tarea Padre 2",
                    description = "Descripción de la tarea 2",
                    date = "2025-03-08",
                    isMandatory = false,
                    isCompleted = false,
                )
            )
        )
    }

    fun getTasks(): List<Task> = taskList

    fun getTaskById(taskId: Int): Task? {

        //here will be your logic to find in the local database
        return taskList.find { it.id == taskId }
    }

    fun addTask(task: Task) {
        //add database add logic here
        taskList.add(task)
    }

    fun updateTask(updatedTask: Task) {
        val index = taskList.indexOfFirst { it.id == updatedTask.id }
        if (index != -1) {
            taskList[index] = updatedTask
        }
    }

    fun deleteTask(task: Task) {
        taskList.remove(task)
    }

    // CRUD de SubTasks
    fun addSubTask(parentTaskId: Int, subTask: SubTask) {
        val index = taskList.indexOfFirst { it.id == parentTaskId }
        if (index != -1) {
            val parentTask = taskList[index]
            val updatedSubTasks = parentTask.subTasks + subTask
            taskList[index] = parentTask.copy(subTasks = updatedSubTasks)
        }
    }

    fun updateSubTask(parentTaskId: Int, updatedSubTask: SubTask) {
        val index = taskList.indexOfFirst { it.id == parentTaskId }
        if (index != -1) {
            val parentTask = taskList[index]
            val updatedSubTasks = parentTask.subTasks.map {
                if (it.id == updatedSubTask.id) updatedSubTask else it
            }
            taskList[index] = parentTask.copy(subTasks = updatedSubTasks)
        }
    }

    fun deleteSubTask(parentTaskId: Int, subTask: SubTask) {
        val index = taskList.indexOfFirst { it.id == parentTaskId }
        if (index != -1) {
            val parentTask = taskList[index]
            val updatedSubTasks = parentTask.subTasks.filter { it.id != subTask.id }
            taskList[index] = parentTask.copy(subTasks = updatedSubTasks)
        }
    }
}