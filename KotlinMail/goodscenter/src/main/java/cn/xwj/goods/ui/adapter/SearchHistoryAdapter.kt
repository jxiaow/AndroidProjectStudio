package cn.xwj.goods.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.xwj.baselibrary.ui.adapter.BaseRecyclerViewAdapter
import cn.xwj.goods.R
import kotlinx.android.synthetic.main.layout_search_history_item.view.*

/**
 * Author: xw
 * Email:i.xiaowujiang@gmail.com
 * Date: 2018-06-04 2018/6/4
 * Description: SearchHistoryAdapter
 */
class SearchHistoryAdapter : BaseRecyclerViewAdapter<String, SearchHistoryAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_search_history_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.itemView.mSearchHistoryTv.text = dataList[position]
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}