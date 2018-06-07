package cn.xwj.pay.presenter

import cn.xwj.baselibrary.ext.execute
import cn.xwj.baselibrary.presenter.BasePresenter
import cn.xwj.pay.data.repository.PayDataSource
import cn.xwj.pay.presenter.view.PayView
import javax.inject.Inject

/**
 * Author: xw
 * Date: 2018-06-07 15:38:44
 * Description: PayPresenter: .
 */
/*
    支付Presenter
 */
class PayPresenter @Inject constructor() : BasePresenter<PayView>() {
    @Inject
    lateinit var repository: PayDataSource

    /*
        获取支付签名
     */
    fun getPaySign(orderId: Int, totalPrice: Long) {
        mView.showLoading()
        repository.getPaySign(orderId, totalPrice)
                .execute(mView, lifecycleOwner) {
                    mView.onGetSignResult(it!!)
                }

    }

    /*
        订单支付，同步订单状态
     */
    fun payOrder(orderId: Int) {
        mView.showLoading()
        repository.payOrder(orderId).execute(mView, lifecycleOwner) {
            mView.onPayOrderResult(it!!)
        }
    }


}
