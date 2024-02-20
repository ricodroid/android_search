package com.example.recyclerviewticktock

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView

data class TagTextData(val tag: String)

class TagTextViewManager(private val context: Context, private val linearLayout: LinearLayout) {

    private val tags = mutableListOf<TagTextData>()

    fun addTag(text: String) {
        tags.add(TagTextData(text))
        updateViews()
    }

    fun removeTag(text: String) {
        val index = tags.indexOfFirst { it.tag == text }
        if (index != -1) {
            tags.removeAt(index)
            updateViews()
        }
    }
    fun addTextViews(texts: Array<String>) {
        for (text in texts) {
            val textView = TextView(context)
            val layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )
            layoutParams.marginStart = context.resources.getDimensionPixelSize(R.dimen.view_margin_start)
            layoutParams.topMargin = context.resources.getDimensionPixelSize(R.dimen.view_margin_top)
            layoutParams.bottomMargin = context.resources.getDimensionPixelSize(R.dimen.view_margin_top)
            textView.layoutParams = layoutParams
            textView.text = text
            textView.setTextColor(Color.WHITE)
            textView.setTypeface(null, Typeface.BOLD)
            textView.gravity = Gravity.CENTER
            textView.setBackgroundColor(Color.parseColor("#87CEFA"))
            val paddingStart = context.resources.getDimensionPixelSize(R.dimen.text_padding_start)
            val paddingEnd = context.resources.getDimensionPixelSize(R.dimen.text_padding_start)

            textView.setPaddingRelative(paddingStart, 0, paddingEnd, 0)

            val shapeDrawable = context.resources.getDrawable(R.drawable.rounded_corners, null)
            textView.background = shapeDrawable
            linearLayout.addView(textView)
        }
    }

    private fun updateViews() {
        linearLayout.removeAllViews()
        for (textData in tags) {
            val textView = TextView(context)
            val layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )
            layoutParams.marginStart = context.resources.getDimensionPixelSize(R.dimen.view_margin_start)
            layoutParams.topMargin = context.resources.getDimensionPixelSize(R.dimen.view_margin_top)
            layoutParams.bottomMargin = context.resources.getDimensionPixelSize(R.dimen.view_margin_top)
            textView.layoutParams = layoutParams
            textView.text = textData.tag
            textView.setTextColor(Color.WHITE)
            textView.setTypeface(null, Typeface.BOLD)
            textView.gravity = Gravity.CENTER
            textView.setBackgroundColor(Color.parseColor("#87CEFA"))
            val paddingStart = context.resources.getDimensionPixelSize(R.dimen.text_padding_start)
            val paddingEnd = context.resources.getDimensionPixelSize(R.dimen.text_padding_start)

            textView.setPaddingRelative(paddingStart, 0, paddingEnd, 0)

            val shapeDrawable = context.resources.getDrawable(R.drawable.rounded_corners, null)
            textView.background = shapeDrawable
            linearLayout.addView(textView)
        }
    }
}