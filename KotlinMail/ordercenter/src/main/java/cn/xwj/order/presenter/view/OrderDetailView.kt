package cn.xwj.order.presenter.view

import cn.xwj.baselibrary.presenter.view.BaseView
import cn.xwj.order.data.protocol.Order

/**
 * Author: xw
 * Date: 2018-06-07 15:07:40
 * Description: OrderDetailView: .
 */
/*
    订单详情页 视图回调
 */
interface OrderDetailView : BaseView {

    fun onGetOrderByIdResult(result: Order)
}
