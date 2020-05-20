package com.android.todolist.cscs8.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.todolist.cscs8.R
import kotlinx.android.synthetic.main.recyclerview_item.view.*

//class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
//    val textView: TextView = view.findViewById(R.id.textView)
//}

class TodoAdapter(private val dataset: Array<String>) :
    RecyclerView.Adapter<TodoAdapter.MyViewHolder>() {
    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val sampleImg = view.sampleImg
        val sampleTxt = view.sampleTxt
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
//        val textView = LayoutInflater.from(parent.context)
//            .inflate(R.layout.recyclerview_item, parent, false) as TextView
//        return MyViewHolder(textView)
        val textView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_item, parent, false)
        return MyViewHolder(textView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.sampleImg.setImageResource(R.mipmap.ic_launcher_round)
        holder.sampleTxt.text = dataset[position]
    }

    override fun getItemCount() = dataset.size
}