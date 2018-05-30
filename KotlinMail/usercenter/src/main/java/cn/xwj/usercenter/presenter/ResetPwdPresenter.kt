package cn.xwj.usercenter.presenter

import android.content.Context
import cn.xwj.baselibrary.ext.execute
import cn.xwj.baselibrary.presenter.BasePresenter
import cn.xwj.baselibrary.rx.BaseSubscriber
import cn.xwj.usercenter.data.respository.UserDataSource
import cn.xwj.usercenter.presenter.view.ResetPwdView
import javax.inject.Inject

/**
 * Author: xw
 * Date: 2018-05-25 15:06:12
 * Description: RegisterPresenter: 注册Presenter.
 */
class ResetPwdPresenter @Inject constructor() : BasePresenter<ResetPwdView>() {

    @Inject
    lateinit var context: Context

    @Inject
    lateinit var repository: UserDataSource

    fun resetPwd(mobile: String, pwd: String) {
        mView.showLoading()
        repository.resetPwd(mobile, pwd)
                .execute(object : BaseSubscriber<Boolean>(mView) {
                    override fun onNext(t: Boolean) {
                        if (t) {
                            mView.onResetPwdResult("密码重置成功")
                        }
                    }
                }, lifecycleOwner)
    }

}