package cn.xwj.order.presenter

import cn.xwj.baselibrary.ext.execute
import cn.xwj.baselibrary.presenter.BasePresenter
import cn.xwj.baselibrary.rx.BaseSubscriber
import cn.xwj.order.data.protocol.Order
import cn.xwj.order.data.repository.OrderDataSource
import cn.xwj.order.presenter.view.OrderConfirmView
import javax.inject.Inject

/**
 * Author: xw
 * Date: 2018-06-06 16:48:55
 * Description: OrderConfirmPresenter: .
 */
/*
    订单确认页 Presenter
 */
class OrderConfirmPresenter @Inject constructor() : BasePresenter<OrderConfirmView>() {

    @Inject
    lateinit var orderRepository: OrderDataSource

    /*
        根据ID获取订单
     */
    fun getOrderById(orderId: Int) {
        mView.showLoading()
        orderRepository.getOrderById(orderId).execute(object : BaseSubscriber<Order>(mView) {
            override fun onComplete() {
                mView.onGetOrderByIdResult(mData!!)
            }
        }, lifecycleOwner)

    }

    /*
        提交订单
     */
    fun submitOrder(order: Order) {

        mView.showLoading()
        orderRepository.submitOrder(order).execute(object : BaseSubscriber<Boolean>(mView) {
            override fun onComplete() {
                mView.onSubmitOrderResult(mData!!)
            }
        }, lifecycleOwner)

    }


}
