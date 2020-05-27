package com.cscs8.todolist.database

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
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
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
        private const val SQL_CREATE_ENTRIES =
            "CREATE TABLE ${TaskReaderContract.Tasks.TABLE_NAME} (" +
                    "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                    "${TaskReaderContract.Tasks.COLUMN_NAME_CONTENT} TEXT," +
                    "${TaskReaderContract.Tasks.COLUMN_NAME_FAVORITE} INTEGER DEFAULT 0)"

        // DROP TABLEのSQL
        private const val SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS ${TaskReaderContract.Tasks.TABLE_NAME}"
    }
}