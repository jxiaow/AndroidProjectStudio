package cn.xwj.usercenter.data.respository

import cn.xwj.baselibrary.data.net.RetrofitFactory
import cn.xwj.baselibrary.ext.convertBoolean
import cn.xwj.usercenter.data.api.UserApi
import cn.xwj.usercenter.data.protocol.RegisterReq
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Author: xw
 * Email:i.xiaowujiang@gmail.com
 * Date: 2018-05-28 2018/5/28
 * Description: UserRepository
 */
class UserRepository @Inject constructor() : UserDataSource {
    override fun register(mobile: String, pwd: String, verifyCode: String): Observable<Boolean> {
        return RetrofitFactory.instance.create(UserApi::class.java)
                .register(RegisterReq(mobile, pwd, verifyCode))
                .convertBoolean()
    }

}