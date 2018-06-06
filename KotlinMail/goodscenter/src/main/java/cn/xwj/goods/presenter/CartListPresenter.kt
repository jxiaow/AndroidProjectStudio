package cn.xwj.goods.presenter

import cn.xwj.baselibrary.ext.execute
import cn.xwj.baselibrary.presenter.BasePresenter
import cn.xwj.baselibrary.rx.BaseSubscriber
import cn.xwj.goods.data.protocol.CartGoods
import cn.xwj.goods.data.repository.CartDataSource
import cn.xwj.goods.presenter.view.CartListView
import javax.inject.Inject

/**
 * Author: xw
 * Email:i.xiaowujiang@gmail.com
 * Date: 2018-06-03 2018/6/3
 * Description: CartListPresenter
 */
class CartListPresenter @Inject constructor() : BasePresenter<CartListView>() {


    @Inject
    lateinit var repository: CartDataSource


    /*
        获取购物车列表
     */
    fun getCartList() {
        mView.showLoading()
        repository.getCartList().execute(object : BaseSubscriber<MutableList<CartGoods>?>(mView) {

            override fun onComplete() {
                super.onComplete()
                mView.onGetCartListResult(mData)
            }
        }, lifecycleOwner)

    }

    /*
        删除购物车商品
     */
    fun deleteCartList(list: List<Int>) {
        mView.showLoading()
        repository.deleteCartList(list).execute(object : BaseSubscriber<Boolean>(mView) {
            override fun onComplete() {
                super.onComplete()
                mView.onDeleteCartListResult(mData!!)
            }
        }, lifecycleOwner)

    }

    /*
        提交购物车商品
     */
    fun submitCart(list: MutableList<CartGoods>, totalPrice: Long) {
        mView.showLoading()
        repository.submitCart(list, totalPrice).execute(object : BaseSubscriber<Int>(mView) {
            override fun onComplete() {
                super.onComplete()
                mView.onSubmitCartListResult(mData!!)
            }
        }, lifecycleOwner)

    }

}