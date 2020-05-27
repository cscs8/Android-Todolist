package com.cscs8.todolist.database

interface ITaskRepository {
    fun find(id: Long): Task?
    fun findAll(): ArrayList<Task>?
    fun save(content: String): Long?
    fun update(task: Task): Long?
    fun delete(id: Long)
}