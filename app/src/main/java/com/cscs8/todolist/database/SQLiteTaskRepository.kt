package com.cscs8.todolist.database

import android.content.ContentValues
import android.provider.BaseColumns
import android.util.Log

class SQLiteTaskRepository(private val helper: DatabaseHelper) : ITaskRepository {
    private val db = helper.writableDatabase

    // Boolean拡張プロパティ
    private val Boolean?.int
        get() = if (this != null && this) 1 else 0

    // Int拡張プロパティ
    private val Int?.boolean
        get() = (this != null && this > 0)

    override fun find(id: Long): Task? {
        TODO("Not yet implemented")
    }

    override fun findAll(): ArrayList<Task>? {
        val projection = arrayOf(
            BaseColumns._ID,
            TaskReaderContract.Tasks.COLUMN_NAME_CONTENT,
            TaskReaderContract.Tasks.COLUMN_NAME_FAVORITE
        )
        // カーソル
        val cursor = db.query(
            TaskReaderContract.Tasks.TABLE_NAME,
            projection,
            null,
            null,
            null,
            null,
           "${BaseColumns._ID} DESC"
        )
        val list: ArrayList<Task> = arrayListOf()
        with(cursor) {
            while (moveToNext()) {
                val id = getLong(getColumnIndexOrThrow(BaseColumns._ID))
                val content =
                    getString(getColumnIndexOrThrow(TaskReaderContract.Tasks.COLUMN_NAME_CONTENT))
                val favorite =
                    getInt(getColumnIndexOrThrow(TaskReaderContract.Tasks.COLUMN_NAME_FAVORITE))
                list.add(Task(id, content, favorite.boolean))
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

    override fun update(task: Task): Int {
        // Create a new map of values, where column names are the keys
        val values = ContentValues().apply {
            put(TaskReaderContract.Tasks.COLUMN_NAME_CONTENT, task.content)
            put(TaskReaderContract.Tasks.COLUMN_NAME_FAVORITE, task.favorite.int)
        }

        val selection = "${BaseColumns._ID} = ?"
        val selectionArgs = arrayOf(task.id.toString())

        // Insert the new row, returning the primary key value of the new row
        val count =
            db.update(TaskReaderContract.Tasks.TABLE_NAME, values, selection, selectionArgs)
        if (count <= 0) Log.d("", "タスクの変更に失敗しました.")
        return count
    }

    override fun delete(id: Long): Int {
        val selection = "${BaseColumns._ID} = ?"
        val selectionArgs = arrayOf(id.toString())
        val deleteRows = db.delete(TaskReaderContract.Tasks.TABLE_NAME, selection, selectionArgs)
        if (deleteRows <= 0) Log.d("", "タスクの削除に失敗しました.")
        return deleteRows
    }
}