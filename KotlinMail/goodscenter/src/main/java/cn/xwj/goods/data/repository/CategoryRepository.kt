package cn.xwj.goods.data.repository

import cn.xwj.baselibrary.data.net.RetrofitFactory
import cn.xwj.baselibrary.ext.convert
import cn.xwj.goods.data.api.CategoryApi
import cn.xwj.goods.data.protocol.Category
import cn.xwj.goods.data.protocol.GetCategoryReq
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Author: xw
 * Email:i.xiaowujiang@gmail.com
 * Date: 2018-06-03 2018/6/3
 * Description: CategoryRepository
 */
class CategoryRepository @Inject constructor() : CategoryDataSource {
    override fun getCategory(parentId: Int): Observable<MutableList<Category>?> {

        return RetrofitFactory.instance.create(CategoryApi::class.java)
                .getCategory(GetCategoryReq(parentId))
                .convert()
    }
}