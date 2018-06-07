package cn.xwj.pay.data.api

import cn.xwj.baselibrary.data.protocol.BaseResp
import cn.xwj.pay.data.protocol.GetPaySignReq
import cn.xwj.pay.data.protocol.PayOrderReq
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Author: xw
 * Date: 2018-06-07 15:57:46
 * Description: PayApi: .
 */

/*
    支付 接口
 */
interface PayApi {

    /*
        获取支付宝支付签名
     */
    @POST("pay/getPaySign")
    fun getPaySign(@Body req: GetPaySignReq): Observable<BaseResp<String>>

    /*
        刷新订单状态，已支付
     */
    @POST("order/pay")
    fun payOrder(@Body req: PayOrderReq): Observable<BaseResp<String>>

}
