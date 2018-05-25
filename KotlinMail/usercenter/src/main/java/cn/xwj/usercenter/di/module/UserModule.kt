package cn.xwj.usercenter.di.module

import dagger.Module
import dagger.Provides

/**
 * Author: xw
 * Date: 2018-05-25 15:55:20
 * Description: UserModule: .
 */
@Module
class UserModule(val code: Int) {

    @Provides
    fun provideCode(): Int {
        return code
    }

}