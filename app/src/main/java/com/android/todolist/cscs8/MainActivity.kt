package com.android.todolist.cscs8

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.todolist.cscs8.adapter.TodoAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_first.*

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var list: ArrayList<String>

    /**
     * recycleViewを初期化する.
     */
    private fun recycleViewInit() {

        /// 表示するテキスト配列を作る [テキスト0, テキスト1, ....]
        list = arrayListOf("テキスト1", "テキスト2", "テキスト3")
        viewAdapter = TodoAdapter(list)
        viewManager = LinearLayoutManager(this)
        recyclerView = findViewById<RecyclerView>(R.id.recyclerView).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        recycleViewInit()


        findViewById<Button>(R.id.button3).setOnClickListener {
            insertTodo()
        }

        // インターフェースの実装
        val adapter: TodoAdapter = viewAdapter as TodoAdapter
        adapter.setOnItemClickListener(object : TodoAdapter.OnItemClickListener {
            override fun onItemClickListener(view: View, position: Int, clickedText: String) {
                deleteTodo(position)
                Toast.makeText(applicationContext, "${clickedText}を削除しました", Toast.LENGTH_LONG)
                    .show()
            }

            override fun onItemClick(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                TODO("Not yet implemented")
            }
        })

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

    /**
     * Todoを追加する.
     */
    private fun insertTodo() {
        // EditTextへの参照を取得する
        val editText = textInputLayout.editText?.text
        // メッセージを取り出す
        val message = editText.toString()
        list.add(0, message)
        viewAdapter.notifyItemInserted(0)
    }

    /**
     * Todoを削除する.
     */
    private fun deleteTodo(position: Int) {
        list.removeAt(position)
        viewAdapter.notifyItemRemoved(position)
    }

    /**
     * Todoにスターをつける.
     */
    private fun addStar(position: Int) {
        // TODO:
    }
}
