package cn.xwj.baselibrary.ui.adapter

import android.support.v7.widget.RecyclerView

/**
 * Author: xw
 * Email:i.xiaowujiang@gmail.com
 * Date: 2018-06-02 2018/6/2
 * Description: BaseRecyclerViewAdapter
 */
abstract class BaseRecyclerViewAdapter<T : Any, VH : RecyclerView.ViewHolder>
    : RecyclerView.Adapter<VH>() {

    var dataList: MutableList<T> = mutableListOf()

    fun setData(source: MutableList<T>) {
        dataList = source
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.itemView.setOnClickListener {
            mOnItemOnClickListener?.onItemClick(dataList[position], position)
        }
    }


    override fun getItemCount(): Int = dataList.size

    private var mOnItemOnClickListener: OnItemClickListener<T>? = null

    fun setOnItemOnClickListener(listener: OnItemClickListener<T>) {
        this.mOnItemOnClickListener = listener
    }

    interface OnItemClickListener<in T> {
        fun onItemClick(item: T, position: Int)
    }
}