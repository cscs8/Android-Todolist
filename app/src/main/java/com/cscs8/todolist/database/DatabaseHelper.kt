package com.cscs8.todolist.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * データベースヘルパー.
 * TOTO: あとでROOMにする.
 */
class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        // テーブル作成用SQL文字列の作成
        val sql = """CREATE TABLE tasks (
            _id INTEGER PRIMARY KEY,
            content TEXT
        );
        """.trimIndent()
        db.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    // クラス名のprivate定数を制限するためにcompanion objectブロックとする
    companion object {
        // DBファイル名の定数フィールド
        private const val DATABASE_NAME = "cscs8todoandroid.db"

        // バージョン情報の定数フィールド
        private const val DATABASE_VERSION = 1
    }
}