package cn.xwj.order.di.component

import cn.xwj.baselibrary.di.component.ActivityComponent
import cn.xwj.baselibrary.di.scope.PerComponentScope
import cn.xwj.order.di.module.OrderModule
import cn.xwj.order.ui.fragment.OrderFragment
import com.kotlin.order.ui.activity.OrderConfirmActivity
import dagger.Component

/**
 * Author: xw
 * Email:i.xiaowujiang@gmail.com
 * Date: 2018-06-03 2018/6/3
 * Description: CategoryComponent
 */
@PerComponentScope
@Component(dependencies = [ActivityComponent::class], modules = [OrderModule::class])
interface OrderComponent {
    fun inject(orderFragment: OrderFragment)
    fun inject(orderConfirmActivity: OrderConfirmActivity)
}