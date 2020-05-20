package com.android.todolist.cscs8.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.todolist.cscs8.R
import kotlinx.android.synthetic.main.recyclerview_item.view.*

//class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
//    val textView: TextView = view.findViewById(R.id.textView)
//}

class TodoAdapter(private val dataset: ArrayList<String>) :
    RecyclerView.Adapter<TodoAdapter.MyViewHolder>() {
    class MyViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
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
        holder.sampleImg.setImageResource(R.mipmap.ic_star_gray_round)
        holder.sampleTxt.text = dataset[position]
    }

    override fun getItemCount() = dataset.size
}