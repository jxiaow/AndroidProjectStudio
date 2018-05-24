package cn.xwj.kotlinforandroid

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.TextView

/**
 * Author: xw
 * Email:i.xiaowujiang@gmail.com
 * Date: 2018-05-15 2018/5/15
 * Description: ForecastListAdapter
 */
class ForecastListAdapter(val itemList: List<String>) : RecyclerView.Adapter<ForecastListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(TextView(parent.context))
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = itemList[position]
    }


    class ViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)
}