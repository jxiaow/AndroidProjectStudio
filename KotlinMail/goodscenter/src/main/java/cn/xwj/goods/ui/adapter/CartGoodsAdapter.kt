package cn.xwj.goods.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.xwj.baselibrary.ext.loadUrl
import cn.xwj.baselibrary.ui.adapter.BaseRecyclerViewAdapter
import cn.xwj.baselibrary.widget.DefaultTextWatcher
import cn.xwj.goods.R
import cn.xwj.goods.data.protocol.CartGoods
import cn.xwj.goods.event.CartAllCheckedEvent
import cn.xwj.goods.event.UpdateTotalPriceEvent
import cn.xwj.baselibrary.utils.YuanFenConverter
import com.eightbitlab.rxbus.Bus
import kotlinx.android.synthetic.main.layout_cart_goods_item.view.*
import org.jetbrains.anko.editText
import org.jetbrains.anko.sdk25.coroutines.onClick

/**
 * Author: xw
 * Date: 2018-06-06 10:01:24
 * Description: CartGoodsAdapter: .
 */
/*
    购物车数据适配器
 */
class CartGoodsAdapter(val context: Context)
    : BaseRecyclerViewAdapter<CartGoods, CartGoodsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context)
                .inflate(R.layout.layout_cart_goods_item,
                        parent,
                        false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val model = dataList[position]
        //是否选中
        holder.itemView.mCheckedCb.isChecked = model.isSelected
        //加载商品图片
        holder.itemView.mGoodsIconIv.loadUrl(model.goodsIcon)
        //商品描述
        holder.itemView.mGoodsDescTv.text = model.goodsDesc
        //商品SKU
        holder.itemView.mGoodsSkuTv.text = model.goodsSku
        //商品价格
        holder.itemView.mGoodsPriceTv.text = YuanFenConverter.changeF2YWithUnit(model.goodsPrice)
        //商品数量
        holder.itemView.mGoodsCountBtn.setCurrentNumber(model.goodsCount)
        //选中按钮事件
        holder.itemView.mCheckedCb.onClick {
            model.isSelected = holder.itemView.mCheckedCb.isChecked
            val isAllChecked = dataList.all {it.isSelected }
            Bus.send(CartAllCheckedEvent(isAllChecked))
            notifyDataSetChanged()
        }

        //商品数量变化监听
        holder.itemView.mGoodsCountBtn.editText().addTextChangedListener(object: DefaultTextWatcher(){
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                model.goodsCount = s.toString().toInt()
                Bus.send(UpdateTotalPriceEvent())
            }
        })

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
