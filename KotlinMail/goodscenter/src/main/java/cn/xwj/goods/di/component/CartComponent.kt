package cn.xwj.goods.di.component

import cn.xwj.baselibrary.di.component.ActivityComponent
import cn.xwj.baselibrary.di.scope.PerComponentScope
import cn.xwj.goods.di.module.CartModule
import cn.xwj.goods.ui.fragment.CartFragment
import cn.xwj.goods.ui.fragment.CategoryFragment
import dagger.Component

/**
 * Author: xw
 * Email:i.xiaowujiang@gmail.com
 * Date: 2018-06-03 2018/6/3
 * Description: CategoryComponent
 */
@PerComponentScope
@Component(dependencies = [ActivityComponent::class],
        modules = [CartModule::class])
interface CartComponent {
    fun inject(cartFragment: CartFragment)
}