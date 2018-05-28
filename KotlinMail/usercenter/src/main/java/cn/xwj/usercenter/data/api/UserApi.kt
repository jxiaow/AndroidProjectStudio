package cn.xwj.usercenter.data.api

import cn.xwj.baselibrary.data.protocol.BaseResp
import cn.xwj.usercenter.data.protocol.RegisterReq
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Author: xw
 * Email:i.xiaowujiang@gmail.com
 * Date: 2018-05-28 2018/5/28
 * Description: UserApi
 */
interface UserApi {

    @POST("/userCenter/register")
    fun register(@Body registerReq: RegisterReq): Observable<BaseResp<String>>
}