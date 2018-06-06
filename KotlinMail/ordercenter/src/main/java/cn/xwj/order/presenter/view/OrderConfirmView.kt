package cn.xwj.order.presenter.view

import cn.xwj.baselibrary.presenter.view.BaseView
import cn.xwj.order.data.protocol.Order

/**
 * Author: xw
 * Date: 2018-06-06 16:50:00
 * Description: OrderConfirmView: .
 */
/*
    订单确认页 视图回调
 */
interface OrderConfirmView : BaseView {

    //获取订单回调
    fun onGetOrderByIdResult(result: Order)
    //提交订单回调
    fun onSubmitOrderResult(result:Boolean)
}
