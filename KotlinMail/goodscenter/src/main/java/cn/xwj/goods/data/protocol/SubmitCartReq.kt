package cn.xwj.goods.data.protocol

/**
 * Author: xw
 * Date: 2018-06-06 11:24:54
 * Description: SubmitCartReq: .
 */

/*
    提交购物车
 */
data class SubmitCartReq(val goodsList: List<CartGoods>,val totalPrice: Long)
