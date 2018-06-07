package cn.xwj.order.ui.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import cn.xwj.baselibrary.ui.activity.BaseMvpActivity
import cn.xwj.baselibrary.utils.YuanFenConverter
import cn.xwj.order.R
import cn.xwj.order.data.protocol.Order
import cn.xwj.order.di.component.DaggerOrderComponent
import cn.xwj.order.di.module.OrderModule
import cn.xwj.order.presenter.OrderDetailPresenter
import cn.xwj.order.presenter.view.OrderDetailView
import cn.xwj.order.ui.adapter.OrderGoodsAdapter
import cn.xwj.provider.common.RoutePath
import com.alibaba.android.arouter.facade.annotation.Route
import com.kotlin.provider.common.ProviderConstant
import kotlinx.android.synthetic.main.activity_order_detail.*
import kotlinx.android.synthetic.main.notification_template_lines_media.view.*

/**
 * Author: xw
 * Date: 2018-06-06 16:36:21
 * Description: OrderDetailActivity: .
 */
/*
    订单详情
 */
@Route(path = RoutePath.MessageCenter.PATH_MESSAGE_ORDER)
class OrderDetailActivity : BaseMvpActivity<OrderDetailPresenter>(), OrderDetailView {
    override fun initPerComponent() {
        DaggerOrderComponent.builder()
                .activityComponent(activityComponent)
                .orderModule(OrderModule(this)).build()
                .inject(this)
    }

    private lateinit var mAdapter: OrderGoodsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_detail)

        initView()
        loadData()
    }

    /*
        初始化视图
     */
    private fun initView() {
        mOrderGoodsRv.layoutManager = LinearLayoutManager(this)
        mAdapter = OrderGoodsAdapter(this)
        mOrderGoodsRv.adapter = mAdapter
    }

    /*
        加载数据
     */
    private fun loadData() {
        mPresenter.getOrderById(intent.getIntExtra(ProviderConstant.KEY_ORDER_ID, -1))
    }

    /*
        获取订单回调
     */
    override fun onGetOrderByIdResult(result: Order) {
        mShipNameTv.setContentText(result.shipAddress!!.shipUserName)
        mShipMobileTv.setContentText(result.shipAddress!!.shipUserMobile)
        mShipAddressTv.setContentText(result.shipAddress!!.shipAddress)
        mTotalPriceTv.setContentText(YuanFenConverter.changeF2YWithUnit(result.totalPrice))

        mAdapter.setData(result.orderGoodsList)
    }

}
