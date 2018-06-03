package cn.xwj.goods.data.repository

import cn.xwj.goods.data.protocol.Category
import io.reactivex.Observable

/**
 * Author: xw
 * Email:i.xiaowujiang@gmail.com
 * Date: 2018-06-03 2018/6/3
 * Description: CategoryDataSource
 */
interface CategoryDataSource {
    fun getCategory(parentId: Int): Observable<MutableList<Category>?>
}