package com.cscs8.todolist.database

import android.content.ContentValues
import android.provider.BaseColumns
import android.util.Log

class SQLiteTaskRepository(private val helper: DatabaseHelper) : ITaskRepository {
    private val db = helper.writableDatabase

    override fun find(id: Long): Task? {
        TODO("Not yet implemented")
    }

    override fun findAll(): ArrayList<Task>? {
        val projection = arrayOf(BaseColumns._ID, TaskReaderContract.Tasks.COLUMN_NAME_CONTENT)
        // カーソル
        val cursor = db.query(
            TaskReaderContract.Tasks.TABLE_NAME,
            projection,
            null,
            null,
            null,
            null,
            null
        )
        val list: ArrayList<Task> = arrayListOf()
        with(cursor) {
            while (moveToNext()) {
                val id = getLong(getColumnIndexOrThrow(BaseColumns._ID))
                val content = getString(getColumnIndexOrThrow("content"))
                list.add(Task(id, content))
            }
        }
        if (list.isEmpty()) return null
        return list
    }

    override fun save(content: String): Long? {
        // Create a new map of values, where column names are the keys
        val values = ContentValues().apply {
            put(TaskReaderContract.Tasks.COLUMN_NAME_CONTENT, content)
        }

        // Insert the new row, returning the primary key value of the new row
        val newRowId = db.insert(TaskReaderContract.Tasks.TABLE_NAME, null, values)
        if (newRowId == -1L) {
            Log.d("", "タスクの追加に失敗しました.")
            return null
        }
        return newRowId

    }

    override fun update(task: Task): Long? {
        TODO("Not yet implemented")
    }

    override fun delete(id: Long) {
        val sql = "DELETE FROM tasks WHERE _id = ?"
        val stmt = db.compileStatement(sql)
        stmt.bindLong(1, id)
        stmt.executeUpdateDelete()
    }
}