package com.android.todolist.cscs8

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.todolist.cscs8.adapter.TodoAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_first.*

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    // リストのアイテム
    private lateinit var list: ArrayList<String>

    // キーボード表示を制御するためのオブジェクト
    private lateinit var inputMethodManager: InputMethodManager

    // 背景のレイアウト
    private lateinit var mainLayout: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        // recycleViewの初期化
        recycleViewInit()

        // adapterの初期化
        onCreateAdapter()

        // 画面全体のレイアウト
        mainLayout = findViewById(R.id.coordinatorLayout)
        // キーボード表示制御
        inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager


        // Addボタン
        findViewById<Button>(R.id.buttonAdd).setOnClickListener {
            insertTodo()
        }


        // editTextからフォーカス変更された時のリスナー
        textInputLayout.editText?.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                // editTextにフォーカスがあれば特に処理なし
                return@setOnFocusChangeListener
            }
            //キーボード非表示
            inputMethodManager.hideSoftInputFromWindow(
                v.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )
        }

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        // 背景へフォーカスを移す
        mainLayout.requestFocus()

        return false
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

    /**
     * onCreateの中でadapterに関するもの.
     */
    private fun onCreateAdapter() {
        // インターフェースの実装
        val adapter: TodoAdapter = viewAdapter as TodoAdapter
        setOnItemRadioClickListener(adapter)
        setOnItemImageClickListener(adapter)
        setOnItemTextClickListener(adapter)
    }

    /**
     * adapterのsetOnItemImageClickListenerの実装.
     */
    private fun setOnItemImageClickListener(adapter: TodoAdapter) {
        adapter.setOnItemImageClickListener(object : TodoAdapter.OnItemImageClickListener {
            override fun onItemImageClickListener(holder: TodoAdapter.MyViewHolder) {
                changeStar(holder)
                // 背景へフォーカスを移す
                mainLayout.requestFocus()
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

    /**
     * adapterのsetOnItemRadioClickListenerの実装.
     */
    private fun setOnItemRadioClickListener(adapter: TodoAdapter) {
        adapter.setOnItemRadioClickListener(object : TodoAdapter.OnItemRadioClickListener {
            override fun onItemRadioClickListener(view: View, position: Int, clickedText: String) {
                deleteTodo(position)
                Toast.makeText(applicationContext, "${clickedText}を削除しました", Toast.LENGTH_LONG)
                    .show()
                // 背景へフォーカスを移す
                mainLayout.requestFocus()
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

    /**
     * adapterのsetOnItemTextClickListenerの実装.
     */
    private fun setOnItemTextClickListener(adapter: TodoAdapter) {
        adapter.setOnItemTextClickListener(object : TodoAdapter.OnItemTextClickListener {
            override fun onItemTextClickListener() {
                // 背景へフォーカスを移す
                mainLayout.requestFocus()
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
        if (list.size == 0) list.add("Todoを追加してください...")
        viewAdapter.notifyDataSetChanged()
    }


    /**
     * スター状態を変更する.
     */
    private fun changeStar(holder: TodoAdapter.MyViewHolder) {
        holder.imageButton.isActivated = !holder.imageButton.isActivated
        if (holder.imageButton.isActivated) {
            // 画像を変更 : オン状態へ
            holder.imageButton.setImageResource(android.R.drawable.btn_star_big_on)
            return
        }
        // 画像を変更 : オフ状態へ
        holder.imageButton.setImageResource(android.R.drawable.btn_star_big_off)
    }
}
