package cn.xwj.goods.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.xwj.baselibrary.ui.fragment.BaseMvpFragment
import cn.xwj.goods.R
import cn.xwj.goods.data.protocol.CartGoods
import cn.xwj.goods.di.component.DaggerCartComponent
import cn.xwj.goods.di.component.DaggerCategoryComponent
import cn.xwj.goods.di.module.CartModule
import cn.xwj.goods.di.module.CategoryModule
import cn.xwj.goods.presenter.CartListPresenter
import cn.xwj.goods.presenter.view.CartListView

/**
 * Author: xw
 * Email:i.xiaowujiang@gmail.com
 * Date: 2018-06-03 2018/6/3
 * Description: CategoryFragment
 */
class CartFragment : BaseMvpFragment<CartListPresenter>(), CartListView {
    override fun onGetCartListResult(result: MutableList<CartGoods>?) {

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }


    private fun initView() {
    }

    override fun injectComponent() {
        DaggerCartComponent.builder().activityComponent(mActivityComponent)
                .cartModule(CartModule(this))
                .build().inject(this)

    }

}