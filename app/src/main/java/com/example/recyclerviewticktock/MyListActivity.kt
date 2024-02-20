package com.example.recyclerviewticktock

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewticktock.databinding.ActivityMyListBinding

class MyListActivity : AppCompatActivity() {

    private val myListViewModel: MyListViewModel by lazy {
        ViewModelProvider(this, MyListViewModel.Factory(application))[MyListViewModel::class.java]
    }

    private lateinit var myListRecyclerView: RecyclerView
    private var mList = ArrayList<CardDate>()
    private lateinit var adapter: MyListCardAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_list)

        val binding = ActivityMyListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        myListRecyclerView = binding.myListRecyclerView

        // タイトルを変更する
        binding.materialToolbar.title = "ユーザー名"

        // ツールバーを表示する
        setSupportActionBar(binding.materialToolbar)
        // ※ ツールバーを表示するより後に書かないと戻るボタンが追加されない
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        myListRecyclerView.setHasFixedSize(true)
        myListRecyclerView.layoutManager = GridLayoutManager(this, 3, RecyclerView.VERTICAL, false)

        addDataTolist()
        adapter = MyListCardAdapter(mList)
        myListRecyclerView.adapter = adapter

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // menuを追加する
        menuInflater.inflate(R.menu.menu_item, menu)
        return true
    }

    private fun addDataTolist() {
        mList.add(CardDate("Java", R.drawable.baseline_thumb_up_off_alt_24))
        mList.add(CardDate("Kotlin", R.drawable.baseline_thumb_up_off_alt_24))
        mList.add(CardDate("Python", R.drawable.baseline_thumb_up_off_alt_24))
        mList.add(CardDate("Swift", R.drawable.baseline_thumb_up_off_alt_24))
        mList.add(CardDate("JavaScript", R.drawable.baseline_thumb_up_off_alt_24))
        mList.add(CardDate("Obc", R.drawable.baseline_thumb_up_off_alt_24))
        mList.add(CardDate("Ruby", R.drawable.baseline_thumb_up_off_alt_24))
        mList.add(CardDate("GO", R.drawable.baseline_thumb_up_off_alt_24))
        mList.add(CardDate("C++", R.drawable.baseline_thumb_up_off_alt_24))
    }

}