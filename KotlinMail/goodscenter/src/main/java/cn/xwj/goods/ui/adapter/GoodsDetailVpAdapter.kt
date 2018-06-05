package cn.xwj.goods.ui.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import cn.xwj.goods.ui.fragment.GoodsDetailTabOneFragment
import cn.xwj.goods.ui.fragment.GoodsDetailTabTwoFragment

/**
 * Author: xw
 * Date: 2018-06-05 16:26:15
 * Description: GoodsDetailVpAdapter: .
 */
/*
    商品详情ViewPager Adapter
 */
class GoodsDetailVpAdapter(fm: FragmentManager, context: Context)
    : FragmentPagerAdapter(fm) {

    private val titles = arrayOf("商品", "详情")

    override fun getItem(position: Int): Fragment {
        return if (position == 0) {
            GoodsDetailTabOneFragment()
        } else {
            GoodsDetailTabTwoFragment()
        }
    }

    override fun getCount(): Int {
        return titles.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return titles[position]
    }
}
