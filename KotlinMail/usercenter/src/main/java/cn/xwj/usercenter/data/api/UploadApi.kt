package cn.xwj.usercenter.data.api

import cn.xwj.baselibrary.data.protocol.BaseResp
import io.reactivex.Observable
import retrofit2.http.POST

/**
 * Author: xw
 * Date: 2018-05-30 16:17:04
 * Description: UploadApi: .
 */
interface UploadApi {

    @POST("common/getUploadToken")
    fun getUploadToken(): Observable<BaseResp<String>>

}