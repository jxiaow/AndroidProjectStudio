package cn.xwj.kotlinmail.ui.adapter

import android.graphics.Paint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.xwj.baselibrary.ext.loadUrl
import cn.xwj.baselibrary.ui.adapter.BaseRecyclerViewAdapter
import cn.xwj.kotlinmail.R
import kotlinx.android.synthetic.main.layout_home_discount_item.view.*

/**
 * Author: xw
 * Email:i.xiaowujiang@gmail.com
 * Date: 2018-06-02 2018/6/2
 * Description: HomeDiscountAdapter
 */

class HomeDiscountAdapter : BaseRecyclerViewAdapter<String, HomeDiscountAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_home_discount_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        with(holder.itemView) {
            mGoodsIconIv.loadUrl(dataList[position])
            //静态假数据
            mDiscountAfterTv.text = "￥123.00"
            mDiscountBeforeTv.text = "$1000.00"
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        init {
            view.mDiscountBeforeTv.paint.flags = Paint.STRIKE_THRU_TEXT_FLAG
            view.mDiscountBeforeTv.paint.isAntiAlias = true
        }
    }

}