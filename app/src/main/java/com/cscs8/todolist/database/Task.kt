package com.cscs8.todolist.database

data class Task(val id: Long, val content: String, val favorite: Boolean = false) {
}