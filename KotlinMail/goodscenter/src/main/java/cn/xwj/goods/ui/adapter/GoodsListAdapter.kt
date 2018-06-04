package cn.xwj.goods.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.xwj.baselibrary.ext.loadUrl
import cn.xwj.baselibrary.ui.adapter.BaseRecyclerViewAdapter
import cn.xwj.goods.R
import cn.xwj.goods.data.protocol.Goods
import cn.xwj.goods.utils.YuanFenConverter
import kotlinx.android.synthetic.main.layout_goods_item.view.*

/**
 * Author: xw
 * Date: 2018-06-04 14:12:11
 * Description: GoodsListAdapter: .
 */
class GoodsListAdapter : BaseRecyclerViewAdapter<Goods, RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_goods_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val model = dataList[position]
        with(holder.itemView) {
            mGoodsIconIv.loadUrl(model.goodsDefaultIcon)
            mGoodsDescTv.text = model.goodsDesc
            mGoodsPriceTv.text = YuanFenConverter.changeF2YWithUnit(model.goodsDefaultPrice)
            mGoodsSalesStockTv.text = "销量${model.goodsSalesCount}件 库存${model.goodsStockCount}"
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}