package com.android.todolist.cscs8.database

interface ITaskRepository {
    fun findMaxId(): Long
    fun find(id: Long): Task?
    fun findAll(): ArrayList<Task>?
    fun save(content: String): Long
    fun delete(id: Long)
}