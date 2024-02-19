package com.example.recyclerviewticktock

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.lifecycle.ViewModelProvider
import com.example.recyclerviewticktock.databinding.ActivityMyListBinding

class MyListActivity : AppCompatActivity() {

    private val myListViewModel: MyListViewModel by lazy {
        ViewModelProvider(this, MyListViewModel.Factory(application))[MyListViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_list)

        val binding = ActivityMyListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // タイトルを変更する
        binding.materialToolbar.title = "ユーザー名"

        // ツールバーを表示する
        setSupportActionBar(binding.materialToolbar)
        // ※ ツールバーを表示するより後に書かないと戻るボタンが追加されない
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // menuを追加する
        menuInflater.inflate(R.menu.menu_item, menu)
        return true
    }

}