package com.example.recyclerviewticktock

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.VideoView
import androidx.recyclerview.widget.RecyclerView

class TikTokAdapter(
    var mList: List<CardDate>,
    private var context: Context
) :
    RecyclerView.Adapter<TikTokAdapter.TikTokViewHolder>() {
    inner class TikTokViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val logo: VideoView = itemView.findViewById(R.id.logoIv)
    }

    fun setFilterdList(mList: List<CardDate>) {
        this.mList = mList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TikTokViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tik_tok_item, parent, false)

        return TikTokViewHolder(view)
    }

    override fun getItemCount() = mList.size

    override fun onBindViewHolder(holder: TikTokViewHolder, position: Int) {
        val cardData = mList[position]
        // VideoView を取得し、動画をセット
        val videoView = holder.itemView.findViewById<VideoView>(R.id.logoIv)
        videoView.setVideoURI(Uri.parse(cardData.logo))
        // 動画再生を開始
        videoView.setOnPreparedListener { mp ->
            Log.d("###", "動画を再生する")
            Log.d("###", mp.toString())
            Log.d("###", Uri.parse(cardData.logo).toString())
            mp.start()
        }

        // アイコンのクリック処理を実装
        holder.itemView.findViewById<ImageView>(R.id.icon1).setOnClickListener {
            // ここにアイコン1のクリック処理を記述
            // 例えば、アイコン1がクリックされた時の処理を書く
            Log.d("TikTokAdapter", "アイコン1がクリックされました")
            // 人型アイコン
            println("アイコン1がクリックされました！aa")
            // マイリスト画面へ遷移する
            val intent = Intent(context, MyListActivity::class.java)
            context.startActivity(intent)
        }

        holder.itemView.findViewById<ImageView>(R.id.icon2).setOnClickListener {
            // ここにアイコン2のクリック処理を記述
            Log.d("TikTokAdapter", "アイコン2がクリックされました")
        }

        holder.itemView.findViewById<ImageView>(R.id.icon3).setOnClickListener {
            // ここにアイコン2のクリック処理を記述
            Log.d("TikTokAdapter", "アイコン3がクリックされました")
        }

        holder.itemView.findViewById<ImageView>(R.id.icon4).setOnClickListener {
            // ここにアイコン2のクリック処理を記述
            Log.d("TikTokAdapter", "アイコン4がクリックされました")
        }

        holder.itemView.findViewById<ImageView>(R.id.icon5).setOnClickListener {
            // ここにアイコン2のクリック処理を記述
            Log.d("TikTokAdapter", "アイコン5がクリックされました")
            println("アイコン5がクリックされました！")
            // 検索画面へ遷移させる
            val intent = Intent(context, SearchViewActivity::class.java)
            context.startActivity(intent)
        }

    }
}