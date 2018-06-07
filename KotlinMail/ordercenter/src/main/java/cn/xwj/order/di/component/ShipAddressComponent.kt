package cn.xwj.order.di.component

import cn.xwj.baselibrary.di.component.ActivityComponent
import cn.xwj.baselibrary.di.scope.PerComponentScope
import cn.xwj.order.di.module.ShipAddressModule
import cn.xwj.order.ui.activity.ShipAddressActivity
import cn.xwj.order.ui.activity.ShipAddressEditActivity
import dagger.Component

/**
 * Author: xw
 * Email:i.xiaowujiang@gmail.com
 * Date: 2018-06-03 2018/6/3
 * Description: CategoryComponent
 */
@PerComponentScope
@Component(dependencies = [ActivityComponent::class], modules = [ShipAddressModule::class])
interface ShipAddressComponent {
    fun inject(shipAddressActivity: ShipAddressActivity)
    fun inject(shipAddressEditActivity: ShipAddressEditActivity)
}