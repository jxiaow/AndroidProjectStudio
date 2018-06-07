package cn.xwj.order.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.xwj.baselibrary.ui.adapter.BaseRecyclerViewAdapter
import cn.xwj.order.R
import cn.xwj.order.data.protocol.ShipAddress
import kotlinx.android.synthetic.main.layout_address_item.view.*

/**
 * Author: xw
 * Date: 2018-06-07 11:46:32
 * Description: ShipAddressAdapter: .
 */

/*
    收货地址数据适配
 */
class ShipAddressAdapter(val context: Context)
: BaseRecyclerViewAdapter<ShipAddress, ShipAddressAdapter.ViewHolder>() {

    var mOptClickListener:OnOptClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context)
                .inflate(R.layout.layout_address_item,
                        parent,
                        false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val model = dataList[position]
        with(holder.itemView){
            mSetDefaultTv.isSelected = model.shipIsDefault == 0
          mSetDefaultTv.isSelected = (model.shipIsDefault == 0)
           mShipNameTv.text = model.shipUserName + "    " + model.shipUserMobile
           mShipAddressTv.text = model.shipAddress

           mSetDefaultTv.setOnClickListener {
                mOptClickListener?.let {
                    if (holder.itemView.mSetDefaultTv.isSelected){
                        return@setOnClickListener
                    }
                    model.shipIsDefault = 0
                    it.onSetDefault(model)
                }
            }

           mEditTv.setOnClickListener {
                mOptClickListener?.let {
                    it.onEdit(model)
                }
            }
           mDeleteTv.setOnClickListener {
                mOptClickListener?.let {
                    it.onDelete(model)
                }
            }
        }
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    /*
        对应操作接口
     */
    interface OnOptClickListener{
        fun onSetDefault(address: ShipAddress)
        fun onEdit(address:ShipAddress)
        fun onDelete(address:ShipAddress)
    }
}
