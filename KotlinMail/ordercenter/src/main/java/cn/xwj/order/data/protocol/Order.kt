package cn.xwj.order.data.protocol

/**
 * Author: xw
 * Date: 2018-06-06 15:48:17
 * Description: Order: .
 */
/*
    订单数据类
 */
data class Order(
        val id: Int,
        val payType: Int,
        var shipAddress: ShipAddress?,
        val totalPrice: Long,
        var orderStatus: Int,
        val orderGoodsList: MutableList<OrderGoods>
)
