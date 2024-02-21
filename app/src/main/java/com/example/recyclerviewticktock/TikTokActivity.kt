package com.example.recyclerviewticktock

import DownloadMp4Manager
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewticktock.databinding.ActivityTikTokBinding


class TikTokActivity : AppCompatActivity() {
    private lateinit var tikTokRecyclerView: RecyclerView

    private val downloadManager = DownloadMp4Manager(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityTikTokBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //動画をダウンロードしてくる
        val videoUrl1 = "https://test-pvg-video-contents-bucket.s3.ap-northeast-1.amazonaws.com/flower.mp4"
        val videoUrl2 = "https://test-pvg-video-contents-bucket.s3.ap-northeast-1.amazonaws.com/flower.mp4"
        val videoUrl3 = "https://test-pvg-video-contents-bucket.s3.ap-northeast-1.amazonaws.com/flower.mp4"
        val videoUrl4 = "https://test-pvg-video-contents-bucket.s3.ap-northeast-1.amazonaws.com/flower.mp4"
        val videoUrl5 = "https://test-pvg-video-contents-bucket.s3.ap-northeast-1.amazonaws.com/flower.mp4"
        val videoUrl6 = "https://test-pvg-video-contents-bucket.s3.ap-northeast-1.amazonaws.com/flower.mp4"
        val fileName = "video.mp4"
        downloadManager.downloadVideo(videoUrl1, fileName,
            onSuccess = {
                runOnUiThread {
                    println("動画をダウンロードしました")

                    val videoPath = downloadManager.getVideoFilePath()
                    val videoUri = Uri.parse(videoPath)
                    println("videoUriのURL$videoUri")
                }
            },
            onFailure = {
                it.printStackTrace()
            }
        )

        tikTokRecyclerView = binding.tiktokRecyclerView
        tikTokRecyclerView.adapter = TikTokRecyclerAdapter(this, object : OnIconClickListener {
            override fun onIconClick(v: View) {
                // アイコンがクリックされたときの処理
                when (v.id) {
                    R.id.icon1 -> {
                        // 人型アイコン
                        println("アイコン1がクリックされました！aa")
                        // マイリスト画面へ遷移する
                        val intent = Intent(this@TikTokActivity, MyListActivity::class.java)
                        startActivity(intent)
                    }

                    R.id.icon2 -> {
                        // スターアイコン
                        println("アイコン2がクリックされました！")

                    }

                    R.id.icon3 -> {
                        // グッドアイコン

                    }

                    R.id.icon4 -> {
                        // バッドアイコン
                    }

                    R.id.icon5 -> {
                        // サーチアイコン
                        println("アイコン5がクリックされました！")
                        // 検索画面へ遷移させる
                        val intent = Intent(this@TikTokActivity, SearchViewActivity::class.java)
                        startActivity(intent)
                    }
                }
            }
        })

        // 一つのアイテムごとに停止
        val snapHelper = PagerSnapHelper()
        tikTokRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        snapHelper.attachToRecyclerView(tikTokRecyclerView)
    }
}

interface OnIconClickListener {
    fun onIconClick(v: View)
}