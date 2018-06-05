package cn.xwj.goods.presenter.view

import cn.xwj.baselibrary.presenter.view.BaseView
import cn.xwj.goods.data.protocol.Goods

/**
 * Author: xw
 * Email:i.xiaowujiang@gmail.com
 * Date: 2018-06-03 2018/6/3
 * Description: CategoryView
 */

interface GoodsDetailView : BaseView {
    //获取商品详情
    fun onGetGoodsDetailResult(result: Goods)

    fun onAddCartResult(result: Int)
}