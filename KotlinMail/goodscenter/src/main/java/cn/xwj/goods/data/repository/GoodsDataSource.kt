package cn.xwj.goods.data.repository

import cn.xwj.goods.data.protocol.Goods
import io.reactivex.Observable

/**
 * Author: xw
 * Email:i.xiaowujiang@gmail.com
 * Date: 2018-06-03 2018/6/3
 * Description: CategoryDataSource
 */
interface GoodsDataSource {
    fun getGoodsList(categoryId: Int, pageNo: Int): Observable<MutableList<Goods>?>
}