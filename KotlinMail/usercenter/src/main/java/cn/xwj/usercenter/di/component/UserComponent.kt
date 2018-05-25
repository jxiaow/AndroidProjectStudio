package cn.xwj.usercenter.di.component

import cn.xwj.usercenter.di.module.UserModule
import cn.xwj.usercenter.ui.activity.RegisterActivity
import dagger.Component

/**
 * Author: xw
 * Date: 2018-05-25 15:54:53
 * Description: UserComponent: .
 */
@Component(modules = [UserModule::class])
interface UserComponent {

    fun inject(registerActivity: RegisterActivity)
}