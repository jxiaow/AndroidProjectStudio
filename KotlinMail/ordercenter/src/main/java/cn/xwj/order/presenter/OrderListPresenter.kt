package cn.xwj.order.presenter

import cn.xwj.baselibrary.ext.execute
import cn.xwj.baselibrary.presenter.BasePresenter
import cn.xwj.baselibrary.rx.BaseSubscriber
import cn.xwj.order.data.protocol.Order
import cn.xwj.order.data.repository.OrderDataSource
import cn.xwj.order.presenter.view.OrderListView
import javax.inject.Inject

/**
 * Author: xw
 * Date: 2018-06-06 15:47:33
 * Description: OrderListPresenter: .
 */
/*
    订单列表Presenter
 */
class OrderListPresenter @Inject constructor() : BasePresenter<OrderListView>() {

    @Inject
    lateinit var orderRepository: OrderDataSource

    /*
        根据订单状态获取订单列表
     */
    fun getOrderList(orderStatus: Int) {

        mView.showLoading()
        orderRepository.getOrderList(orderStatus).execute(object : BaseSubscriber<MutableList<Order>?>(mView) {
            override fun onNext(t: MutableList<Order>?) {
                mView.onGetOrderListResult(t)
            }
        }, lifecycleOwner)

    }

    /*
        订单确认收货
     */
    fun confirmOrder(orderId: Int) {
        mView.showLoading()
        orderRepository.confirmOrder(orderId).execute(object : BaseSubscriber<Boolean>(mView) {
            override fun onNext(t: Boolean) {
                mView.onConfirmOrderResult(t)
            }
        }, lifecycleOwner)

    }

    /*
        取消订单
     */
    fun cancelOrder(orderId: Int) {
        mView.showLoading()
        orderRepository.cancelOrder(orderId).execute(object : BaseSubscriber<Boolean>(mView) {
            override fun onNext(t: Boolean) {
                mView.onCancelOrderResult(t)
            }
        }, lifecycleOwner)

    }


}
