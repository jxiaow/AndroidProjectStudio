package cn.xwj.goods.ui.activity

import android.os.Bundle
import cn.xwj.baselibrary.ui.activity.BaseActivity
import cn.xwj.goods.R
import cn.xwj.goods.ui.fragment.CartFragment

/**
 * Author: xw
 * Date: 2018-06-04 12:50:10
 * Description: GoodsActivity: .
 */
class CartActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        val fragment = supportFragmentManager.findFragmentById(R.id.fragment_cart)
        (fragment as CartFragment).setBackVisible(true)

    }
}