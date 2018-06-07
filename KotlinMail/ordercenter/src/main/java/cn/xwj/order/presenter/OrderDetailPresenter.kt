package cn.xwj.order.presenter

import cn.xwj.baselibrary.ext.execute
import cn.xwj.baselibrary.presenter.BasePresenter
import cn.xwj.order.data.repository.OrderRepository
import cn.xwj.order.presenter.view.OrderDetailView
import javax.inject.Inject

/**
 * Author: xw
 * Date: 2018-06-07 12:50:51
 * Description: EditShipAddressPresenter: .
 */
/*
    编辑收货人信息Presenter
 */
class OrderDetailPresenter @Inject constructor() : BasePresenter<OrderDetailView>() {

    @Inject
    lateinit var orderRepository: OrderRepository

    /*
        根据ID查询订单
     */
    fun getOrderById(orderId: Int) {
        mView.showLoading()
        orderRepository.getOrderById(orderId)
                .execute(mView, lifecycleOwner) {
                    mView.onGetOrderByIdResult(it!!)
                }

    }

}
