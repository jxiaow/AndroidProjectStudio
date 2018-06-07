package cn.xwj.order.presenter.view

import cn.xwj.baselibrary.presenter.view.BaseView
import cn.xwj.order.data.protocol.ShipAddress

/**
 * Author: xw
 * Date: 2018-06-07 11:33:28
 * Description: ShipAddressView: .
 */
/*
    收货人列表 视图回调
 */
interface ShipAddressView : BaseView {

    //获取收货人列表回调
    fun onGetShipAddressResult(result: MutableList<ShipAddress>?)
    //设置默认收货人回调
    fun onSetDefaultResult(result: Boolean)
    //删除收货人回调
    fun onDeleteResult(result: Boolean)

}
