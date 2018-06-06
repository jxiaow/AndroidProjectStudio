package cn.xwj.order.data.repository

import cn.xwj.order.data.protocol.Order
import io.reactivex.Observable

/**
 * Author: xw
 * Date: 2018-06-06 15:50:40
 * Description: OrderDataSource: .
 */
/*
    订单业务 接口
 */
interface OrderDataSource {

    /*
       根据ID查询订单
    */
    fun getOrderById(orderId: Int): Observable<Order>

    /*
    提交订单
 */
    fun submitOrder(order: Order): Observable<Boolean>

    /*
    根据状态查询订单列表
 */
    fun getOrderList(orderStatus: Int): Observable<MutableList<Order>?>

    /*
    取消订单
 */
    fun cancelOrder(orderId: Int): Observable<Boolean>

    /*
        确认订单
     */
    fun confirmOrder(orderId: Int): Observable<Boolean>
}
