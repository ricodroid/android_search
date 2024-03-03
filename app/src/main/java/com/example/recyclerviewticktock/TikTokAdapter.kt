package com.example.recyclerviewticktock

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.VideoView
import androidx.recyclerview.widget.RecyclerView

class TikTokAdapter(
    var mList: List<CardDate>,
    private var context: Context
) :
    RecyclerView.Adapter<TikTokAdapter.TikTokViewHolder>() {

    private lateinit var beforeText: String
    private lateinit var replaceText: String
    private lateinit var text1: TextView
    private lateinit var text2: TextView
    private lateinit var text3: TextView
    private lateinit var mottoMiruButton: TextView
    private lateinit var shukushouButton: TextView
    private lateinit var icon1: ImageView
    private lateinit var icon2: ImageView
    private lateinit var icon3: ImageView
    private lateinit var icon4: ImageView
    private lateinit var icon5: ImageView

    enum class IconButtonState {
        TAPPED,
        UN_TAPPED
    }

    private var currentHumanState = TikTokViewHolderItem.IconButtonState.UN_TAPPED
    private var currentStarState = TikTokViewHolderItem.IconButtonState.UN_TAPPED
    private var currentGoodState = TikTokViewHolderItem.IconButtonState.UN_TAPPED
    private var currentBadState = TikTokViewHolderItem.IconButtonState.UN_TAPPED
    private var currentSearchState = TikTokViewHolderItem.IconButtonState.UN_TAPPED

    inner class TikTokViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val logo: VideoView = itemView.findViewById(R.id.logoIv)
    }

    fun setFilterdList(mList: List<CardDate>) {
        this.mList = mList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TikTokViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tik_tok_item, parent, false)

        return TikTokViewHolder(view)
    }

    override fun getItemCount() = mList.size

    override fun onBindViewHolder(holder: TikTokViewHolder, position: Int) {
        val cardData = mList[position]
        // VideoView を取得し、動画をセット
        val videoView = holder.itemView.findViewById<VideoView>(R.id.logoIv)
        videoView.setVideoURI(Uri.parse(cardData.logo))
        // 動画再生を開始
        videoView.setOnPreparedListener { mp ->
            Log.d("###動画を再生する", Uri.parse(cardData.logo).toString())
            mp.start()
        }

        text1 = holder.itemView.findViewById(R.id.textView1)
        text2 = holder.itemView.findViewById(R.id.textView2)
        text3 = holder.itemView.findViewById(R.id.textView3)
        text1.text = "雨ニモ負ケズ"
        beforeText = text3.text.toString()
        mottoMiruButton = holder.itemView.findViewById(R.id.mottoMiruButton)
        shukushouButton = holder.itemView.findViewById(R.id.shukushouButton)

        /** いいねボタンとかのアイコン類 */
        icon1 = holder.itemView.findViewById(R.id.icon1)
        icon2 = holder.itemView.findViewById(R.id.icon2)
        icon3 = holder.itemView.findViewById(R.id.icon3)
        icon4 = holder.itemView.findViewById(R.id.icon4)
        icon5 = holder.itemView.findViewById(R.id.icon5)



        if (isLongText(text3.text)) {
            // 13文字以上なら、もっと見るボタンを表示する
            toggleMottoMiruButtonVisibility(mottoMiruButton, View.VISIBLE)
            toggleMottoMiruButtonVisibility(shukushouButton, View.INVISIBLE)
            replaceText = replaceText(text3).toString()
            text3.text = replaceText(text3)
        } else {
            toggleMottoMiruButtonVisibility(mottoMiruButton, View.INVISIBLE)
            toggleMottoMiruButtonVisibility(shukushouButton, View.VISIBLE)
        }



        mottoMiruButton.setOnClickListener {
            println("もっと見るテキストがクリックされました！")
            holder.itemView.findViewById<TextView>(R.id.textView3).text = beforeText
            toggleMottoMiruButtonVisibility(holder.itemView.findViewById(R.id.mottoMiruButton), View.INVISIBLE)
            toggleMottoMiruButtonVisibility(holder.itemView.findViewById(R.id.shukushouButton), View.VISIBLE)
            holder.itemView.findViewById<TextView>(R.id.mottoMiruButton).text = "縮小表示に戻す"
        }

        shukushouButton.setOnClickListener {
            println("縮小テキストがクリックされました！")
            holder.itemView.findViewById<TextView>(R.id.textView3).text = replaceText
            toggleMottoMiruButtonVisibility(holder.itemView.findViewById(R.id.shukushouButton), View.INVISIBLE)
            toggleMottoMiruButtonVisibility(holder.itemView.findViewById(R.id.mottoMiruButton), View.VISIBLE)
            holder.itemView.findViewById<TextView>(R.id.mottoMiruButton).text = "...もっと見る"
        }

        // アイコンのクリック処理を実装
        icon1.setOnClickListener {
            Log.d("TikTokAdapter", "アイコン1がクリックされました")
            // 人型アイコン
            // マイリスト画面へ遷移する
            val intent = Intent(context, MyListActivity::class.java)
            context.startActivity(intent)
        }

        icon2.setOnClickListener {
            // ここにアイコン2のクリック処理を記述
            Log.d("TikTokAdapter", "アイコン2がクリックされました")
            toggleIconState(currentStarState, icon2)
            updateStarButtonImage(holder.itemView.findViewById(R.id.icon2))
        }

        icon3.setOnClickListener {
            // ここにアイコン2のクリック処理を記述
            Log.d("TikTokAdapter", "アイコン3がクリックされました")
            toggleIconState(currentGoodState, icon3)// icon3はcurrentGoodStateがTAPPEDになる

            updateGoodButtonImage(holder.itemView.findViewById(R.id.icon3))// icon3はTAPPEDのアイコンになる
            // icon4がTAPPEDだったら、icon4をUNTAPPEDにする
            if (currentGoodState == TikTokViewHolderItem.IconButtonState.TAPPED && currentBadState == TikTokViewHolderItem.IconButtonState.TAPPED) {
                toggleIconState(currentBadState, icon4)// badのステータスUN_TAPPED
                updateBadButtonImage(holder.itemView.findViewById(R.id.icon4))
            }
        }

        icon4.setOnClickListener {
            // ここにアイコン2のクリック処理を記述
            Log.d("TikTokAdapter", "アイコン4がクリックされました")
            toggleIconState(currentBadState, icon4)

            updateBadButtonImage(holder.itemView.findViewById(R.id.icon4))
            // icon3がTAPPEDだったら、icon3をUNTAPPEDにする
            if (currentGoodState == TikTokViewHolderItem.IconButtonState.TAPPED && currentBadState == TikTokViewHolderItem.IconButtonState.TAPPED) {
                toggleIconState(currentGoodState, icon3)
                updateGoodButtonImage(holder.itemView.findViewById(R.id.icon3))
            }
        }

        icon5.setOnClickListener {
            // ここにアイコン2のクリック処理を記述
            Log.d("TikTokAdapter", "アイコン5がクリックされました")
            // 検索画面へ遷移させる
            val intent = Intent(context, SearchViewActivity::class.java)
            context.startActivity(intent)
        }

    }


    /**
     * テキストの最初の13文字だけを取り出して、
     * 末尾に「・・・」をつける
     *
     * @return String
     */
    private fun replaceText(text: TextView): StringBuffer {
        // 最初から13文字を取得
        val shortenedText = text.text.take(9)
        // 末尾に「・・・」を追加
        return StringBuffer(shortenedText).append("・・・")
    }

    /**
     * 13文字以上ならtrue
     * 13文字未満ならfalse
     * @return Boolean
     */
    private fun isLongText(text: CharSequence): Boolean {
        return text.length >= 9
    }

    private fun toggleMottoMiruButtonVisibility(textType: TextView, viewStatus: Int) {
        textType.visibility = viewStatus
    }

    private fun toggleIconState(state: TikTokViewHolderItem.IconButtonState, button: ImageView) {
        // アイコンの状態をトグルする
        when (button) {
            icon2 -> {
                currentStarState =
                    when (state) {
                        TikTokViewHolderItem.IconButtonState.UN_TAPPED -> TikTokViewHolderItem.IconButtonState.TAPPED
                        TikTokViewHolderItem.IconButtonState.TAPPED -> TikTokViewHolderItem.IconButtonState.UN_TAPPED
                    }
            }

            icon3 -> {
                currentGoodState =
                    when (state) {
                        TikTokViewHolderItem.IconButtonState.UN_TAPPED -> TikTokViewHolderItem.IconButtonState.TAPPED
                        TikTokViewHolderItem.IconButtonState.TAPPED -> TikTokViewHolderItem.IconButtonState.UN_TAPPED
                    }
            }

            icon4 -> {
                currentBadState =
                    when (state) {
                        TikTokViewHolderItem.IconButtonState.UN_TAPPED -> TikTokViewHolderItem.IconButtonState.TAPPED
                        TikTokViewHolderItem.IconButtonState.TAPPED -> TikTokViewHolderItem.IconButtonState.UN_TAPPED
                    }
            }
        }

    }

    private fun updateGoodButtonImage(button: ImageView) {
        // Goodボタン：現在の状態に応じて画像を切り替える
        val imageResource = when (currentGoodState) {
            TikTokViewHolderItem.IconButtonState.UN_TAPPED -> R.drawable.baseline_thumb_up_off_alt_24
            TikTokViewHolderItem.IconButtonState.TAPPED -> R.drawable.icon_good_tapped
        }
        button.setImageResource(imageResource)
    }

    private fun updateBadButtonImage(button: ImageView) {
        // Badボタン：現在の状態に応じて画像を切り替える
        val imageResource = when (currentBadState) {
            TikTokViewHolderItem.IconButtonState.UN_TAPPED -> R.drawable.icon_bad
            TikTokViewHolderItem.IconButtonState.TAPPED ->  R.drawable.icon_bad_tapped
        }
        button.setImageResource(imageResource)
    }

    private fun updateStarButtonImage(button: ImageView) {
        // アップデートボタン：現在の状態に応じて画像を切り替える
        val imageResource = when (currentStarState) {
            TikTokViewHolderItem.IconButtonState.UN_TAPPED -> R.drawable.icon_star
            TikTokViewHolderItem.IconButtonState.TAPPED -> R.drawable.icon_star_tapped
        }
        button.setImageResource(imageResource)
    }
}