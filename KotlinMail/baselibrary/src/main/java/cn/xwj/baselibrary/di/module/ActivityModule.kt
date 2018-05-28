package cn.xwj.baselibrary.di.module

import android.app.Activity
import cn.xwj.baselibrary.di.scope.ActivityScope
import dagger.Module
import dagger.Provides

/**
 * Author: xw
 * Date: 2018-05-25 17:14:13
 * Description: ActivityModule: .
 */
@Module
class ActivityModule(private val activity: Activity) {

    @Provides
    @ActivityScope
    fun provideActivity(): Activity {
        return activity
    }

}