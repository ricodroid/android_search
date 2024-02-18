package com.example.recyclerviewticktock

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TagAdapter(var mList: List<TagDate>): RecyclerView.Adapter<TagAdapter.TagHolder>() {
    inner class  TagHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tag: TextView = itemView.findViewById(R.id.tag)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tag_item, parent, false)
        return TagHolder(view)
    }

    override fun getItemCount() = mList.size

    override fun onBindViewHolder(holder: TagHolder, position: Int) {
        holder.tag.text = mList[position].tag
    }
}