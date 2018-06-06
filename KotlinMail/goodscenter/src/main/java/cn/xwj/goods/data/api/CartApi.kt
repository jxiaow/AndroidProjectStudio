package cn.xwj.goods.data.api

import cn.xwj.baselibrary.data.protocol.BaseResp
import cn.xwj.goods.data.protocol.AddCartReq
import cn.xwj.goods.data.protocol.CartGoods
import cn.xwj.goods.data.protocol.DeleteCartReq
import cn.xwj.goods.data.protocol.SubmitCartReq
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
        获取购物车列表
     */
    @POST("cart/getList")
    fun getCartList(): Observable<BaseResp<MutableList<CartGoods>?>>

    /*
        添加商品到购物车
     */
    @POST("cart/add")
    fun addCart(@Body req: AddCartReq): Observable<BaseResp<Int>>

    /*
        删除购物车商品
     */
    @POST("cart/delete")
    fun deleteCartList(@Body req: DeleteCartReq): Observable<BaseResp<String>>

    /*
        提交购物车商品
     */
    @POST("cart/submit")
    fun submitCart(@Body req: SubmitCartReq): Observable<BaseResp<Int>>
}
