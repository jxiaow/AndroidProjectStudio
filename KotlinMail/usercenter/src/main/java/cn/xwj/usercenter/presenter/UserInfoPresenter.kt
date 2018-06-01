package cn.xwj.usercenter.presenter

import android.content.Context
import cn.xwj.baselibrary.ext.execute
import cn.xwj.baselibrary.presenter.BasePresenter
import cn.xwj.baselibrary.rx.BaseSubscriber
import cn.xwj.usercenter.data.protocol.UserInfo
import cn.xwj.usercenter.data.respository.UploadDataSource
import cn.xwj.usercenter.data.respository.UserDataSource
import cn.xwj.usercenter.presenter.view.UserInfoView
import cn.xwj.usercenter.utils.UserPrefsUtils
import javax.inject.Inject

/**
 * Author: xw
 * Date: 2018-05-25 15:06:12
 * Description: RegisterPresenter: 注册Presenter.
 */
class UserInfoPresenter @Inject constructor() : BasePresenter<UserInfoView>() {

    @Inject
    lateinit var context: Context

    @Inject
    lateinit var repository: UserDataSource

    @Inject
    lateinit var uploadRepository: UploadDataSource


    fun getUploadToken() {
        mView.showLoading()
        uploadRepository.getUploadToken()
                .execute(object : BaseSubscriber<String>(mView) {
                    override fun onNext(t: String) {
                        mView.onGetUploadTokenResult(t)
                    }
                }, lifecycleOwner)
    }

    fun editUser(userIcon: String, userName: String, userGender: String, userSign: String) {
        mView.showLoading()
        repository.editUser(userIcon, userName, userGender, userSign)
                .execute(object : BaseSubscriber<UserInfo>(mView) {
                    override fun onNext(t: UserInfo) {
                        UserPrefsUtils.putUserInfo(userInfo = t)
                        mView.onEditUserResult("修改用户信息成功")
                    }
                }, lifecycleOwner)
    }

}