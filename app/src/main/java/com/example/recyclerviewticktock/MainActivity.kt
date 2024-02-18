package com.example.recyclerviewticktock

import android.os.Bundle
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
    private lateinit var searchView: SearchView
    private var mList = ArrayList<CardDate>()
    private lateinit var adapter: CardAdapter

    private val mainViewModel: MainViewModel by lazy {
        ViewModelProvider(this, MainViewModel.Factory(application))[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = binding.recyclerView
        searchView = findViewById(R.id.searchView)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(this, 2, RecyclerView.VERTICAL, false)

        addDataTolist()
        adapter = CardAdapter(mList)
        recyclerView.adapter = adapter

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

}