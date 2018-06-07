package cn.xwj.order.presenter.view

import cn.xwj.baselibrary.presenter.view.BaseView

/**
 * Author: xw
 * Date: 2018-06-07 12:51:31
 * Description: EditShipAddressView: .
 */
/*
    编辑收货人信息 视图回调
 */
interface EditShipAddressView : BaseView {
    //添加收货人回调
    fun onAddShipAddressResult(result: Boolean)
    //修改收货人回调
    fun onEditShipAddressResult(result: Boolean)
}
