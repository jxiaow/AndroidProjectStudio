package cn.xwj.goods.data.api

import cn.xwj.baselibrary.data.protocol.BaseResp
import cn.xwj.goods.data.protocol.Category
import cn.xwj.goods.data.protocol.GetCategoryReq
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Author: xw
 * Email:i.xiaowujiang@gmail.com
 * Date: 2018-06-03 2018/6/3
 * Description: CategoryApi
 */
interface CategoryApi {

    @POST("category/getCategory")
    fun getCategory(@Body req: GetCategoryReq): Observable<BaseResp<MutableList<Category>?>>
}