package com.example.recyclerviewticktock

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.SearchView
import java.util.Locale
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewticktock.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var tagRecycleView: RecyclerView
    private lateinit var searchView: SearchView
    private var mList = ArrayList<CardDate>()
    private var mTagList = ArrayList<TagDate>()
    private lateinit var adapter: CardAdapter
    private lateinit var tagAdapter: TagAdapter

    private val mainViewModel: MainViewModel by lazy {
        ViewModelProvider(this, MainViewModel.Factory(application))[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
        searchView = findViewById(R.id.searchView)

        tagAdapter.setOnTagCellClickListener(
            object : TagAdapter.OnTagCellClickListener {
                override fun onItemClick(tag: TagDate) {
                    Log.d(tag.tag,"###クリックされました")
                    // TODO クリックされたタグを、検索ボックスの中に入れる必要がある
                }
            }
        )

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(this, 2, RecyclerView.VERTICAL, false)

        addDataTolist()
        adapter = CardAdapter(mList)
        recyclerView.adapter = adapter
        binding.tagTitle.text = "タグリスト"

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            View.OnFocusChangeListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }

            override fun onFocusChange(v: View?, hasFocus: Boolean) {
                if (hasFocus) {
                    // フォーカスを持った状態での処理
                } else {
                    // フォーカスが外れた状態での処理
                }
            }
        })
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
        mList.add(CardDate("Java", R.drawable.baseline_thumb_up_off_alt_24))
        mList.add(CardDate("Kotlin", R.drawable.baseline_thumb_up_off_alt_24))
        mList.add(CardDate("Python", R.drawable.baseline_thumb_up_off_alt_24))
        mList.add(CardDate("Swift", R.drawable.baseline_thumb_up_off_alt_24))
        mList.add(CardDate("JavaScript", R.drawable.baseline_thumb_up_off_alt_24))
        mList.add(CardDate("Obc", R.drawable.baseline_thumb_up_off_alt_24))
    }

    private fun addTagList() {
        mTagList.add(TagDate("#タグ1タ"))
        mTagList.add(TagDate("#タグ2"))
        mTagList.add(TagDate("#タグ1グ1グ3"))
        mTagList.add(TagDate("#タグ4タグ4タグ4タグ4タグ4タグ4aa"))
        mTagList.add(TagDate("#タグdfasrer1グ1グ5"))
        mTagList.add(TagDate("#タグ6"))
        mTagList.add(TagDate("#タグ7"))
    }

}