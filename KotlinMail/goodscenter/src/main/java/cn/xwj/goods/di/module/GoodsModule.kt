package cn.xwj.goods.di.module

import cn.xwj.baselibrary.di.scope.PerComponentScope
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
class GoodsModule {

    private lateinit var goodsListView: GoodsListView
    private lateinit var goodsDetailView: GoodsDetailView

    constructor(goodsListView: GoodsListView) {
        this.goodsListView = goodsListView
    }

    constructor(goodsDetailView: GoodsDetailView) {
        this.goodsDetailView = goodsDetailView
    }

    @Provides
    @PerComponentScope
    fun provideGoodsListView(): GoodsListView = goodsListView

    @Provides
    @PerComponentScope
    fun provideGoodsDetailView(): GoodsDetailView = goodsDetailView

    @Provides
    @PerComponentScope
    fun provideGoodsListRepository(goodsRepository: GoodsRepository): GoodsDataSource {
        return goodsRepository
    }
}