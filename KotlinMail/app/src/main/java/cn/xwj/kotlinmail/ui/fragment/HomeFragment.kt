package cn.xwj.kotlinmail.ui.fragment

import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.xwj.baselibrary.ui.fragment.BaseFragment
import cn.xwj.kotlinmail.R
import cn.xwj.kotlinmail.ui.adapter.HomeDiscountAdapter
import cn.xwj.kotlinmail.ui.adapter.TopicAdapter
import cn.xwj.kotlinmail.utils.BannerImageLoader
import com.kotlin.mall.common.MainConstant.Companion.HOME_BANNER_FOUR
import com.kotlin.mall.common.MainConstant.Companion.HOME_BANNER_ONE
import com.kotlin.mall.common.MainConstant.Companion.HOME_BANNER_THREE
import com.kotlin.mall.common.MainConstant.Companion.HOME_BANNER_TWO
import com.kotlin.mall.common.MainConstant.Companion.HOME_DISCOUNT_FIVE
import com.kotlin.mall.common.MainConstant.Companion.HOME_DISCOUNT_FOUR
import com.kotlin.mall.common.MainConstant.Companion.HOME_DISCOUNT_ONE
import com.kotlin.mall.common.MainConstant.Companion.HOME_DISCOUNT_THREE
import com.kotlin.mall.common.MainConstant.Companion.HOME_DISCOUNT_TWO
import com.kotlin.mall.common.MainConstant.Companion.HOME_TOPIC_FIVE
import com.kotlin.mall.common.MainConstant.Companion.HOME_TOPIC_FOUR
import com.kotlin.mall.common.MainConstant.Companion.HOME_TOPIC_ONE
import com.kotlin.mall.common.MainConstant.Companion.HOME_TOPIC_THREE
import com.kotlin.mall.common.MainConstant.Companion.HOME_TOPIC_TWO
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.fragment_home.*
import me.crosswall.lib.coverflow.CoverFlow
import org.jetbrains.anko.support.v4.toast

/**
 * Author: xw
 * Date: 2018-06-01 14:34:17
 * Description: HomeFragment: .
 */
class HomeFragment : BaseFragment(), View.OnClickListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initBanner()
        initNews()
        initDiscount()
        initTopic()
    }

    private fun initTopic() {

        mTopicPager.adapter = TopicAdapter(activity!!, mutableListOf(HOME_TOPIC_ONE,
                HOME_TOPIC_TWO, HOME_TOPIC_THREE, HOME_TOPIC_FOUR, HOME_TOPIC_FIVE))

        mTopicPager.currentItem = 1
        mTopicPager.offscreenPageLimit = 5

        CoverFlow.Builder().with(mTopicPager)
                .scale(0.3f)
                .pagerMargin(-30.0f)
                .spaceSize(0f)
                .build()

    }

    private fun initDiscount() {
        val manager = LinearLayoutManager(activity)
        manager.orientation = LinearLayoutManager.HORIZONTAL
        mHomeDiscountRv.layoutManager = manager

        val discountAdapter = HomeDiscountAdapter()
        mHomeDiscountRv.itemAnimator = DefaultItemAnimator()
        mHomeDiscountRv.adapter = discountAdapter
        discountAdapter.setData(mutableListOf(HOME_DISCOUNT_ONE, HOME_DISCOUNT_TWO, HOME_DISCOUNT_THREE,
                HOME_DISCOUNT_FOUR, HOME_DISCOUNT_FIVE))
    }

    private fun initNews() {
        mNewsFlipperView.setData(arrayOf("夏日炎炎，第一波福利还有30秒到达战场", "新用户立领1000元优惠券"))
    }

    private fun initBanner() {
        mHomeBanner.setImageLoader(BannerImageLoader())
        mHomeBanner.setImages(listOf(HOME_BANNER_ONE, HOME_BANNER_TWO, HOME_BANNER_THREE, HOME_BANNER_FOUR))
        mHomeBanner.setBannerAnimation(Transformer.Accordion)
        mHomeBanner.setDelayTime(2000)
        mHomeBanner.setIndicatorGravity(BannerConfig.RIGHT)
        mHomeBanner.start()
    }

    override fun onResume() {
        super.onResume()
        mHomeBanner.startAutoPlay()
    }

    override fun onPause() {
        super.onPause()
        mHomeBanner.stopAutoPlay()
    }


    private fun initView() {
        mScanIv.setOnClickListener {
            toast(R.string.coming_soon_tip)
        }
        mSearchEt.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.mScanIv -> toast(R.string.coming_soon_tip)
            R.id.mSearchEt -> {

            }
        }
    }
}