package cn.xwj.order.data.protocol

/**
 * Author: xw
 * Date: 2018-06-06 16:14:01
 * Description: GetOrderListReq: .
 */
/*
    根据订单状态查询查询订单列表
 */
data class GetOrderListReq(val orderStatus:Int)
