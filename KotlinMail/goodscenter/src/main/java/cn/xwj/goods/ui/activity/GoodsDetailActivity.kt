package cn.xwj.goods.ui.activity

import android.os.Bundle
import android.view.Gravity
import cn.xwj.baselibrary.ui.activity.BaseActivity
import cn.xwj.baselibrary.utils.AppPreferences
import cn.xwj.goods.R
import cn.xwj.goods.common.GoodsConstants
import cn.xwj.goods.event.AddCartEvent
import cn.xwj.goods.event.UpdateCartSizeEvent
import cn.xwj.goods.ui.adapter.GoodsDetailVpAdapter
import cn.xwj.provider.ext.afterLogin
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import kotlinx.android.synthetic.main.activity_goods_detail.*
import q.rorbin.badgeview.QBadgeView

/**
 * Author: xw
 * Email:i.xiaowujiang@gmail.com
 * Date: 2018-06-04 2018/6/4
 * Description: SearchGoodsActivity
 */
class GoodsDetailActivity : BaseActivity() {
    private lateinit var mCartBadge: QBadgeView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goods_detail)
        initView()
        initObserve()
        loadCartSize()
    }

    /*
       初始化视图
    */
    private fun initView() {
        mGoodsDetailVp.adapter = GoodsDetailVpAdapter(supportFragmentManager, this)
        //TabLayout关联ViewPager
        mGoodsDetailTab.setupWithViewPager(mGoodsDetailVp)

        mAddCartBtn.setOnClickListener {
            afterLogin {
                Bus.send(AddCartEvent())
            }
        }

        mEnterCartTv.setOnClickListener {
            //            startActivity<CartActivity>()
        }

        mLeftIv.setOnClickListener {
            finish()
        }

        mCartBadge = QBadgeView(this)
    }

    /*
        加载购物车数量
     */
    private fun loadCartSize() {
        setCartBadge()
    }

    /*
        监听购物车数量变化
     */
    private fun initObserve() {
        Bus.observe<UpdateCartSizeEvent>()
                .subscribe {
                    setCartBadge()
                }.registerInBus(this)
    }

    /*
        设置购物车标签
     */
    private fun setCartBadge() {
        mCartBadge.badgeGravity = Gravity.END or Gravity.TOP
        mCartBadge.setGravityOffset(22f, -2f, true)
        mCartBadge.setBadgeTextSize(6f, true)
        mCartBadge.bindTarget(mEnterCartTv).badgeNumber = AppPreferences.instance.get(GoodsConstants.SP_CART_SIZE, 0)
    }

    /*
        Bus取消监听
     */
    override fun onDestroy() {
        Bus.unregister(this)
        super.onDestroy()
    }
}
