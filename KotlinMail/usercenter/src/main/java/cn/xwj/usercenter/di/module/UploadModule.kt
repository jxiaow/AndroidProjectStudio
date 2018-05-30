package cn.xwj.usercenter.di.module

import cn.xwj.baselibrary.di.scope.PerComponentScope
import cn.xwj.usercenter.data.respository.UploadDataSource
import cn.xwj.usercenter.data.respository.UploadRepository
import dagger.Module
import dagger.Provides

/**
 * Author: xw
 * Date: 2018-05-30 16:13:41
 * Description: UploadModule: .
 */
@Module
class UploadModule {


    @PerComponentScope
    @Provides
    fun provideUploadRepository(uploadRepository: UploadRepository): UploadDataSource {
        return uploadRepository
    }
}