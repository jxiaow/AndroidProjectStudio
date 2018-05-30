package cn.xwj.usercenter.presenter

import android.content.Context
import cn.xwj.baselibrary.ext.execute
import cn.xwj.baselibrary.presenter.BasePresenter
import cn.xwj.baselibrary.rx.BaseSubscriber
import cn.xwj.usercenter.data.respository.UserDataSource
import cn.xwj.usercenter.presenter.view.ForgetPwdView
import javax.inject.Inject

/**
 * Author: xw
 * Date: 2018-05-25 15:06:12
 * Description: RegisterPresenter: 注册Presenter.
 */
class ForgetPwdPresenter @Inject constructor() : BasePresenter<ForgetPwdView>() {

    @Inject
    lateinit var context: Context

    @Inject
    lateinit var repository: UserDataSource

    fun forgetPwd(mobile: String, verifyCode: String) {
        mView.showLoading()
        repository.forgetPwd(mobile, verifyCode)
                .execute(object : BaseSubscriber<Boolean>(mView) {
                    override fun onNext(t: Boolean) {
                        if (t) {
                            mView.onForgetPwdResult("验证成功")
                        }
                    }
                }, lifecycleOwner)
    }

}