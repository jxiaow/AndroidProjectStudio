package cn.xwj.usercenter.data.api

import cn.xwj.baselibrary.data.protocol.BaseResp
import cn.xwj.usercenter.data.protocol.*
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

    @POST("userCenter/register")
    fun register(@Body registerReq: RegisterReq): Observable<BaseResp<String>>

    @POST("userCenter/login")
    fun login(@Body loginReq: LoginReq): Observable<BaseResp<UserInfo>>

    @POST("userCenter/forgetPwd")
    fun forgetPwd(@Body forgetPwdReq: ForgetPwdReq): Observable<BaseResp<Boolean>>

    @POST("userCenter/resetPwd")
    fun resetPwd(@Body resetPwdReq: ResetPwdReq): Observable<BaseResp<Boolean>>
}