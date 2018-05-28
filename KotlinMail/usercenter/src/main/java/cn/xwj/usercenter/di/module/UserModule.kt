package cn.xwj.usercenter.di.module

import cn.xwj.baselibrary.di.scope.PerComponentScope
import cn.xwj.baselibrary.presenter.view.BaseView
import cn.xwj.usercenter.presenter.view.RegisterView
import dagger.Module
import dagger.Provides

/**
 * Author: xw
 * Date: 2018-05-25 15:55:20
 * Description: UserModule: .
 */
@Module
class UserModule {
    private val registerView: RegisterView

    constructor(view: RegisterView) {
        registerView = view
    }


    @PerComponentScope
    @Provides
    fun provideRegisterView(): RegisterView {
        return registerView
    }

}