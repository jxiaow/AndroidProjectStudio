package cn.xwj.goods.ui.fragment

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import cn.xwj.baselibrary.ui.activity.BaseActivity
import cn.xwj.baselibrary.ui.fragment.BaseMvpFragment
import cn.xwj.baselibrary.utils.BannerImageLoader
import cn.xwj.goods.R
import cn.xwj.goods.R.id.*
import cn.xwj.goods.common.GoodsConstants
import cn.xwj.goods.data.protocol.Goods
import cn.xwj.goods.di.module.GoodsModule
import cn.xwj.goods.presenter.GoodsDetailPresenter
import cn.xwj.goods.presenter.view.GoodsDetailView
import cn.xwj.goods.ui.activity.GoodsDetailActivity
import cn.xwj.goods.ui.widget.GoodsSkuPopView
import cn.xwj.goods.utils.YuanFenConverter
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.fragment_goods_detail_tab_one.*

/**
 * Author: xw
 * Date: 2018-06-05 11:29:24
 * Description: GoodsDetailTabOneFragment: .
 */
class GoodsDetailTabOneFragment : BaseMvpFragment<GoodsDetailPresenter>(), GoodsDetailView {

    private var mCurrentGoods: Goods? = null
    private lateinit var mPopView: GoodsSkuPopView
    //SKU弹层出场动画
    private lateinit var mAnimationStart: Animation
    //SKU弹层退场动画
    private lateinit var mAnimationEnd: Animation

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_goods_detail_tab_one, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initAnim()
        initSkuPop()
        loadData()
    }

    private fun initAnim() {
        mAnimationStart = ScaleAnimation(
                1f, 0.95f, 1f, 0.95f, Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        mAnimationStart.duration = 500
        mAnimationStart.fillAfter = true

        mAnimationEnd = ScaleAnimation(
                0.95f, 1f, 0.95f, 1f, Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        mAnimationEnd.duration = 500
        mAnimationEnd.fillAfter = true
    }

    private fun initView() {
        initBanner()
        //sku弹层
        mSkuView.setOnClickListener {
            mPopView.showAtLocation((activity as GoodsDetailActivity).contentView
                    , Gravity.BOTTOM and Gravity.CENTER_HORIZONTAL,
                    0, 0)
            (activity as BaseActivity).contentView.startAnimation(mAnimationStart)
        }
    }

    private fun initSkuPop() {
        mPopView = GoodsSkuPopView(activity!!)
        mPopView.setOnDismissListener {
            (activity as BaseActivity).contentView.startAnimation(mAnimationEnd)
        }
    }

    private fun loadData() {
        mPresenter.getGoodsDetail(activity!!.intent
                .getIntExtra(GoodsConstants.EXTRA_GOODS_ID, -1))
    }

    private fun initBanner() {
        mGoodsDetailBanner.setImageLoader(BannerImageLoader())
        mGoodsDetailBanner.setDelayTime(2000)
        mGoodsDetailBanner.setBannerAnimation(Transformer.ZoomOutSlide)
        mGoodsDetailBanner.setIndicatorGravity(BannerConfig.RIGHT)
    }


    override fun onGetGoodsDetailResult(result: Goods) {
        mCurrentGoods = result

        mGoodsDetailBanner.setImages(result.goodsBanner.split(","))
        mGoodsDetailBanner.start()

        mGoodsPriceTv.text = YuanFenConverter.changeF2YWithUnit(result.goodsDefaultPrice)
        mGoodsDescTv.text = result.goodsDesc
        mSkuSelectedTv.text = result.goodsDefaultSku
    }

    override fun onResume() {
        super.onResume()
        mGoodsDetailBanner.startAutoPlay()
    }

    override fun onPause() {
        super.onPause()
        mGoodsDetailBanner.stopAutoPlay()
    }


    override fun injectComponent() {
        DaggerGoodsComponent.builder().goodsModule(GoodsModule(this))
                .activityComponent(mActivityComponent)
                .build().inject(this)
    }

}