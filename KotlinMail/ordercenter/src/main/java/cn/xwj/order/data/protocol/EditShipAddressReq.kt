package cn.xwj.order.data.protocol

/**
 * Author: xw
 * Date: 2018-06-07 11:38:31
 * Description: EditShipAddressReq: .
 */
/*
    修改收货地址
 */
data class EditShipAddressReq(val id:Int,val shipUserName:String,val shipUserMobile:String,
                              val shipAddress:String,val shipIsDefault:Int)
