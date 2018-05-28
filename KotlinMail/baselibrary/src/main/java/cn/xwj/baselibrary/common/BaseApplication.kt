package cn.xwj.baselibrary.common

import android.app.Application
import cn.xwj.baselibrary.di.component.AppComponent
import cn.xwj.baselibrary.di.component.DaggerAppComponent

/**
 * Author: xw
 * Date: 2018-05-25 15:21:42
 * Description: BaseApplication: .
 */
open class BaseApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        initAppInjection()
    }

    private fun initAppInjection() {
        appComponent = DaggerAppComponent.builder()
                .context(this)
                .build()
    }

}