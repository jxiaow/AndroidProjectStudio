package cn.xwj.pay.presenter.view

import cn.xwj.baselibrary.presenter.view.BaseView

/**
 * Author: xw
 * Date: 2018-06-07 15:39:07
 * Description: PayView: .
 */
/*
    支付 视图回调
 */
interface PayView : BaseView {

    //获取支付签名
    fun onGetSignResult(result: String)
    //同步支付成功状态
    fun onPayOrderResult(result: Boolean)

}
