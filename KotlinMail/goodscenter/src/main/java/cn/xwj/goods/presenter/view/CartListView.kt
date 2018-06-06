package cn.xwj.goods.presenter.view

import cn.xwj.baselibrary.presenter.view.BaseView
import cn.xwj.goods.data.protocol.CartGoods

/**
 * Author: xw
 * Email:i.xiaowujiang@gmail.com
 * Date: 2018-06-03 2018/6/3
 * Description: CategoryView
 */

interface CartListView : BaseView {

    //获取购物车列表
    fun onGetCartListResult(result: MutableList<CartGoods>?)

    //删除购物车
    fun onDeleteCartListResult(result: Boolean)

    //提交购物车
    fun onSubmitCartListResult(result: Int)
}
