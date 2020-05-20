package com.android.todolist.cscs8.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.todolist.cscs8.R
import kotlinx.android.synthetic.main.recyclerview_item.view.*

//class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
//    val textView: TextView = view.findViewById(R.id.textView)
//}

class TodoAdapter(private val dataset: ArrayList<String>) :
    RecyclerView.Adapter<TodoAdapter.MyViewHolder>() {
    // リスナー格納変数
    private lateinit var listener: OnItemClickListener

    class MyViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val radio: RadioButton = view.radioButton
        val sampleImg: ImageView = view.sampleImg
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
        holder.sampleImg.setImageResource(R.mipmap.ic_star_gray_round)
        holder.sampleTxt.text = dataset[position]



        holder.radio.setOnClickListener {
            listener.onItemClickListener(it, position, dataset[position])
        }

        holder.sampleImg.setOnClickListener {
            listener.onItemClickListener(it, position, dataset[position])
        }
    }

    override fun getItemCount() = dataset.size

    //インターフェースの作成
    interface OnItemClickListener : AdapterView.OnItemClickListener {
        fun onItemClickListener(view: View, position: Int, clickedText: String)
    }

    // リスナー
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}