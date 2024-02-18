package com.example.recyclerviewticktock

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CardAdapter(var mList: List<CardDate>): RecyclerView.Adapter<CardAdapter.CardViewHolder>() {
    inner class  CardViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val logo: ImageView = itemView.findViewById(R.id.logoIv)
        val titleTv: TextView = itemView.findViewById(R.id.titleTv)
    }

    fun setFilterdList(mList: List<CardDate>){
        this.mList = mList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.each_item, parent, false)
        return CardViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
       holder.logo.setImageResource(mList[position].logo)
        holder.titleTv.text = mList[position].title
    }
}