package cn.xwj.baselibrary.di.module

import android.arch.lifecycle.LifecycleOwner
import dagger.Module
import dagger.Provides

/**
 * Author: xw
 * Date: 2018-05-29 11:45:39
 * Description: LifecycleOwnerModule: .
 */
@Module
class LifecycleOwnerModule(private val lifecycleOwner: LifecycleOwner) {

    @Provides
    fun provideLifecycleOwner(): LifecycleOwner {
        return lifecycleOwner
    }
}