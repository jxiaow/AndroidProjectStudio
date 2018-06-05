package cn.xwj.goods.di.component

import cn.xwj.baselibrary.di.component.ActivityComponent
import cn.xwj.baselibrary.di.scope.PerComponentScope
import cn.xwj.goods.di.module.GoodsModule
import cn.xwj.goods.ui.activity.GoodsActivity
import cn.xwj.goods.ui.fragment.GoodsDetailTabOneFragment
import dagger.Component

/**
 * Author: xw
 * Email:i.xiaowujiang@gmail.com
 * Date: 2018-06-03 2018/6/3
 * Description: CategoryComponent
 */
@PerComponentScope
@Component(dependencies = [ActivityComponent::class], modules = [GoodsModule::class])
interface GoodsComponent {

    fun inject(goodsActivity: GoodsActivity)
    fun inject(goodsDetailTabOneFragment: GoodsDetailTabOneFragment)
}