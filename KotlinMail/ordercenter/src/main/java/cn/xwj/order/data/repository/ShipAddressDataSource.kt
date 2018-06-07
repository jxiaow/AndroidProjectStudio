package cn.xwj.order.data.repository

import cn.xwj.order.data.protocol.ShipAddress
import io.reactivex.Observable

/**
 * Author: xw
 * Date: 2018-06-06 15:50:40
 * Description: OrderDataSource: .
 */
/*
    订单业务 接口
 */
interface ShipAddressDataSource {

    /*
        添加收货地址
     */
    fun addShipAddress(shipUserName: String, shipUserMobile: String, shipAddress: String): Observable<Boolean>

    /*
    获取收货地址列表
 */
    fun getShipAddressList(): Observable<MutableList<ShipAddress>?>

    /*
     修改收货地址
  */
    fun editShipAddress(address: ShipAddress): Observable<Boolean>

    /*
    删除收货地址
 */
    fun deleteShipAddress(id: Int): Observable<Boolean>

}
