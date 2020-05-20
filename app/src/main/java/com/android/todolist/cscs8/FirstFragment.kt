package com.android.todolist.cscs8

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.todolist.cscs8.adapter.TodoAdapter
import kotlinx.android.synthetic.main.fragment_first.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button3).setOnClickListener {

            // EditTextへの参照を取得する
            val editText = textInputLayout.editText?.text
            // メッセージを取り出す
            val message = editText.toString()

//            viewManager = LinearLayoutManager(activity)
//            val array = Array<String>(10) { "test$it" }
//            array.plus(message)

//
//            viewAdapter = TodoAdapter(array)
//
//            recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView).apply {
//                // use this setting to improve performance if you know that changes
//                // in content do not change the layout size of the RecyclerView
//                setHasFixedSize(true)
//
//                // use a linear layout manager
//                layoutManager = viewManager
//
//                // specify an viewAdapter (see also next example)
//                adapter = viewAdapter
//
//            }
//            // Intentを作成する
//            val intent = Intent(context, ResultActivity::class.java)
//            // パラメータをセットする
//            intent.putExtra("message", message)
//            // 画面を遷移させる
//            startActivity(intent)
        }

//        recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView).apply {
//            // use this setting to improve performance if you know that changes
//            // in content do not change the layout size of the RecyclerView
//            setHasFixedSize(true)
//
//            // use a linear layout manager
//            layoutManager = viewManager
//
//            // specify an viewAdapter (see also next example)
//            adapter = viewAdapter
//
//        }
    }

//    fun insert(item: String) {
//        val adapter = recyclerView.adapter as TodoAdapter
//        adapter.notifyDataSetChanged()
//        recyclerView.adapter.notifyItemInserted()
//    }
}
