package cn.xwj.order.presenter

import cn.xwj.baselibrary.ext.execute
import cn.xwj.baselibrary.presenter.BasePresenter
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
        orderRepository.getOrderList(orderStatus)
                .execute(mView, lifecycleOwner) {
                    mView.onGetOrderListResult(it)
                }
    }

    /*
        订单确认收货
     */
    fun confirmOrder(orderId: Int) {
        mView.showLoading()
        orderRepository.confirmOrder(orderId).execute(mView, lifecycleOwner) {
            mView.onConfirmOrderResult(it!!)
        }

    }

    /*
        取消订单
     */
    fun cancelOrder(orderId: Int) {
        mView.showLoading()
        orderRepository.cancelOrder(orderId).execute(mView, lifecycleOwner) {
            mView.onCancelOrderResult(it!!)
        }

    }


}
