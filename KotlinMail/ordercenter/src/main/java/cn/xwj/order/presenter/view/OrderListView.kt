package cn.xwj.order.presenter.view

import cn.xwj.baselibrary.presenter.view.BaseView
import cn.xwj.order.data.protocol.Order

/**
 * Author: xw
 * Date: 2018-06-06 15:47:44
 * Description: OrderListView: .
 */
/*
    订单列表 视图回调
 */
interface OrderListView : BaseView {

    //获取订单列表回调
    fun onGetOrderListResult(result:MutableList<Order>?)
    //确认订单回调
    fun onConfirmOrderResult(result:Boolean)
    //取消订单回调
    fun onCancelOrderResult(result:Boolean)

}
