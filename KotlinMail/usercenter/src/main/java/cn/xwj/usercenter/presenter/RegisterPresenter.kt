package cn.xwj.usercenter.presenter

import android.arch.lifecycle.LifecycleRegistryOwner
import android.content.Context
import cn.xwj.baselibrary.ext.execute
import cn.xwj.baselibrary.presenter.BasePresenter
import cn.xwj.baselibrary.rx.BaseSubscriber
import cn.xwj.usercenter.data.respository.UserDataSource
import cn.xwj.usercenter.presenter.view.RegisterView
import org.jetbrains.anko.toast
import javax.inject.Inject

/**
 * Author: xw
 * Date: 2018-05-25 15:06:12
 * Description: RegisterPresenter: 注册Presenter.
 */
class RegisterPresenter @Inject constructor() : BasePresenter<RegisterView>() {

    @Inject
    lateinit var context: Context

    @Inject
    lateinit var repository: UserDataSource

    fun register(mobile: String, password: String, verifyCode: String) {
        mView.showLoading()
        repository.register(mobile, password, verifyCode)
                .execute(object : BaseSubscriber<Boolean>(mView) {
                    override fun onNext(t: Boolean) {
                        if(t) mView.onRegisterResult("注册成功")
                    }
                }, lifecycleOwner)
    }

    fun sendVerifyCode(mobile: String) {
        context.toast("请求发送成功")
    }
}