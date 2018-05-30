package cn.xwj.usercenter.data.respository

import cn.xwj.usercenter.data.protocol.UserInfo
import io.reactivex.Observable

/**
 * Author: xw
 * Email:i.xiaowujiang@gmail.com
 * Date: 2018-05-28 2018/5/28
 * Description: UserDataSource
 */
interface UserDataSource {
    fun register(mobile: String, pwd: String, verifyCode: String): Observable<Boolean>
    fun login(mobile: String, pwd: String, pushId: String): Observable<UserInfo>
    fun forgetPwd(mobile: String, verifyCode: String): Observable<Boolean>
    fun resetPwd(mobile: String, pwd: String): Observable<Boolean>
}