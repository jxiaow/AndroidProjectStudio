package cn.xwj.order.data.repository

import cn.xwj.baselibrary.data.net.RetrofitFactory
import cn.xwj.baselibrary.ext.convert
import cn.xwj.baselibrary.ext.convertBoolean
import cn.xwj.order.data.api.OrderApi
import cn.xwj.order.data.protocol.*
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Author: xw
 * Date: 2018-06-06 15:52:13
 * Description: OrderRepository: .
 */
class OrderRepository @Inject constructor() : OrderDataSource {
    override fun getOrderById(orderId: Int): Observable<Order> {
        return RetrofitFactory.instance.create(OrderApi::class.java)
                .getOrderById(GetOrderByIdReq(orderId))
                .convert()
    }

    override fun submitOrder(order: Order): Observable<Boolean> {
        return RetrofitFactory.instance.create(OrderApi::class.java)
                .submitOrder(SubmitOrderReq(order))
                .convertBoolean()
    }

    override fun getOrderList(orderStatus: Int): Observable<MutableList<Order>?> {
        return RetrofitFactory.instance.create(OrderApi::class.java)
                .getOrderList(GetOrderListReq(orderStatus))
                .convert()
    }

    override fun cancelOrder(orderId: Int): Observable<Boolean> {
        return RetrofitFactory.instance.create(OrderApi::class.java)
                .cancelOrder(CancelOrderReq(orderId))
                .convertBoolean()
    }

    override fun confirmOrder(orderId: Int): Observable<Boolean> {
        return RetrofitFactory.instance.create(OrderApi::class.java)
                .confirmOrder(ConfirmOrderReq(orderId))
                .convertBoolean()
    }
}