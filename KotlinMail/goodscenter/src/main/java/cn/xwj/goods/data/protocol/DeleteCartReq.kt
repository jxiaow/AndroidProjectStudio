package cn.xwj.goods.data.protocol

/**
 * Author: xw
 * Date: 2018-06-06 11:24:33
 * Description: DeleteCartReq: .
 */
/*
    删除购物车商品请求
 */
data class DeleteCartReq(val cartIdList: List<Int> = arrayListOf())
