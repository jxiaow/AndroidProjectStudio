package cn.xwj.baselibrary.di.component

import android.app.Activity
import android.content.Context
import cn.xwj.baselibrary.di.module.ActivityModule
import cn.xwj.baselibrary.di.scope.ActivityScope
import dagger.Component

/**
 * Author: xw
 * Date: 2018-05-25 17:01:06
 * Description: ActivityComponent: .
 */
@ActivityScope
@Component(modules = [ActivityModule::class], dependencies = [AppComponent::class])
interface ActivityComponent {

    fun activity(): Activity

    fun context(): Context

}