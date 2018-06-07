package cn.xwj.pay.data.repository

import cn.xwj.baselibrary.data.net.RetrofitFactory
import cn.xwj.baselibrary.ext.convert
import cn.xwj.baselibrary.ext.convertBoolean
import cn.xwj.pay.data.api.PayApi
import cn.xwj.pay.data.protocol.GetPaySignReq
import cn.xwj.pay.data.protocol.PayOrderReq
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Author: xw
 * Date: 2018-06-07 15:56:09
 * Description: PayRepository: .
 */
class PayRepository @Inject constructor() : PayDataSource {
    override fun getPaySign(orderId: Int, totalPrice: Long): Observable<String> {

        return RetrofitFactory.instance.create(PayApi::class.java)
                .getPaySign(GetPaySignReq(orderId, totalPrice))
                .convert()
    }

    override fun payOrder(orderId: Int): Observable<Boolean> {
        return RetrofitFactory.instance.create(PayApi::class.java)
                .payOrder(PayOrderReq(orderId))
                .convertBoolean()
    }
}