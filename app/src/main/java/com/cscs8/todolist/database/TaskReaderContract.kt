package com.cscs8.todolist.database

import android.provider.BaseColumns

object TaskReaderContract {
    object Tasks : BaseColumns {
        const val TABLE_NAME = "tasks"
        const val COLUMN_NAME_CONTENT = "content"
        const val COLUMN_NAME_FAVORITE = "favorite"
    }
}