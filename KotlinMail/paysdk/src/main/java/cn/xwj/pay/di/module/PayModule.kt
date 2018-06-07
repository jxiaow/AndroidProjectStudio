package cn.xwj.pay.di.module

import cn.xwj.baselibrary.di.scope.PerComponentScope
import cn.xwj.pay.data.repository.PayDataSource
import cn.xwj.pay.data.repository.PayRepository
import cn.xwj.pay.presenter.view.PayView
import dagger.Module
import dagger.Provides

/**
 * Author: xw
 * Date: 2018-06-07 15:53:04
 * Description: PayModule: .
 */
@Module
class PayModule(private val payView: PayView) {

    @Provides
    @PerComponentScope
    fun providePayView(): PayView = payView


    @Provides
    @PerComponentScope
    fun providePayRepository(payRepository: PayRepository): PayDataSource = payRepository


}