package com.example.recyclerviewticktock

import android.content.Context
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.AbsoluteSizeSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.Menu
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewticktock.databinding.ActivityFavoriteBinding

class FavoriteActivity : AppCompatActivity() {

    private val favoriteViewModel: FavoriteViewModel by lazy {
        ViewModelProvider(this, FavoriteViewModel.Factory(application))[FavoriteViewModel::class.java]
    }

    private lateinit var favoriteMyListRecyclerView: RecyclerView
    private lateinit var favoriteRecyclerView: RecyclerView
    private var mFavoriteMyList = ArrayList<CardDate>()
    private var mList = ArrayList<CardDate>()
    private lateinit var favoriteMyListAdapter: FavoriteMyListCardAdapter
    private lateinit var adapter: FavoriteCardAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        val binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        favoriteMyListRecyclerView = binding.favoriteRecyclerViewMyList
        favoriteRecyclerView = binding.favoriteRecyclerView

        // タイトルを変更する
        binding.materialToolbar.title = "お気に入り画面"

        // ツールバーを表示する
        setSupportActionBar(binding.materialToolbar)
        // ※ ツールバーを表示するより後に書かないと戻るボタンが追加されない
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        favoriteMyListRecyclerView.setHasFixedSize(true)



        // 横スクロールにする
        favoriteMyListRecyclerView.layoutManager = GridHorizontalLayoutManager(this)
        addDataToFavoriteMyList()
        favoriteMyListAdapter = FavoriteMyListCardAdapter(mFavoriteMyList)
        favoriteMyListRecyclerView.adapter = favoriteMyListAdapter


        favoriteRecyclerView.setHasFixedSize(true)
        favoriteRecyclerView.layoutManager = GridLayoutManager(this, 3, RecyclerView.VERTICAL, false)

        addDataTolist()
        adapter = FavoriteCardAdapter(mList)
        favoriteRecyclerView.adapter = adapter

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

    private fun addDataToFavoriteMyList() {
        mFavoriteMyList.add(CardDate("Java", R.drawable.baseline_thumb_up_off_alt_24))
        mFavoriteMyList.add(CardDate("Kotlin", R.drawable.baseline_thumb_up_off_alt_24))
        mFavoriteMyList.add(CardDate("Python", R.drawable.baseline_thumb_up_off_alt_24))
        mFavoriteMyList.add(CardDate("Swift", R.drawable.baseline_thumb_up_off_alt_24))
        mFavoriteMyList.add(CardDate("JavaScript", R.drawable.baseline_thumb_up_off_alt_24))
        mFavoriteMyList.add(CardDate("Obc", R.drawable.baseline_thumb_up_off_alt_24))
        mFavoriteMyList.add(CardDate("Ruby", R.drawable.baseline_thumb_up_off_alt_24))
        mFavoriteMyList.add(CardDate("GO", R.drawable.baseline_thumb_up_off_alt_24))
        mFavoriteMyList.add(CardDate("C++", R.drawable.baseline_thumb_up_off_alt_24))
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