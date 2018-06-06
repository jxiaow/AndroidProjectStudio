package cn.xwj.order.data.protocol

/**
 * Author: xw
 * Date: 2018-06-06 15:49:15
 * Description: OrderGoods: .
 */
/*
   订单中的商品
 */
data class OrderGoods(
        val id: Int,
        var goodsId: Int,
        val goodsDesc: String,
        val goodsIcon: String,
        val goodsPrice: Long,
        val goodsCount: Int,
        val goodsSku: String,
        val orderId: Int
)
