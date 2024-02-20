package com.example.recyclerviewticktock

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TagAdapter(var mList: List<TagDate>): RecyclerView.Adapter<TagAdapter.TagHolder>() {
    inner class  TagHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val tag: TextView = itemView.findViewById(R.id.tag)
    }

    private lateinit var listener: OnTagCellClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tag_item, parent, false)
        return TagHolder(view)
    }

    override fun getItemCount() = mList.size

    override fun onBindViewHolder(holder: TagHolder, position: Int) {
        holder.tag.text = mList[position].tag

        holder.itemView.setOnClickListener {
            listener.onItemClick(mList[position])
        }
    }

    // 2. インターフェースを作成
    interface  OnTagCellClickListener {
        fun onItemClick(tag: TagDate)
    }

    // クリックリスナー
    fun setOnTagCellClickListener(listener: OnTagCellClickListener) {
        // 定義した変数listenerに実行したい処理を引数で渡す（BookListFragmentで渡している）
        this.listener = listener
    }
}