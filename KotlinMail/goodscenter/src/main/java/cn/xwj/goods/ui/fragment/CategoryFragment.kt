package cn.xwj.goods.ui.fragment

import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.xwj.baselibrary.ext.startLoading
import cn.xwj.baselibrary.ui.adapter.BaseRecyclerViewAdapter
import cn.xwj.baselibrary.ui.fragment.BaseMvpFragment
import cn.xwj.goods.R
import cn.xwj.goods.R.id.*
import cn.xwj.goods.common.GoodsConstants
import cn.xwj.goods.data.protocol.Category
import cn.xwj.goods.di.component.DaggerCategoryComponent
import cn.xwj.goods.di.module.CategoryModule
import cn.xwj.goods.presenter.CategoryPresenter
import cn.xwj.goods.presenter.view.CategoryView
import cn.xwj.goods.ui.adapter.SecondCategoryAdapter
import cn.xwj.goods.ui.adapter.TopCategoryAdapter
import cn.xwj.provider.common.RoutePath
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.kennyc.view.MultiStateView
import kotlinx.android.synthetic.main.fragment_category.*

/**
 * Author: xw
 * Email:i.xiaowujiang@gmail.com
 * Date: 2018-06-03 2018/6/3
 * Description: CategoryFragment
 */
@Route(path = RoutePath.GoodsCenter.GET_CATEGORY_FRAGMENT)
class CategoryFragment : BaseMvpFragment<CategoryPresenter>(), CategoryView {


    private lateinit var topAdapter: TopCategoryAdapter
    private lateinit var secondCategoryAdapter: SecondCategoryAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        loadData(0)
    }

    private fun loadData(parentId: Int) {
        if (parentId != 0) {
            mMultiStateView.startLoading()
        }

        mPresenter.getCategory(parentId)
    }

    private fun initView() {
        val manager = LinearLayoutManager(activity)
        manager.orientation = LinearLayoutManager.VERTICAL
        mTopCategoryRv.layoutManager = manager
        mTopCategoryRv.itemAnimator = DefaultItemAnimator()
        topAdapter = TopCategoryAdapter()
        mTopCategoryRv.adapter = topAdapter

        mSecondCategoryRv.layoutManager = GridLayoutManager(activity, 3)
        mSecondCategoryRv.itemAnimator = DefaultItemAnimator()
        secondCategoryAdapter = SecondCategoryAdapter()
        mSecondCategoryRv.adapter = secondCategoryAdapter


        topAdapter.setOnItemOnClickListener(object : BaseRecyclerViewAdapter.OnItemClickListener<Category> {
            override fun onItemClick(item: Category, position: Int) {
                for (category in topAdapter.dataList) {
                    category.isSelected = item.id == category.id
                }
                topAdapter.notifyDataSetChanged()
                loadData(item.id)
            }

        })

        secondCategoryAdapter.setOnItemOnClickListener(object : BaseRecyclerViewAdapter.OnItemClickListener<Category> {
            override fun onItemClick(item: Category, position: Int) {
                ARouter.getInstance()
                        .build(RoutePath.GoodsCenter.GET_GOODS_LIST)
                        .withInt(GoodsConstants.EXTRA_CATEGORY_ID, item.id)
                        .navigation()
            }
        })
    }

    override fun injectComponent() {
        DaggerCategoryComponent.builder()
                .categoryModule(CategoryModule(this))
                .activityComponent(mActivityComponent)
                .build()
                .inject(this)
    }

    override fun onGetCategoryResult(dataList: MutableList<Category>?) {

        if (dataList == null || dataList.isEmpty()) {
            //没有数据
            mTopCategoryIv.visibility = View.GONE
            mCategoryTitleTv.visibility = View.GONE
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
            return
        }

        with(dataList[0]) {
            when (parentId) {
                0 -> {
                    isSelected = true
                    topAdapter.setData(dataList)
                    mPresenter.getCategory(id)
                }
                else -> {
                    secondCategoryAdapter.setData(dataList)
                    mTopCategoryIv.visibility = View.VISIBLE
                    mCategoryTitleTv.visibility = View.VISIBLE
                    mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
                }
            }
        }

    }
}