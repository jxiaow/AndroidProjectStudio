package cn.xwj.baselibrary.common

import android.app.Application
import cn.xwj.baselibrary.di.component.AppComponent
import cn.xwj.baselibrary.di.component.DaggerAppComponent
import cn.xwj.baselibrary.utils.AppPreferences
import com.alibaba.android.arouter.launcher.ARouter

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
        AppPreferences.instance.init(this)

        ARouter.openLog()
        ARouter.openDebug()
        ARouter.init(this)
    }

    private fun initAppInjection() {
        appComponent = DaggerAppComponent.builder()
                .context(this)
                .build()
    }

}