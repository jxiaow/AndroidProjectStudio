package cn.xwj.order.ui.activity


import android.os.Bundle
import cn.xwj.baselibrary.ui.activity.BaseActivity
import cn.xwj.order.R
import cn.xwj.order.common.OrderConstant
import cn.xwj.order.common.OrderStatus
import cn.xwj.order.ui.adapter.OrderVpAdapter
import cn.xwj.provider.common.RoutePath
import com.alibaba.android.arouter.facade.annotation.Route
import kotlinx.android.synthetic.main.activity_order.*

/**
 * Author: xw
 * Date: 2018-06-06 15:26:22
 * Description: OrderActivity: .
 */
/*
    订单Activity
    主要包括不同订单状态的Fragment
 */
class OrderActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        initView()
    }

    /*
        初始化视图
     */
    private fun initView() {
        mOrderVp.adapter = OrderVpAdapter(supportFragmentManager, this)
        mOrderTab.setupWithViewPager(mOrderVp)

        //根据订单状态设置当前页面
        mOrderVp.currentItem = intent.getIntExtra(OrderConstant.KEY_ORDER_STATUS, OrderStatus.ORDER_ALL)
    }
}
