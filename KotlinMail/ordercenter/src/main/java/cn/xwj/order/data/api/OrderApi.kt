package cn.xwj.order.data.api

import cn.xwj.baselibrary.data.protocol.BaseResp
import cn.xwj.order.data.protocol.*
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Author: xw
 * Date: 2018-06-06 16:11:48
 * Description: OrderApi: .
 */

/*
    订单 接口
 */
interface OrderApi {

    /*
        取消订单
     */
    @POST("order/cancel")
    fun cancelOrder(@Body req: CancelOrderReq): Observable<BaseResp<String>>

    /*
        确认订单
     */
    @POST("order/confirm")
    fun confirmOrder(@Body req: ConfirmOrderReq): Observable<BaseResp<String>>

    /*
        根据ID获取订单
     */
    @POST("order/getOrderById")
    fun getOrderById(@Body req: GetOrderByIdReq): Observable<BaseResp<Order>>

    /*
        根据订单状态查询查询订单列表
     */
    @POST("order/getOrderList")
    fun getOrderList(@Body req: GetOrderListReq): Observable<BaseResp<MutableList<Order>?>>

    /*
        提交订单
     */
    @POST("order/submitOrder")
    fun submitOrder(@Body req: SubmitOrderReq): Observable<BaseResp<String>>

}
