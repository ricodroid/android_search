package com.example.recyclerviewticktock

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.recyclerviewticktock.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by lazy {
        ViewModelProvider(this, MainViewModel.Factory(application))[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ボタンがクリックされるたびに、クリック回数をカウントアップして、表示を更新する。
        binding.button.setOnClickListener {
            mainViewModel.countUp()
            showCount()
        }
    }

    override fun onResume() {
        super.onResume()
        showCount()
    }

    /** クリック回数を TextView に表示する。 */
    private fun showCount() {
        findViewById<TextView>(R.id.textView).text = mainViewModel.count.toString()
    }


}