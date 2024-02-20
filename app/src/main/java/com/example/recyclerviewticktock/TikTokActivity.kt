package com.example.recyclerviewticktock

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
        tikTokRecyclerView.adapter = TikTokRecyclerAdapter(this)

        // 一つのアイテムごとに停止
        val snapHelper = PagerSnapHelper()
        tikTokRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        snapHelper.attachToRecyclerView(tikTokRecyclerView)
    }
}