package cn.xwj.order.data.api

import cn.xwj.baselibrary.data.protocol.BaseResp
import cn.xwj.order.data.protocol.AddShipAddressReq
import cn.xwj.order.data.protocol.DeleteShipAddressReq
import cn.xwj.order.data.protocol.EditShipAddressReq
import cn.xwj.order.data.protocol.ShipAddress
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Author: xw
 * Date: 2018-06-07 11:37:17
 * Description: ShipAddressApi: .
 */
/*
    地址管理 接口
 */
interface ShipAddressApi {

    /*
        添加收货地址
     */
    @POST("shipAddress/add")
    fun addShipAddress(@Body req: AddShipAddressReq): Observable<BaseResp<String>>

    /*
        删除收货地址
     */
    @POST("shipAddress/delete")
    fun deleteShipAddress(@Body req: DeleteShipAddressReq): Observable<BaseResp<String>>

    /*
        修改收货地址
     */
    @POST("shipAddress/modify")
    fun editShipAddress(@Body req: EditShipAddressReq): Observable<BaseResp<String>>

    /*
        查询收货地址列表
     */
    @POST("shipAddress/getList")
    fun getShipAddressList(): Observable<BaseResp<MutableList<ShipAddress>?>>

}
