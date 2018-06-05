package cn.xwj.goods.ui.activity

import android.os.Bundle
import cn.xwj.baselibrary.ui.activity.BaseActivity
import cn.xwj.goods.R
import cn.xwj.goods.ui.adapter.GoodsDetailVpAdapter
import kotlinx.android.synthetic.main.activity_goods_detail.*

/**
 * Author: xw
 * Date: 2018-06-04 12:50:10
 * Description: GoodsActivity: .
 */

class GoodsDetailActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goods_detail)
        initView()

    }


    private fun initView() {
        mGoodsDetailVp.adapter = GoodsDetailVpAdapter(supportFragmentManager, this)
        mGoodsDetailTab.setupWithViewPager(mGoodsDetailVp)

        mLeftIv.setOnClickListener { finish() }
    }

}