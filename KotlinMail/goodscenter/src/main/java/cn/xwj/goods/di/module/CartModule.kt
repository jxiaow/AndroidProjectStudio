package cn.xwj.goods.di.module

import cn.xwj.baselibrary.di.scope.PerComponentScope
import cn.xwj.goods.data.repository.CartDataSource
import cn.xwj.goods.data.repository.CartRepository
import cn.xwj.goods.data.repository.GoodsDataSource
import cn.xwj.goods.data.repository.GoodsRepository
import cn.xwj.goods.presenter.view.GoodsDetailView
import cn.xwj.goods.presenter.view.GoodsListView
import dagger.Module
import dagger.Provides

/**
 * Author: xw
 * Email:i.xiaowujiang@gmail.com
 * Date: 2018-06-03 2018/6/3
 * Description: GoodsModule
 */
@Module
class CartModule {

    @Provides
    @PerComponentScope
    fun provideCartRepository(cartRepository: CartRepository): CartDataSource {
        return cartRepository
    }
}