package com.example.recyclerviewticktock

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.AbsoluteSizeSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.ContextCompat
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
        val menuItem = menu?.findItem(R.id.action_edit)
        val spannableString = SpannableString(menuItem?.title)
        spannableString.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(this, R.color.colorDarkBlue)),
            0,
            spannableString.length,
            Spannable.SPAN_INCLUSIVE_INCLUSIVE
        )

        // テキストのサイズを設定
        spannableString.setSpan(
            AbsoluteSizeSpan(16, true),
            0,
            spannableString.length,
            Spannable.SPAN_INCLUSIVE_INCLUSIVE
        )

        // テキストのスタイルを設定 (太字)
        spannableString.setSpan(
            StyleSpan(Typeface.BOLD),
            0,
            spannableString.length,
            Spannable.SPAN_INCLUSIVE_INCLUSIVE
        )
        menuItem?.title = spannableString
        return true
    }

    private fun addDataTolist() {
        mList.add(CardDate("Good", "https://test-pvg-video-contents-bucket.s3.ap-northeast-1.amazonaws.com/pexels-bu%CC%88s%CC%A7ra-c%CC%A7akmak-20159065+(1080p).mp4"))

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            // TODO ここに仮で、お気に入り画面に遷移する処理を入れている【仮】
            R.id.action_edit -> {
                // 編集ボタンタップ時
                val intent = Intent(this@MyListActivity, FavoriteActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
