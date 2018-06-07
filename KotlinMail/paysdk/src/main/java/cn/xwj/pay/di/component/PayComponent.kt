package cn.xwj.pay.di.component

import cn.xwj.baselibrary.di.component.ActivityComponent
import cn.xwj.baselibrary.di.scope.PerComponentScope
import cn.xwj.pay.di.module.PayModule
import cn.xwj.pay.ui.activity.CashRegisterActivity
import dagger.Component

/**
 * Author: xw
 * Date: 2018-06-07 15:51:58
 * Description: PayComponent: .
 */
@PerComponentScope
@Component(dependencies = [ActivityComponent::class],
        modules = [PayModule::class])
interface PayComponent {
    fun inject(cashRegisterActivity: CashRegisterActivity)
}