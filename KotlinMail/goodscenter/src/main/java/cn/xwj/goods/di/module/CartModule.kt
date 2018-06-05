package cn.xwj.goods.di.module

import cn.xwj.baselibrary.di.scope.PerComponentScope
import cn.xwj.goods.data.repository.CartDataSource
import cn.xwj.goods.data.repository.CartRepository
import cn.xwj.goods.presenter.view.CartListView
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

    private lateinit var cartListView: CartListView

    constructor(cartListView: CartListView){
        this.cartListView = cartListView
    }

    @Provides
    @PerComponentScope
    fun provideCartRepository(cartRepository: CartRepository): CartDataSource {
        return cartRepository
    }


    @Provides
    @PerComponentScope
    fun provideCartListView(cartListView: CartListView): CartListView {
        return cartListView
    }
}