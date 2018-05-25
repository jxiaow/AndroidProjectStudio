package cn.xwj.baselibrary.di.component

import android.content.Context
import cn.xwj.baselibrary.di.module.AppModule
import dagger.BindsInstance
import dagger.Component

/**
 * Author: xw
 * Date: 2018-05-25 16:37:15
 * Description: AppComponent: .
 */
@Component(modules = [AppModule::class])
interface AppComponent {

    fun context(): Context

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }
}