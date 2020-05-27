package com.cscs8.todolist.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

/**
 * データベースヘルパー.
 * TOTO: あとでROOMにする.
 */
class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_TASKS)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        if (oldVersion != 1) return

        val projection = arrayOf(
            BaseColumns._ID,
            TaskReaderContract.Tasks.COLUMN_NAME_CONTENT
        )
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
                val content =
                    getString(getColumnIndexOrThrow(TaskReaderContract.Tasks.COLUMN_NAME_CONTENT))
                list.add(Task(id, content))
            }
        }

        db.execSQL(SQL_DELETE_TASKS)
        onCreate(db)


        for (task in list) {
            val values = ContentValues().apply {
                put(BaseColumns._ID, task.id)
                put(TaskReaderContract.Tasks.COLUMN_NAME_CONTENT, task.content)
            }
            db.insert(TaskReaderContract.Tasks.TABLE_NAME, null, values)
        }
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    // クラス名のprivate定数を制限するためにcompanion objectブロックとする
    companion object {
        // DBファイル名の定数フィールド
        private const val DATABASE_NAME = "cscs8todoandroid.db"

        // バージョン情報の定数フィールド
        private const val DATABASE_VERSION = 2

        // CREATE TABLEのSQL
        private const val SQL_CREATE_TASKS =
            "CREATE TABLE ${TaskReaderContract.Tasks.TABLE_NAME} (" +
                    "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                    "${TaskReaderContract.Tasks.COLUMN_NAME_CONTENT} TEXT," +
                    "${TaskReaderContract.Tasks.COLUMN_NAME_FAVORITE} INTEGER DEFAULT 0)"

        // DROP TABLEのSQL
        private const val SQL_DELETE_TASKS =
            "DROP TABLE IF EXISTS ${TaskReaderContract.Tasks.TABLE_NAME}"

        // old version 1からのupgrade用SQL
        private const val SQL_UPGRADE_TASKS_OLD_1 =
            "ALTER TABLE ${TaskReaderContract.Tasks.TABLE_NAME} ADD COLUMN" +
                    "${TaskReaderContract.Tasks.COLUMN_NAME_FAVORITE} INTEGER DEFAULT 0"
    }
}