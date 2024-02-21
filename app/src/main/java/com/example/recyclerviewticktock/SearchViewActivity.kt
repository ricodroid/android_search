package com.example.recyclerviewticktock

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import java.util.Locale
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewticktock.databinding.ActivitySearchBinding

class SearchViewActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var tagRecycleView: RecyclerView
    private var mList = ArrayList<CardDate>()
    private var mTagList = ArrayList<TagDate>()
    private lateinit var adapter: CardAdapter
    private lateinit var tagAdapter: TagAdapter

    private val mainViewModel: MainViewModel by lazy {
        ViewModelProvider(this, MainViewModel.Factory(application))[MainViewModel::class.java]
    }

    private lateinit var searchButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 検索画面なら
        if (true) {
            binding.tagRecyclerView.visibility = View.VISIBLE
            binding.recyclerView.visibility = View.INVISIBLE
            binding.tagTitle.text = "タグリスト"
        } else {
            // 検索結果画面なら
            binding.tagRecyclerView.visibility = View.INVISIBLE
            binding.recyclerView.visibility = View.VISIBLE
            binding.tagTitle.text = "検索結果"
        }

        searchButton = binding.searchButton

        tagRecycleView = binding.tagRecyclerView
        tagRecycleView.setHasFixedSize(true)
        tagRecycleView.layoutManager = GridLayoutManager(this, 4).apply {
            spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                // TODO ここで、テキストのサイズが大きい場合は、幅を大きくとりたい
                override fun getSpanSize(position: Int): Int {
                    val item = mTagList[position]
                    return if (isLargeItem(item)) {
                        2 // 大きいアイテムの場合は2つのサイズを使用
                    } else {
                        1 // 通常のアイテムの場合は1つのサイズを使用
                    }
                }
            }
        }
        // https://kumaskun.hatenablog.com/entry/2022/11/20/104902 // ここに、リストの可変サイズのハックがある

        addTagList()
        tagAdapter = TagAdapter(mTagList)
        tagRecycleView.adapter = tagAdapter

        recyclerView = binding.recyclerView

        // 検索ボックス内のタグを表現するレイアウト
        val linearLayout = findViewById<LinearLayout>(R.id.search_box_layout)
        val textViewManager = TagTextViewManager(this, linearLayout)

        // タップされたタグのテキストを検索ボックスにaddしていく
        tagAdapter.setOnTagCellClickListener(
            object : TagAdapter.OnTagCellClickListener {
                override fun onItemClick(tag: TagDate) {
                    Log.d(tag.tag, "tag Tapped!")
                    textViewManager.addTag(tag.tag)
                }
            }
        )

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(this, 2, RecyclerView.VERTICAL, false)

        addDataTolist()
        adapter = CardAdapter(mList)
        recyclerView.adapter = adapter

        searchButton.setOnClickListener {
            // 検索ボタンタップ
            // タグをリクエストボディに付与して、APIリクエストを送る
            // 画面を、検索画面から検索結果A画面へ遷移させる → 同じActivityなので、表示非表示で切り替え
            binding.tagRecyclerView.visibility = View.INVISIBLE
            binding.recyclerView.visibility = View.VISIBLE
        }

    }
    private fun isLargeItem(item: TagDate): Boolean {
        // セル内の値のサイズに応じて大きいアイテムかどうかを判断するロジック
        // ここでは例として、TagItemが大きい場合を判断する条件を設定しています
        return item.tag.length > 8 // 例: テキストの長さが10以上の場合を大きいアイテムとして扱う
    }

    private fun filterList(query: String?) {

        if (query != null) {
            val filteredList = ArrayList<CardDate>()
            for (i in mList) {
                if (i.title.lowercase(Locale.ROOT).contains(query)) {
                    filteredList.add(i)
                }
            }

            if (filteredList.isEmpty()) {
                Toast.makeText(this, "No Data found", Toast.LENGTH_SHORT).show()
            } else {
                adapter.setFilterdList(filteredList)
            }
        }
    }

    override fun onResume() {
        super.onResume()
    }

    private fun addDataTolist() {
        mList.add(CardDate("Good", "android.resource://" + this.packageName + "/" + R.raw.move1))
        mList.add(CardDate("Good", "android.resource://" + this.packageName + "/" + R.raw.move2))
        mList.add(CardDate("Good", "android.resource://" + this.packageName + "/" + R.raw.move3))
        mList.add(CardDate("Good", "android.resource://" + this.packageName + "/" + R.raw.move4))
        mList.add(CardDate("Good", "android.resource://" + this.packageName + "/" + R.raw.move5))
        mList.add(CardDate("Good", "android.resource://" + this.packageName + "/" + R.raw.move1))
    }

    private fun addTagList() {
        mTagList.add(TagDate("#タグ1タ"))
        mTagList.add(TagDate("#タグ2"))
        mTagList.add(TagDate("#タグ1グ1グ3"))
        mTagList.add(TagDate("#タグ4タグ4タグ4タグ4タグ4タグ4aa"))
        mTagList.add(TagDate("#タグdfasrer1グ1グ5"))
    }
}