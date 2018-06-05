package cn.xwj.goods.data.api

import cn.xwj.baselibrary.data.protocol.BaseResp
import cn.xwj.goods.data.protocol.AddCartReq
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Author: xw
 * Date: 2018-06-05 17:32:14
 * Description: CartApi: .
 */
/*
    购物车 接口
 */
interface CartApi {

    /*
        添加商品到购物车
     */
    @POST("cart/add")
    fun addCart(@Body req: AddCartReq): Observable<BaseResp<Int>>

}
