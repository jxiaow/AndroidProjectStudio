package cn.xwj.usercenter.data.respository

import cn.xwj.baselibrary.data.net.RetrofitFactory
import cn.xwj.baselibrary.ext.convert
import cn.xwj.baselibrary.ext.convertBoolean
import cn.xwj.usercenter.data.api.UserApi
import cn.xwj.usercenter.data.protocol.*
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Author: xw
 * Email:i.xiaowujiang@gmail.com
 * Date: 2018-05-28 2018/5/28
 * Description: UserRepository
 */
class UserRepository @Inject constructor() : UserDataSource {
    override fun editUser(userIcon: String, userName: String, userGender: String, userSign: String): Observable<UserInfo> {
        return RetrofitFactory.instance.create(UserApi::class.java)
                .editUser(EditUserReq(userIcon, userName, userGender, userSign))
                .convert()
    }

    override fun resetPwd(mobile: String, pwd: String): Observable<Boolean> {
        return RetrofitFactory.instance.create(UserApi::class.java)
                .resetPwd(ResetPwdReq(mobile, pwd))
                .convertBoolean()
    }

    override fun forgetPwd(mobile: String, verifyCode: String): Observable<Boolean> {
        return RetrofitFactory.instance.create(UserApi::class.java)
                .forgetPwd(ForgetPwdReq(mobile, verifyCode))
                .convertBoolean()
    }

    override fun login(mobile: String, pwd: String, pushId: String): Observable<UserInfo> {
        return RetrofitFactory.instance.create(UserApi::class.java)
                .login(LoginReq(mobile, pwd, pushId))
                .convert()

    }

    override fun register(mobile: String, pwd: String, verifyCode: String): Observable<Boolean> {
        return RetrofitFactory.instance.create(UserApi::class.java)
                .register(RegisterReq(mobile, pwd, verifyCode))
                .convertBoolean()
    }

}