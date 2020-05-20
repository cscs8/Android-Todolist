package com.cscs8.todolist.database

class SQLiteTaskRepository(private val helper: DatabaseHelper) : ITaskRepository {
    private val db = helper.writableDatabase
    override fun findMaxId(): Long {
        val sqlSelectMaxId = "SELECT MAX(_id) as _id FROM tasks"
        // カーソル
        val cursor = db.rawQuery(sqlSelectMaxId, null)
        if (cursor.moveToNext()) {
            val idxId = cursor.getColumnIndex("_id")
            val id = cursor.getLong(idxId)
            return id
        }
        return 1
    }

    override fun find(id: Long): Task? {
        TODO("Not yet implemented")
    }

    override fun findAll(): ArrayList<Task>? {
        val sql = "SELECT _id, content FROM tasks ORDER BY _id"
        // カーソル
        val cursor = db.rawQuery(sql, null)
        val list: ArrayList<Task> = arrayListOf()
        while (cursor.moveToNext()) {
            val idxId = cursor.getColumnIndex("_id")
            val idxContent = cursor.getColumnIndex("content")
            val id = cursor.getLong(idxId)
            val content = cursor.getString(idxContent)
            list.plus(Task(id, content))
        }
        if (list.isEmpty()) return null
        return list
    }

    override fun save(content: String): Long {
        val maxId = findMaxId()
        val sqlInsert = "INSERT INTO tasks (_id, content) VALUES(?, ?)"
        val stmt = db.compileStatement(sqlInsert)
        stmt.bindLong(1, maxId + 1)
        stmt.bindString(2, content)
        stmt.executeInsert()
        return maxId
    }

    override fun delete(id: Long) {
        val sql = "DELETE FROM tasks WHERE _id = ?"
        val stmt = db.compileStatement(sql)
        stmt.bindLong(1, id)
        stmt.executeUpdateDelete()
    }
}