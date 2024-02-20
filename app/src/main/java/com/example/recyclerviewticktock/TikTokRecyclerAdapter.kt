package com.example.recyclerviewticktock

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class TikTokRecyclerAdapter(private val context: Context): RecyclerView.Adapter<TikTokViewHolderItem>() {
    private val videoList = listOf(
        "android.resource://" + context.packageName + "/" + R.raw.move1,
        "android.resource://" + context.packageName + "/" + R.raw.move2,
        "android.resource://" + context.packageName + "/" + R.raw.move3,
        "android.resource://" + context.packageName + "/" + R.raw.move4,
        "android.resource://" + context.packageName + "/" + R.raw.move5,
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TikTokViewHolderItem {
        return TikTokViewHolderItem.create(parent)
    }

    override fun getItemCount(): Int {
        return videoList.size
    }

    override fun onBindViewHolder(holder: TikTokViewHolderItem, position: Int) {
        val videoPath = videoList[position]
        holder.bind(videoPath)
    }
}