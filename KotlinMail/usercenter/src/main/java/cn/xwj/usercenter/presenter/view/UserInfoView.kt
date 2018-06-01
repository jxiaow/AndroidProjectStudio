package cn.xwj.usercenter.presenter.view

import cn.xwj.baselibrary.presenter.view.BaseView

/**
 * Author: xw
 * Date: 2018-05-25 15:05:47
 * Description: RegisterView: .
 */
interface UserInfoView : BaseView {

    fun onGetUploadTokenResult(result: String)
    fun onEditUserResult(result: String)
}