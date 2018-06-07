package cn.xwj.order.di.module

import cn.xwj.baselibrary.di.scope.PerComponentScope
import cn.xwj.order.data.repository.OrderDataSource
import cn.xwj.order.data.repository.OrderRepository
import cn.xwj.order.presenter.view.OrderConfirmView
import cn.xwj.order.presenter.view.OrderDetailView
import cn.xwj.order.presenter.view.OrderListView
import dagger.Module
import dagger.Provides

/**
 * Author: xw
 * Email:i.xiaowujiang@gmail.com
 * Date: 2018-06-03 2018/6/3
 * Description: GoodsModule
 */
@Module
class OrderModule {

    private lateinit var orderListView: OrderListView
    private lateinit var orderConfirmView: OrderConfirmView
    private lateinit var orderDetailView: OrderDetailView

    constructor(orderConfirmView: OrderConfirmView) {
        this.orderConfirmView = orderConfirmView
    }

    constructor(orderDetailView: OrderDetailView) {
        this.orderDetailView = orderDetailView
    }

    constructor(orderListView: OrderListView) {
        this.orderListView = orderListView
    }

    @Provides
    @PerComponentScope
    fun provideOrderRepository(orderRepository: OrderRepository): OrderDataSource {
        return orderRepository
    }


    @Provides
    @PerComponentScope
    fun provideOrderListView(): OrderListView {
        return orderListView
    }

    @Provides
    @PerComponentScope
    fun provideOrderConfirmView(): OrderConfirmView {
        return orderConfirmView
    }

    @Provides
    @PerComponentScope
    fun provideOrderDetailView(): OrderDetailView {
        return orderDetailView
    }
}