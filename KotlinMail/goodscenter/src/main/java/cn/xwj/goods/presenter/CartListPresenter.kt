package cn.xwj.goods.presenter

import cn.xwj.baselibrary.presenter.BasePresenter
import cn.xwj.goods.data.repository.CartDataSource
import cn.xwj.goods.presenter.view.CartListView
import javax.inject.Inject

/**
 * Author: xw
 * Email:i.xiaowujiang@gmail.com
 * Date: 2018-06-03 2018/6/3
 * Description: CategoryPresenter
 */
class CartListPresenter @Inject constructor() : BasePresenter<CartListView>() {


    @Inject
    lateinit var repository: CartDataSource

    fun getGoodsList(categoryId: Int, pageNo: Int) {

    }
}