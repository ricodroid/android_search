package com.example.recyclerviewticktock

import android.content.Context
import android.net.Uri
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class TikTokRecyclerAdapter(private val context: Context, private val iconClickListener: OnIconClickListener): RecyclerView.Adapter<TikTokViewHolderItem>() {
    private val videoList = getAllVideoFilePaths()

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

    private fun getAllVideoFilePaths(): List<String> {
        val videoDir = context.filesDir // 動画ファイルが保存されているディレクトリ
        val videoFiles = videoDir.listFiles { file -> file.extension == "mp4" } // mp4形式の動画ファイルのみを取得

        return videoFiles?.map { it.absolutePath } ?: emptyList() // ファイルの絶対パスのリストを返す。nullの場合は空のリストを返す
    }
}