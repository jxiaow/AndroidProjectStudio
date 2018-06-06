package cn.xwj.goods.data.repository

import cn.xwj.goods.data.protocol.CartGoods
import io.reactivex.Observable

/**
 * Author: xw
 * Email:i.xiaowujiang@gmail.com
 * Date: 2018-06-03 2018/6/3
 * Description: CategoryDataSource
 */
interface CartDataSource {
    fun addCart(goodsId: Int, goodsDesc: String, goodsIcon: String, goodsPrice: Long,
                goodsCount: Int, goodsSku: String): Observable<Int>

    fun getCartList(): Observable<MutableList<CartGoods>?>
    fun deleteCartList(list: List<Int>): Observable<Boolean>
    fun submitCart(list: MutableList<CartGoods>, totalPrice: Long): Observable<Int>
}