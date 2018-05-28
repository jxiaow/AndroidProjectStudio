package cn.xwj.usercenter.presenter.view

import cn.xwj.baselibrary.presenter.view.BaseView

/**
 * Author: xw
 * Date: 2018-05-25 15:05:47
 * Description: RegisterView: .
 */
interface RegisterView : BaseView {

    fun onRegisterResult(result: String)

    fun onSendVerifyCode(result: Boolean)

}