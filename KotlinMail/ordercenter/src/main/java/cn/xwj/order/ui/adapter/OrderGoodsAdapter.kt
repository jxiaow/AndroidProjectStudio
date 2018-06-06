package cn.xwj.order.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.xwj.baselibrary.ext.loadUrl
import cn.xwj.baselibrary.ui.adapter.BaseRecyclerViewAdapter
import cn.xwj.baselibrary.utils.YuanFenConverter
import cn.xwj.order.R
import cn.xwj.order.data.protocol.OrderGoods
import kotlinx.android.synthetic.main.layout_order_goods_item.view.*

/**
 * Author: xw
 * Date: 2018-06-06 16:50:39
 * Description: OrderGoodsAdapter: .
 */
/*
    订单中商品列表
 */
class OrderGoodsAdapter(val context: Context)
    : BaseRecyclerViewAdapter<OrderGoods, OrderGoodsAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context)
                .inflate(R.layout.layout_order_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val model = dataList[position]

        holder.itemView.mGoodsIconIv.loadUrl(model.goodsIcon)
        holder.itemView.mGoodsDescTv.text = model.goodsDesc
        holder.itemView.mGoodsSkuTv.text = model.goodsSku
        holder.itemView.mGoodsPriceTv.text = YuanFenConverter.changeF2YWithUnit(model.goodsPrice)
        holder.itemView.mGoodsCountTv.text = "x${model.goodsCount}"

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

}
