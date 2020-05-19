package com.android.todolist.cscs8

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        // Buttonへの参照を取得する
        val button = findViewById<Button>(R.id.button3)
        // ボタンタップ時の処理を指定する
        button.setOnClickListener {
            // EditTextへの参照を取得する
            val editText = findViewById<EditText>(R.id.textInputLayout)
            // メッセージを取り出す
            val message = editText.text.toString()

            // Intentを作成する
            val intent = Intent(this, ResultActivity::class.java)
            // パラメータをセットする
            intent.putExtra("message", message)
            // 画面を遷移させる
            startActivity(intent)

//            // パラメータを取得する
//            val message = intent.getStringExtra("message")
        }
//        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()
//        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
