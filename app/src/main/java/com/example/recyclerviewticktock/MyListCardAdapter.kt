package com.example.recyclerviewticktock

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyListCardAdapter(var mList: List<CardDate>): RecyclerView.Adapter<MyListCardAdapter.MyListCardViewHolder>() {
    inner class  MyListCardViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val logo: ImageView = itemView.findViewById(R.id.logoIv)
//        val titleTv: TextView = itemView.findViewById(R.id.titleTv)
    }

    fun setFilterdList(mList: List<CardDate>){
        this.mList = mList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyListCardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.my_list_item, parent, false)
        return MyListCardViewHolder(view)
    }

    override fun getItemCount() = mList.size

    override fun onBindViewHolder(holder: MyListCardViewHolder, position: Int) {
       holder.logo.setImageResource(mList[position].logo)
//        holder.titleTv.text = mList[position].title
    }
}