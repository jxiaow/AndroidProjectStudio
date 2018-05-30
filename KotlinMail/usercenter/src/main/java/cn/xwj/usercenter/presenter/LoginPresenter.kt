package cn.xwj.usercenter.presenter

import android.content.Context
import cn.xwj.baselibrary.ext.execute
import cn.xwj.baselibrary.presenter.BasePresenter
import cn.xwj.baselibrary.rx.BaseSubscriber
import cn.xwj.usercenter.data.protocol.UserInfo
import cn.xwj.usercenter.data.respository.UserDataSource
import cn.xwj.usercenter.presenter.view.LoginView
import javax.inject.Inject

/**
 * Author: xw
 * Date: 2018-05-25 15:06:12
 * Description: RegisterPresenter: 注册Presenter.
 */
class LoginPresenter @Inject constructor() : BasePresenter<LoginView>() {

    @Inject
    lateinit var context: Context

    @Inject
    lateinit var repository: UserDataSource

    fun login(mobile: String, password: String, pushId: String) {
        mView.showLoading()
        repository.login(mobile, password, pushId)
                .execute(object : BaseSubscriber<UserInfo>(mView) {
                    override fun onNext(t: UserInfo) {
                        mView.onLoginResult("登录成功")
                    }
                }, lifecycleOwner)
    }

}