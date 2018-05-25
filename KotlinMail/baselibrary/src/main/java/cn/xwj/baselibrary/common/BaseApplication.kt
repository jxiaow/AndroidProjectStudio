package cn.xwj.baselibrary.common

import android.app.Application
import cn.xwj.baselibrary.di.component.DaggerAppComponent

/**
 * Author: xw
 * Date: 2018-05-25 15:21:42
 * Description: BaseApplication: .
 */
open class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initAppInjection()
    }

    private fun initAppInjection() {
        val appComponent = DaggerAppComponent.
                builder()
                .context(this)
                .build()
    }

}