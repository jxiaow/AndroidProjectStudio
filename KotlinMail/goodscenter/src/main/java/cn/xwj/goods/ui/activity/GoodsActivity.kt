package cn.xwj.goods.ui.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder
import cn.bingoogolapple.refreshlayout.BGARefreshLayout
import cn.xwj.baselibrary.ext.startLoading
import cn.xwj.baselibrary.ui.activity.BaseMvpActivity
import cn.xwj.goods.R
import cn.xwj.goods.common.GoodsConstants
import cn.xwj.goods.data.protocol.Goods
import cn.xwj.goods.di.component.DaggerGoodsComponent
import cn.xwj.goods.di.module.GoodsModule
import cn.xwj.goods.presenter.GoodsListPresenter
import cn.xwj.goods.presenter.view.GoodsListView
import cn.xwj.goods.ui.adapter.GoodsListAdapter
import cn.xwj.provider.common.RoutePath
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.kennyc.view.MultiStateView
import kotlinx.android.synthetic.main.activity_goods.*

/**
 * Author: xw
 * Date: 2018-06-04 12:50:10
 * Description: GoodsActivity: .
 */
@Route(path = RoutePath.GoodsCenter.GET_GOODS_LIST)
class GoodsActivity : BaseMvpActivity<GoodsListPresenter>(), GoodsListView,
        BGARefreshLayout.BGARefreshLayoutDelegate {

    @JvmField
    @Autowired(name = GoodsConstants.EXTRA_CATEGORY_ID)
    var mCategoryId: Int = -1

    @JvmField
    @Autowired(name = GoodsConstants.EXTRA_KEY_WORDS)
    var mKeyWords: String = ""


    private var mCurrentPage: Int = 1
    private var mMaxPage: Int = 1

    private lateinit var goodsListAdapter: GoodsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goods)

        initView()
        initRefresh()
        ARouter.getInstance().inject(this)
        loadData()

    }

    private fun loadData() {

        if(mKeyWords.isNotEmpty()){
            mMultiStateView.startLoading()
            mPresenter.getGoodsListByKeyWords(mKeyWords, mCurrentPage)
        }else{
            mMultiStateView.startLoading()
            mPresenter.getGoodsList(mCategoryId, mCurrentPage)
        }
    }

    private fun initRefresh() {
        mRefreshLayout.setDelegate(this)
        val viewHolder = BGANormalRefreshViewHolder(this, true)
        viewHolder.setLoadMoreBackgroundColorRes(R.color.common_bg)
        viewHolder.setRefreshViewBackgroundColorRes(R.color.common_bg)
        mRefreshLayout.setRefreshViewHolder(viewHolder)
    }

    private fun initView() {
        val manager = LinearLayoutManager(this)
        manager.orientation = LinearLayoutManager.VERTICAL
        goodsListAdapter = GoodsListAdapter()
        mGoodsRv.layoutManager = manager
        mGoodsRv.adapter = goodsListAdapter
    }

    override fun initPerComponent() {

        DaggerGoodsComponent.builder().activityComponent(activityComponent)
                .goodsModule(GoodsModule(this))
                .build().inject(this)
    }

    override fun onGoodsListResult(result: MutableList<Goods>?) {
        mRefreshLayout.endLoadingMore()
        mRefreshLayout.endRefreshing()
        if (result == null || result.isEmpty()) {
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
            return
        }

        mMaxPage = result[0].maxPage
        if (mCurrentPage == 1) {
            goodsListAdapter.setData(result)
        } else {
            goodsListAdapter.dataList.addAll(result)
            goodsListAdapter.notifyDataSetChanged()
        }
        mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
    }

    override fun onBGARefreshLayoutBeginLoadingMore(refreshLayout: BGARefreshLayout?): Boolean {
        return when {
            mCurrentPage < mMaxPage -> {
                mCurrentPage++
                loadData()
                true
            }
            else -> false
        }
    }

    override fun onBGARefreshLayoutBeginRefreshing(refreshLayout: BGARefreshLayout?) {
        mCurrentPage = 1
        loadData()
    }
}