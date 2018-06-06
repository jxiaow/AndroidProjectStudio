package cn.xwj.order.ui.adapter

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import cn.xwj.order.common.OrderConstant
import cn.xwj.order.ui.fragment.OrderFragment

/**
 * Author: xw
 * Date: 2018-06-06 15:33:06
 * Description: OrderVpAdapter: .
 */
/*
    订单Tab对应ViewPager
 */
class OrderVpAdapter(fm: FragmentManager, context: Context)
    : FragmentPagerAdapter(fm) {

    private val titles = arrayOf("全部", "待付款", "待收货", "已完成", "已取消")

    override fun getItem(position: Int): Fragment {
        val fragment = OrderFragment()
        val bundle = Bundle()
        bundle.putInt(OrderConstant.KEY_ORDER_STATUS, position)
        fragment.arguments = bundle
        return fragment

    }

    override fun getCount(): Int {
        return titles.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return titles[position]
    }
}
