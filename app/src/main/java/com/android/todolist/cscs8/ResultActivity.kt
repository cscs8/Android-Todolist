package com.android.todolist.cscs8

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        // パラメータを取得する
        val message = intent.getStringExtra("message")
        // TextViewへの参照を取得する
        val textView = findViewById<TextView>(R.id.textView)
        // TextViewにテキストを設定する
        textView.text = message
    }
}
