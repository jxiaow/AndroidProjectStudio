package cn.xwj.usercenter.di.module

import cn.xwj.baselibrary.di.scope.PerComponentScope
import cn.xwj.usercenter.data.respository.UserDataSource
import cn.xwj.usercenter.data.respository.UserRepository
import cn.xwj.usercenter.presenter.view.*
import dagger.Module
import dagger.Provides

/**
 * Author: xw
 * Date: 2018-05-25 15:55:20
 * Description: UserModule: .
 */
@Module
class UserModule {

    private lateinit var registerView: RegisterView
    private lateinit var loginView: LoginView
    private lateinit var forgetPwdView: ForgetPwdView
    private lateinit var resetPwdView: ResetPwdView
    private lateinit var userInfoView: UserInfoView


    constructor(view: LoginView) {
        loginView = view
    }

    constructor(view: ForgetPwdView) {
        forgetPwdView = view
    }

    constructor(view: ResetPwdView) {
        resetPwdView = view
    }

    constructor(view: RegisterView) {
        registerView = view
    }

    constructor(view: UserInfoView) {
        userInfoView = view
    }


    @PerComponentScope
    @Provides
    fun provideRegisterView(): RegisterView {
        return registerView
    }

    @PerComponentScope
    @Provides
    fun provideUserInfoView(): UserInfoView {
        return userInfoView
    }


    @PerComponentScope
    @Provides
    fun provideForgetPwdView(): ForgetPwdView {
        return forgetPwdView
    }


    @PerComponentScope
    @Provides
    fun provideResetPwdView(): ResetPwdView {
        return resetPwdView
    }


    @PerComponentScope
    @Provides
    fun provideLoginView(): LoginView {
        return loginView
    }


    @PerComponentScope
    @Provides
    fun provideRepository(userRepository: UserRepository): UserDataSource {
        return userRepository
    }
}