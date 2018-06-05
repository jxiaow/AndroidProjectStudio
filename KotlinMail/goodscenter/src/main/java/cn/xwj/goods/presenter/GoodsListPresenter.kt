package cn.xwj.goods.presenter

import cn.xwj.baselibrary.ext.execute
import cn.xwj.baselibrary.presenter.BasePresenter
import cn.xwj.baselibrary.rx.BaseSubscriber
import cn.xwj.goods.data.protocol.Goods
import cn.xwj.goods.data.repository.GoodsDataSource
import cn.xwj.goods.presenter.view.GoodsListView
import javax.inject.Inject

/**
 * Author: xw
 * Email:i.xiaowujiang@gmail.com
 * Date: 2018-06-03 2018/6/3
 * Description: CategoryPresenter
 */
class GoodsListPresenter @Inject constructor() : BasePresenter<GoodsListView>() {


    @Inject
    lateinit var repository: GoodsDataSource

    fun getGoodsList(categoryId: Int, pageNo: Int) {

        repository.getGoodsList(categoryId, pageNo)
                .execute(object : BaseSubscriber<MutableList<Goods>?>(mView) {
                    override fun onComplete() {
                        super.onComplete()
                        mView.onGoodsListResult(mData)
                    }
                }, lifecycleOwner)
    }

    fun getGoodsListByKeyWords(keyWords: String, pageNo: Int) {
        repository.getGoodsListByKeyWords(keyWords, pageNo)
                .execute(object : BaseSubscriber<MutableList<Goods>?>(mView) {
                    override fun onComplete() {
                        super.onComplete()
                        mView.onGoodsListResult(mData)
                    }
                }, lifecycleOwner)
    }

}