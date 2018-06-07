package cn.xwj.order.data.protocol

/**
 * Author: xw
 * Date: 2018-06-07 11:37:39
 * Description: AddShipAddressReq: .
 */

/*
    添加收货地址
 */
data class AddShipAddressReq(val shipUserName: String, val shipUserMobile: String, val shipAddress: String)
