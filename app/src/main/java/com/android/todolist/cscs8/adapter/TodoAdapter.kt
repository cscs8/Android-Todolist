package com.android.todolist.cscs8.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageButton
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.todolist.cscs8.R
import kotlinx.android.synthetic.main.recyclerview_item.view.*

class TodoAdapter(private val dataset: ArrayList<String>) :
    RecyclerView.Adapter<TodoAdapter.MyViewHolder>() {
    // リスナー格納変数
    private lateinit var radioListener: OnItemRadioClickListener
    private lateinit var imageListener: OnItemImageClickListener

    class MyViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val radio: RadioButton = view.radioButton
        val imageButton: ImageButton = view.imageButton
        val sampleTxt: TextView = view.sampleTxt
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.radio.isChecked = false
        holder.imageButton.setImageResource(android.R.drawable.btn_star_big_off)
        holder.sampleTxt.text = dataset[position]

        holder.radio.setOnClickListener {
            radioListener.onItemRadioClickListener(it, position, dataset[position])
        }

        holder.imageButton.setOnClickListener {
            imageListener.onItemImageClickListener(holder)
        }
    }

    override fun getItemCount() = dataset.size

    //インターフェースの作成
    interface OnItemRadioClickListener : AdapterView.OnItemClickListener {
        fun onItemRadioClickListener(view: View, position: Int, clickedText: String)
    }

    // リスナー
    fun setOnItemRadioClickListener(listener: OnItemRadioClickListener) {
        this.radioListener = listener
    }

    //インターフェースの作成
    interface OnItemImageClickListener : AdapterView.OnItemClickListener {
        fun onItemImageClickListener(holder: MyViewHolder)
    }

    // リスナー
    fun setOnItemImageClickListener(listener: OnItemImageClickListener) {
        this.imageListener = listener
    }

}