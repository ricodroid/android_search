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
    private var videoManager = VideoInfoManager()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityTikTokBinding.inflate(layoutInflater)
        setContentView(binding.root)



        videoManager = VideoInfoManager()

        videoManager.addVideoInfo("https://test-pvg-video-contents-bucket.s3.ap-northeast-1.amazonaws.com/pexels-bu%CC%88s%CC%A7ra-c%CC%A7akmak-20159065+(1080p).mp4", "video2.mp4")
        videoManager.addVideoInfo("https://test-pvg-video-contents-bucket.s3.ap-northeast-1.amazonaws.com/pexels-bu%CC%88s%CC%A7ra-c%CC%A7akmak-20159065+(1080p).mp4", "video3.mp4")
        videoManager.addVideoInfo("https://test-pvg-video-contents-bucket.s3.ap-northeast-1.amazonaws.com/pexels-bu%CC%88s%CC%A7ra-c%CC%A7akmak-20159065+(1080p).mp4", "video4.mp4")
        videoManager.addVideoInfo("https://test-pvg-video-contents-bucket.s3.ap-northeast-1.amazonaws.com/flower.mp4", "video5.mp4")
        videoManager.addVideoInfo("https://test-pvg-video-contents-bucket.s3.ap-northeast-1.amazonaws.com/flower.mp4", "video6.mp4")
        //動画をダウンロードしてくる


        videoManager.getVideoInfos().forEachIndexed { _, videoUrl ->
            downloadManager.downloadVideo(videoUrl.url, videoUrl.fileName,
                onSuccess = {
                    runOnUiThread {
                        println("動画をダウンロードしました")


                        val videoPath = downloadManager.getVideoFilePath()
                        val videoUri = Uri.parse(videoPath)
                        println("videoUriのURL$videoUri")
                    }
                }
            ) {
                println("動画のダウンロードに失敗しました")

                it.printStackTrace()
            }
        }

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