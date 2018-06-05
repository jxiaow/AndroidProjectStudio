package cn.xwj.goods.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.xwj.baselibrary.ui.fragment.BaseMvpFragment
import cn.xwj.goods.R
import cn.xwj.goods.presenter.CartListPresenter
import cn.xwj.goods.presenter.view.CartListView

/**
 * Author: xw
 * Email:i.xiaowujiang@gmail.com
 * Date: 2018-06-03 2018/6/3
 * Description: CategoryFragment
 */
class CartFragment : BaseMvpFragment<CartListPresenter>(), CartListView {



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
    }

}