package cn.xwj.goods.ui.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import cn.xwj.goods.ui.fragment.GoodsDetailTabOneFragment
import cn.xwj.goods.ui.fragment.GoodsDetailTabTwoFragment

/**
 * Author: xw
 * Date: 2018-06-05 11:24:11
 * Description: GoodsDetailVpAdapter: .
 */
class GoodsDetailVpAdapter(private val supportManager: FragmentManager, val context: Context)
    : FragmentPagerAdapter(supportManager) {

    private val titles = arrayListOf("商品", "详情")

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> GoodsDetailTabOneFragment()
            else -> GoodsDetailTabTwoFragment()
        }
    }

    override fun getCount(): Int = titles.size

    override fun getPageTitle(position: Int): CharSequence? = titles[position]
}