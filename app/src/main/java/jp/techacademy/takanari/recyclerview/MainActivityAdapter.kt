package jp.techacademy.takanari.recyclerview

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MainActivityAdapter(
//    private val items: List<String>,
    private val onItemClick: (item: Boolean) -> Unit = {}
): RecyclerView.Adapter<MainActivityAdapter.ViewHolder>() {


    var mListData: ArrayList<Data>

    init {
        mListData = ArrayList()

        for (i in 0 until ITEM_NUM) {
            val text = String.format(LIST_TEXT_BASE, i)
            mListData.add(Data(text))
        }
    }
    companion object {

        private val LIST_TEXT_BASE = "　"
        private val ITEM_NUM = 34

    }

    // 1
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var contentTitle = itemView.findViewById(R.id.contentText) as TextView
    }

    // 2
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view: View = LayoutInflater.from(p0.context).inflate(R.layout.item_recycler_view, p0, false)
        val vh = ViewHolder(view)
        //配列押した時
        view.setOnClickListener {
            //配列の背景の色変更
            onItemClick(mListData[vh.adapterPosition].test2)

            if (mListData[vh.adapterPosition].test2==false) {
                mListData[vh.adapterPosition].test2=true
                view.setBackgroundColor(Color.rgb(127, 127, 255))

            }else if (mListData[vh.adapterPosition].test2==true){
                mListData[vh.adapterPosition].test2=false
                view.setBackgroundColor(Color.rgb(255,255,255))
            }
        }
        return vh
    }

    // 3
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.contentTitle.text = mListData[position].test

        if (mListData[position].test2 == true) {
            holder.contentTitle.setBackgroundColor(Color.rgb(127, 127, 255))       }
    }

    // 4
    override fun getItemCount() = mListData.size
}
