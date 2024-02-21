package com.example.recyclerviewticktock

import android.content.Context
import android.net.Uri
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class TikTokRecyclerAdapter(private val context: Context, private val iconClickListener: OnIconClickListener): RecyclerView.Adapter<TikTokViewHolderItem>() {
    private val videoList = listOf(
        "https://test-pvg-video-contents-bucket.s3.ap-northeast-1.amazonaws.com/flower.mp4",
        "https://test-pvg-video-contents-bucket.s3.ap-northeast-1.amazonaws.com/test_30mb.mp4",
        "https://test-pvg-video-contents-bucket.s3.ap-northeast-1.amazonaws.com/pexels-bu%CC%88s%CC%A7ra-c%CC%A7akmak-20159065+(1080p).mp4",
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TikTokViewHolderItem {
        return TikTokViewHolderItem.create(parent)
    }

    override fun getItemCount(): Int {
        return videoList.size
    }

    override fun onBindViewHolder(holder: TikTokViewHolderItem, position: Int) {
        val videoPath = videoList[position]
        holder.bind(Uri.parse(videoPath), iconClickListener)
    }
}