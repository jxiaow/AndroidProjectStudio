package cn.xwj.order.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.xwj.baselibrary.ext.startLoading
import cn.xwj.baselibrary.ui.adapter.BaseRecyclerViewAdapter
import cn.xwj.baselibrary.ui.fragment.BaseMvpFragment
import cn.xwj.order.R
import cn.xwj.order.common.OrderConstant
import cn.xwj.order.data.protocol.Order
import cn.xwj.order.di.component.DaggerOrderComponent
import cn.xwj.order.di.module.OrderModule
import cn.xwj.order.presenter.OrderListPresenter
import cn.xwj.order.presenter.view.OrderListView
import cn.xwj.order.ui.activity.OrderDetailActivity
import cn.xwj.order.ui.adapter.OrderAdapter
import cn.xwj.provider.common.RoutePath
import com.alibaba.android.arouter.launcher.ARouter
import com.bigkoo.alertview.AlertView
import com.bigkoo.alertview.OnItemClickListener
import com.kennyc.view.MultiStateView
import com.kotlin.provider.common.ProviderConstant
import kotlinx.android.synthetic.main.fragment_order.*
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast

/**
 * Author: xw
 * Date: 2018-06-06 15:33:57
 * Description: OrderFragment: .
 */
/*
    订单列表Fragment
 */
class OrderFragment : BaseMvpFragment<OrderListPresenter>(), OrderListView {

    private lateinit var mAdapter: OrderAdapter

    /*
        Dagger注册
     */
    override fun injectComponent() {
        DaggerOrderComponent
                .builder()
                .activityComponent(mActivityComponent)
                .orderModule(OrderModule(this))
                .build()
                .inject(this)
        mPresenter.mView = this
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_order, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onStart() {
        super.onStart()
        loadData()
    }

    /*
        初始化视图
     */
    private fun initView() {
        mOrderRv.layoutManager = LinearLayoutManager(activity)
        mAdapter = OrderAdapter(activity!!)
        mOrderRv.adapter = mAdapter

        /*
            订单对应操作
         */
        mAdapter.listener = object : OrderAdapter.OnOptClickListener {
            override fun onOptClick(optType: Int, order: Order) {
                when (optType) {
                    OrderConstant.OPT_ORDER_PAY -> {
                        ARouter.getInstance().build(RoutePath.PaySDK.PATH_PAY)
                                .withInt(ProviderConstant.KEY_ORDER_ID, order.id)
                                .withLong(ProviderConstant.KEY_ORDER_PRICE, order.totalPrice)
                                .navigation()
                    }
                    OrderConstant.OPT_ORDER_CONFIRM -> {
                        mPresenter.confirmOrder(order.id)
                    }
                    OrderConstant.OPT_ORDER_CANCEL -> {
                        mPresenter.cancelOrder(order.id)
                        showCancelDialog(order)
                    }
                }
            }
        }

        /*
            列表单项点击事件
         */
        mAdapter.setOnItemOnClickListener(object : BaseRecyclerViewAdapter.OnItemClickListener<Order> {
            override fun onItemClick(item: Order, position: Int) {
                startActivity<OrderDetailActivity>(ProviderConstant.KEY_ORDER_ID to item.id)
            }
        })


    }

    /*
        取消订单对话框
     */
    private fun showCancelDialog(order: Order) {
        AlertView("取消订单", "确定取消该订单？", "取消", null, arrayOf("确定"),
                activity, AlertView.Style.Alert, OnItemClickListener { _, position ->
            if (position == 0) {
                mPresenter.cancelOrder(order.id)
            }
        }

        ).show()
    }

    /*
        加载数据
     */
    private fun loadData() {
        mMultiStateView.startLoading()
        mPresenter.getOrderList(arguments!!.getInt(OrderConstant.KEY_ORDER_STATUS, -1))
    }

    /*
        获取订单列表回调
     */
    override fun onGetOrderListResult(result: MutableList<Order>?) {
        if (result != null && result.size > 0) {
            mAdapter.setData(result)
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        } else {
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
    }

    /*
        订单确认收货回调
     */
    override fun onConfirmOrderResult(result: Boolean) {
        toast("确认收货成功")
        loadData()
    }

    /*
        取消订单回调
     */
    override fun onCancelOrderResult(result: Boolean) {
        toast("取消订单成功")
        loadData()
    }
}
