package com.example.recyclerviewticktock

import DownloadMp4Manager
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewticktock.databinding.ActivityTikTokBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File


class TikTokActivity : AppCompatActivity() {
    private lateinit var tikTokRecyclerView: RecyclerView
    private var mList = ArrayList<CardDate>()
    private lateinit var adapter: TikTokAdapter


    private val downloadManager = DownloadMp4Manager(this)
    private var videoManager = VideoInfoManager()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityTikTokBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tikTokRecyclerView = binding.tiktokRecyclerView
        tikTokRecyclerView.setHasFixedSize(true)
        tikTokRecyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

//        adapter = TikTokAdapter(mList)
//        tikTokRecyclerView.adapter = adapter

        videoManager = VideoInfoManager()

        // S3の動画のパスを設定する
        videoManager.addVideoInfo(
            "https://test-pvg-video-contents-bucket.s3.ap-northeast-1.amazonaws.com/pexels-bu%CC%88s%CC%A7ra-c%CC%A7akmak-20159065+(1080p).mp4",
            "video1.mp4"
        )
        videoManager.addVideoInfo(
            "https://test-pvg-video-contents-bucket.s3.ap-northeast-1.amazonaws.com/pexels-bu%CC%88s%CC%A7ra-c%CC%A7akmak-20159065+(1080p).mp4",
            "video2.mp4"
        )
        videoManager.addVideoInfo(
            "https://test-pvg-video-contents-bucket.s3.ap-northeast-1.amazonaws.com/pexels-bu%CC%88s%CC%A7ra-c%CC%A7akmak-20159065+(1080p).mp4",
            "video3.mp4"
        )
        videoManager.addVideoInfo(
            "https://test-pvg-video-contents-bucket.s3.ap-northeast-1.amazonaws.com/pexels-bu%CC%88s%CC%A7ra-c%CC%A7akmak-20159065+(1080p).mp4",
            "video4.mp4"
        )
        videoManager.addVideoInfo(
            "https://test-pvg-video-contents-bucket.s3.ap-northeast-1.amazonaws.com/pexels-bu%CC%88s%CC%A7ra-c%CC%A7akmak-20159065+(1080p).mp4",
            "video5.mp4"
        )

        // 各ダウンロードの完了をまつフラグ
        val downloadFlags = MutableList(videoManager.getVideoInfos().size) { false }

        // Coroutineを起動してダウンロード処理を非同期に実行
        CoroutineScope(Dispatchers.Main).launch {
            // すべてのダウンロードが完了するのを待機
            videoManager.getVideoInfos().forEachIndexed { index, videoUrl ->
                downloadManager.downloadVideo(videoUrl.url, videoUrl.fileName,
                    onSuccess = {
                        println("動画をダウンロードしました")
                        val videoPath = downloadManager.getVideoFilePath()
                        val videoUri = Uri.parse(videoPath)
                        println("ダウンロードファイル名$videoUrl.fileName")

                        // ダウンロードが完了したらフラグをセット
                        downloadFlags[index] = true

                        // すべてのダウンロードが完了したかチェック
                        if (downloadFlags.all { it }) {
                            // すべてのダウンロードが完了した後の処理を実行
                            println("すべての動画のダウンロードが完了しました")
                            // 次の処理をここに記述する

                            val directory = File(filesDir.absolutePath)
                            val files = directory.listFiles()

                            files?.forEach { file ->
                                println("###ダウンロードされたファイル: ${file.name}, Absolute path: ${file.absolutePath}")
                            }

                            addDataTolist()

                            adapter = TikTokAdapter(mList)
                            tikTokRecyclerView.adapter = adapter
                        }
                    }
                ) {
                    println("動画のダウンロードに失敗しました")
                    it.printStackTrace()
                }
            }
        }

        // 一つのアイテムごとに停止
        val snapHelper = PagerSnapHelper()
        tikTokRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        snapHelper.attachToRecyclerView(tikTokRecyclerView)



//        videoManager.getMatchingFileNames(this)

//        tikTokRecyclerView = binding.tiktokRecyclerView
//        tikTokRecyclerView.adapter = TikTokRecyclerAdapter(this, object : OnIconClickListener {
//            override fun onIconClick(v: View) {
//                // アイコンがクリックされたときの処理
//                when (v.id) {
//                    R.id.icon1 -> {
//                        // 人型アイコン
//                        println("アイコン1がクリックされました！aa")
//                        // マイリスト画面へ遷移する
//                        val intent = Intent(this@TikTokActivity, MyListActivity::class.java)
//                        startActivity(intent)
//                    }
//
//                    R.id.icon2 -> {
//                        // スターアイコン
//                        println("アイコン2がクリックされました！")
//
//                    }
//
//                    R.id.icon3 -> {
//                        // グッドアイコン
//
//                    }
//
//                    R.id.icon4 -> {
//                        // バッドアイコン
//                    }
//
//                    R.id.icon5 -> {
//                        // サーチアイコン
//                        println("アイコン5がクリックされました！")
//                        // 検索画面へ遷移させる
//                        val intent = Intent(this@TikTokActivity, SearchViewActivity::class.java)
//                        startActivity(intent)
//                    }
//                }
//            }
//        })
//
    }

    private fun addDataTolist() {
        val videoDir = filesDir // 動画ファイルが保存されているディレクトリ
        val videoFiles = videoDir.listFiles { file -> file.extension == "mp4" } // mp4形式の動画ファイルのみを取得

        videoFiles?.forEach { file ->
            println("###addDataTolist: $file")
            mList.add(CardDate(file.name, file.absolutePath))
        }
    }

    private fun getAllVideoCardData(): List<CardDate> {
        val videoDir = filesDir // 動画ファイルが保存されているディレクトリ
        val videoFiles = videoDir.listFiles { file -> file.extension == "mp4" } // mp4形式の動画ファイルのみを取得

        return videoFiles?.map { file -> CardDate(file.name, file.absolutePath) } ?: emptyList() // CardDateオブジェクトのリストを返す。nullの場合は空のリストを返す
    }

}

interface OnIconClickListener {
    fun onIconClick(v: View)
}