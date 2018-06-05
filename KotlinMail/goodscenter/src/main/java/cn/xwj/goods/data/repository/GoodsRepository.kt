package cn.xwj.goods.data.repository

import cn.xwj.baselibrary.data.net.RetrofitFactory
import cn.xwj.baselibrary.ext.convert
import cn.xwj.goods.data.api.GoodsApi
import cn.xwj.goods.data.protocol.GetGoodsDetailReq
import cn.xwj.goods.data.protocol.GetGoodsListByKeywordReq
import cn.xwj.goods.data.protocol.GetGoodsListReq
import cn.xwj.goods.data.protocol.Goods
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Author: xw
 * Email:i.xiaowujiang@gmail.com
 * Date: 2018-06-03 2018/6/3
 * Description: GoodsRepository
 */
class GoodsRepository @Inject constructor() : GoodsDataSource {
    override fun getGoodsDetail(goodsId: Int): Observable<Goods> {
        return RetrofitFactory.instance.create(GoodsApi::class.java)
                .getGoodsDetail(GetGoodsDetailReq(goodsId))
                .convert()
    }

    override fun getGoodsList(categoryId: Int, pageNo: Int): Observable<MutableList<Goods>?> {
        return RetrofitFactory.instance.create(GoodsApi::class.java)
                .getGoodsList(GetGoodsListReq(categoryId, pageNo))
                .convert()
    }

    override fun getGoodsListByKeyWords(keyWords: String, pageNo: Int): Observable<MutableList<Goods>?> {
        return RetrofitFactory.instance.create(GoodsApi::class.java)
                .getGoodsListByKeyword(GetGoodsListByKeywordReq(keyWords, pageNo))
                .convert()
    }
}