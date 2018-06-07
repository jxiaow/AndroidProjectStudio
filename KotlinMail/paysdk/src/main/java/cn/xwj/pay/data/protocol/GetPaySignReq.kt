package cn.xwj.pay.data.protocol

/**
 * Author: xw
 * Date: 2018-06-07 15:58:08
 * Description: GetPaySignReq: .
 */
/*
    获取支付宝 支付签名
 */
data class GetPaySignReq(val orderId: Int, val totalPrice: Long)
