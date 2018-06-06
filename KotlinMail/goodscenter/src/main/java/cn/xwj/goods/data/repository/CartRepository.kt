package cn.xwj.goods.data.repository

import cn.xwj.baselibrary.data.net.RetrofitFactory
import cn.xwj.baselibrary.ext.convert
import cn.xwj.baselibrary.ext.convertBoolean
import cn.xwj.goods.data.api.CartApi
import cn.xwj.goods.data.protocol.AddCartReq
import cn.xwj.goods.data.protocol.CartGoods
import cn.xwj.goods.data.protocol.DeleteCartReq
import cn.xwj.goods.data.protocol.SubmitCartReq
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Author: xw
 * Email:i.xiaowujiang@gmail.com
 * Date: 2018-06-03 2018/6/3
 * Description: GoodsRepository
 */
class CartRepository @Inject constructor() : CartDataSource {
    override fun getCartList(): Observable<MutableList<CartGoods>?> {
        return RetrofitFactory.instance.create(CartApi::class.java)
                .getCartList()
                .convert()
    }

    override fun deleteCartList(list: List<Int>): Observable<Boolean> {
        return RetrofitFactory.instance.create(CartApi::class.java)
                .deleteCartList(DeleteCartReq(list))
                .convertBoolean()

    }

    override fun submitCart(list: MutableList<CartGoods>, totalPrice: Long): Observable<Int> {
        return RetrofitFactory.instance.create(CartApi::class.java)
                .submitCart(SubmitCartReq(list, totalPrice))
                .convert()
    }

    override fun addCart(goodsId: Int, goodsDesc: String, goodsIcon: String,
                         goodsPrice: Long, goodsCount: Int, goodsSku: String): Observable<Int> {
        return RetrofitFactory.instance.create(CartApi::class.java)
                .addCart(AddCartReq(goodsId, goodsDesc, goodsIcon, goodsPrice, goodsCount, goodsSku))
                .convert()
    }
}