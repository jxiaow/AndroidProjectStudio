package cn.xwj.goods.presenter

import cn.xwj.baselibrary.ext.execute
import cn.xwj.baselibrary.presenter.BasePresenter
import cn.xwj.baselibrary.rx.BaseSubscriber
import cn.xwj.goods.data.protocol.Category
import cn.xwj.goods.data.repository.CategoryDataSource
import cn.xwj.goods.presenter.view.CategoryView
import javax.inject.Inject

/**
 * Author: xw
 * Email:i.xiaowujiang@gmail.com
 * Date: 2018-06-03 2018/6/3
 * Description: CategoryPresenter
 */
class CategoryPresenter @Inject constructor() : BasePresenter<CategoryView>() {


    @Inject
    lateinit var repository: CategoryDataSource

    fun getCategory(parentId: Int) {

        repository.getCategory(parentId)
                .execute(object : BaseSubscriber<MutableList<Category>?>(mView) {
                    override fun onComplete() {
                        super.onComplete()
                        mView.onGetCategoryResult(mData)
                    }
                }, lifecycleOwner)
    }

}