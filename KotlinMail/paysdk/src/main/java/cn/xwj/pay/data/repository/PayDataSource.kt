package cn.xwj.pay.data.repository

import io.reactivex.Observable

/**
 * Author: xw
 * Date: 2018-06-07 15:40:07
 * Description: PayDataSource: .
 */
interface PayDataSource {
    /*
 获取支付宝支付签名
*/
    fun getPaySign(orderId: Int, totalPrice: Long): Observable<String>

    /*
    刷新订单状态已支付
 */
    fun payOrder(orderId: Int): Observable<Boolean>
}