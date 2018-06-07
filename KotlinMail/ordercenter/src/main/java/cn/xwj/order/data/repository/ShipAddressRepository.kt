package cn.xwj.order.data.repository

import cn.xwj.baselibrary.data.net.RetrofitFactory
import cn.xwj.baselibrary.ext.convert
import cn.xwj.baselibrary.ext.convertBoolean
import cn.xwj.order.data.api.ShipAddressApi
import cn.xwj.order.data.protocol.AddShipAddressReq
import cn.xwj.order.data.protocol.DeleteShipAddressReq
import cn.xwj.order.data.protocol.EditShipAddressReq
import cn.xwj.order.data.protocol.ShipAddress
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Author: xw
 * Date: 2018-06-06 15:52:13
 * Description: OrderRepository: .
 */
class ShipAddressRepository @Inject constructor() : ShipAddressDataSource {
    override fun addShipAddress(shipUserName: String, shipUserMobile: String,
                                shipAddress: String): Observable<Boolean> {
        return RetrofitFactory.instance.create(ShipAddressApi::class.java)
                .addShipAddress(AddShipAddressReq(shipUserName, shipUserMobile, shipAddress))
                .convertBoolean()
    }

    override fun getShipAddressList(): Observable<MutableList<ShipAddress>?> {
        return RetrofitFactory.instance.create(ShipAddressApi::class.java)
                .getShipAddressList()
                .convert()
    }

    override fun editShipAddress(address: ShipAddress): Observable<Boolean> {
        return RetrofitFactory.instance.create(ShipAddressApi::class.java)
                .editShipAddress(EditShipAddressReq(address.id, address.shipUserName,
                        address.shipUserMobile, address.shipAddress, address.shipIsDefault))
                .convertBoolean()
    }

    override fun deleteShipAddress(id: Int): Observable<Boolean> {
        return RetrofitFactory.instance.create(ShipAddressApi::class.java)
                .deleteShipAddress(DeleteShipAddressReq(id))
                .convertBoolean()
    }


}