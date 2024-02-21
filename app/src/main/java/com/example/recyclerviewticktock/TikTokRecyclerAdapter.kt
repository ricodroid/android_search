package com.example.recyclerviewticktock

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class TikTokRecyclerAdapter(private val context: Context, private val iconClickListener: OnIconClickListener): RecyclerView.Adapter<TikTokViewHolderItem>() {
    private val videoList = listOf(
        "https://test-pvg-video-contents-bucket.s3.ap-northeast-1.amazonaws.com/Video+MP4_Moon+-+testfile.org.mp4",
        "https://test-pvg-video-contents-bucket.s3.ap-northeast-1.amazonaws.com/Video+MP4_Moon+-+testfile.org.mp4",
        "https://test-pvg-video-contents-bucket.s3.ap-northeast-1.amazonaws.com/Video+MP4_Moon+-+testfile.org.mp4",
        "https://test-pvg-video-contents-bucket.s3.ap-northeast-1.amazonaws.com/Video+MP4_Moon+-+testfile.org.mp4",
        "https://test-pvg-video-contents-bucket.s3.ap-northeast-1.amazonaws.com/Video+MP4_Moon+-+testfile.org.mp4",
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TikTokViewHolderItem {
        return TikTokViewHolderItem.create(parent)
    }

    override fun getItemCount(): Int {
        return videoList.size
    }

    override fun onBindViewHolder(holder: TikTokViewHolderItem, position: Int) {
        val videoPath = videoList[position]
        holder.bind(videoPath, iconClickListener)
    }
}