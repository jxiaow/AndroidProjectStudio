package cn.xwj.usercenter.presenter

import android.content.Context
import cn.xwj.baselibrary.presenter.BasePresenter
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

    fun register(mobile: String, password: String, verifyCode: String) {

    }

    fun sendVerifyCode(mobile: String) {
        context.toast("请求发送成功")
    }
}