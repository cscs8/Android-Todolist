package com.android.todolist.cscs8

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_first.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button3).setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)

            // EditTextへの参照を取得する
            val editText = textInputLayout.editText?.text
            // メッセージを取り出す
            val message = editText.toString()

            // Intentを作成する
            val intent = Intent(context, ResultActivity::class.java)
            // パラメータをセットする
            intent.putExtra("message", message)
            // 画面を遷移させる
            startActivity(intent)
        }
    }
}
