package com.example.recyclerviewticktock

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewticktock.databinding.ActivityTikTokBinding


class TikTokActivity : AppCompatActivity() {
    private lateinit var tikTokRecyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityTikTokBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tikTokRecyclerView = binding.tiktokRecyclerView
        tikTokRecyclerView.adapter = TikTokRecyclerAdapter(this, object : OnIconClickListener {
            override fun onIconClick(v: View) {
                // アイコンがクリックされたときの処理
                when (v.id) {
//                    R.id.mottoMiruButton -> {
//                        println("もっと見るテキストがクリックされました！")
//                        text3.text = beforeText
//                        toggleMottoMiruButtonVisibility(mottoMiruButton, View.INVISIBLE)
//                        toggleMottoMiruButtonVisibility(shukushouButton, View.VISIBLE)
//                        mottoMiruButton.text = "縮小表示に戻す"
//                    }
//
//                    shukushouButton -> {
//                        println("縮小テキストがクリックされました！")
//                        text3.text = replaceText
//                        toggleMottoMiruButtonVisibility(shukushouButton, View.INVISIBLE)
//                        toggleMottoMiruButtonVisibility(mottoMiruButton, View.VISIBLE)
//                        mottoMiruButton.text = "...もっと見る"
//                    }

                    R.id.icon1 -> {
                        // 人型アイコン
                        println("アイコン1がクリックされました！")
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