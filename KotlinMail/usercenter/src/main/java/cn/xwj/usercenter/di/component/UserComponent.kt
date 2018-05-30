package cn.xwj.usercenter.di.component

import cn.xwj.baselibrary.di.component.ActivityComponent
import cn.xwj.baselibrary.di.scope.PerComponentScope
import cn.xwj.usercenter.di.module.UploadModule
import cn.xwj.usercenter.di.module.UserModule
import cn.xwj.usercenter.ui.activity.*
import dagger.Component

/**
 * Author: xw
 * Date: 2018-05-25 15:54:53
 * Description: UserComponent: .
 */
@PerComponentScope
@Component(modules = [UserModule::class, UploadModule::class], dependencies = [ActivityComponent::class])
interface UserComponent {

    fun inject(registerActivity: RegisterActivity)
    fun inject(loginActivity: LoginActivity)
    fun inject(forgetPwdActivity: ForgetPwdActivity)
    fun inject(resetPwdActivity: ResetPwdActivity)
    fun inject(userInfoActivity: UserInfoActivity)
}