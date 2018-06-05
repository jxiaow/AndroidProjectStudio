package cn.xwj.goods.data.protocol

/**
 * Author: xw
 * Email:i.xiaowujiang@gmail.com
 * Date: 2018-06-03 2018/6/3
 * Description: Category
 */

/*
    添加商品到购物车请求
 */
data class AddCartReq(
        val goodsId: Int, //商品ID
        val goodsDesc: String, //商品描述
        val goodsIcon: String, //商品图标
        val goodsPrice: Long, //商品价格
        val goodsCount: Int, //商品数量
        val goodsSku: String //商品SKU
)
