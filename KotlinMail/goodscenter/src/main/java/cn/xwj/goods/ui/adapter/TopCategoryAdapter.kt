package cn.xwj.goods.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.xwj.baselibrary.ui.adapter.BaseRecyclerViewAdapter
import cn.xwj.goods.R
import cn.xwj.goods.data.protocol.Category
import kotlinx.android.synthetic.main.layout_top_category_item.view.*

/**
 * Author: xw
 * Email:i.xiaowujiang@gmail.com
 * Date: 2018-06-03 2018/6/3
 * Description: TopCategoryAdapter
 */
class TopCategoryAdapter : BaseRecyclerViewAdapter<Category, TopCategoryAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_top_category_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val model = dataList[position]
        with(holder.itemView) {
            mTopCategoryNameTv.text = model.categoryName
            mTopCategoryNameTv.isSelected = model.isSelected
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}