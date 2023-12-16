package com.example.recyclerviewticktock

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainViewModel(application: Application) : AndroidViewModel(application) {
    class Factory(private val application: Application) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>)
        = MainViewModel(application) as T
    }

    /** Button のクリック回数 */
    var count = 0
        private set

    /** クリック回数をカウントアップする。 */
    fun countUp() {
        count++
    }

}