package cn.xwj.usercenter.di.component

import cn.xwj.baselibrary.di.component.ActivityComponent
import cn.xwj.baselibrary.di.scope.PerComponentScope
import cn.xwj.baselibrary.presenter.view.BaseView
import cn.xwj.usercenter.di.module.UserModule
import cn.xwj.usercenter.presenter.view.RegisterView
import cn.xwj.usercenter.ui.activity.RegisterActivity
import dagger.BindsInstance
import dagger.Component

/**
 * Author: xw
 * Date: 2018-05-25 15:54:53
 * Description: UserComponent: .
 */
@PerComponentScope
@Component(modules = [UserModule::class], dependencies = [ActivityComponent::class])
interface UserComponent {

    fun inject(registerActivity: RegisterActivity)
}