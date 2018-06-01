package cn.xwj.kotlinmail.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.xwj.baselibrary.ui.fragment.BaseFragment
import cn.xwj.kotlinmail.R
import cn.xwj.kotlinmail.ui.utils.BannerImageLoader
import com.kotlin.mall.common.MainConstant.Companion.HOME_BANNER_FOUR
import com.kotlin.mall.common.MainConstant.Companion.HOME_BANNER_ONE
import com.kotlin.mall.common.MainConstant.Companion.HOME_BANNER_THREE
import com.kotlin.mall.common.MainConstant.Companion.HOME_BANNER_TWO
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * Author: xw
 * Date: 2018-06-01 14:34:17
 * Description: HomeFragment: .
 */
class HomeFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initBanner()
        initNews()
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

    }
}