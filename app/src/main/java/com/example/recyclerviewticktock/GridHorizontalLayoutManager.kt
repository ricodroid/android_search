package com.example.recyclerviewticktock

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * RecyclerViewのレイアウトを横方向に、一画面に3アイテム表示する
 */
class GridHorizontalLayoutManager(context: Context) : LinearLayoutManager(context, HORIZONTAL, false) {
    override fun checkLayoutParams(lp: RecyclerView.LayoutParams?): Boolean {
        lp?.width = ((width - paddingRight - paddingLeft) / 3)
        return true
    }
}