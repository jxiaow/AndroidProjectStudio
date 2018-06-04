package cn.xwj.goods.data.protocol

/**
 * Author: xw
 * Date: 2018-06-04 14:47:20
 * Description: GoodsSku: .
 */
data class GoodsSku(
        val id: Int,
        val skuTitle: String,//SKU标题
        val skuContent: List<String>//SKU内容
)
