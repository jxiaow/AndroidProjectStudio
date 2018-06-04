package cn.xwj.goods.ui.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import cn.xwj.baselibrary.ext.content
import cn.xwj.baselibrary.ext.setVisiable
import cn.xwj.baselibrary.ui.activity.BaseActivity
import cn.xwj.baselibrary.utils.AppPreferences
import cn.xwj.goods.R
import cn.xwj.goods.common.GoodsConstants
import cn.xwj.goods.ui.adapter.SearchHistoryAdapter
import cn.xwj.provider.common.RoutePath
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import kotlinx.android.synthetic.main.activity_search_goods.*
import org.jetbrains.anko.toast

/**
 * Author: xw
 * Email:i.xiaowujiang@gmail.com
 * Date: 2018-06-04 2018/6/4
 * Description: SearchGoodsActivity
 */
@Route(path = RoutePath.GoodsCenter.SEARCH_GOODS_BY_KEYWORD)
class SearchGoodsActivity : BaseActivity(), View.OnClickListener {

    private lateinit var mAdapter: SearchHistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_goods)
        initView()
        loadData()
    }

    private fun loadData() {
        val mutableSet = AppPreferences.instance
                .getStringSet(GoodsConstants.SP_SEARCH_HISTORY, mutableSetOf())
        mDataView.setVisiable(mutableSet.isNotEmpty())
        mNoDataTv.setVisiable(mutableSet.isEmpty())
        if (mutableSet.isNotEmpty()) {
            mAdapter.setData(mutableSet.toMutableList())
        }
    }

    private fun initView() {
        mLeftIv.setOnClickListener(this)
        mSearchTv.setOnClickListener(this)

        val manager = LinearLayoutManager(this)
        manager.orientation = LinearLayoutManager.VERTICAL
        mSearchHistoryRv.layoutManager = manager
        mAdapter = SearchHistoryAdapter()
        mSearchHistoryRv.adapter = mAdapter
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.mLeftIv -> finish()
            R.id.mClearBtn -> AppPreferences.instance.remove(GoodsConstants.SP_SEARCH_HISTORY)
            R.id.mSearchTv -> {
                if (mKeywordEt.content.isEmpty()) {
                    toast("请输入需要搜索的关键字")
                } else {
                    val inputValue = mKeywordEt.content
                    AppPreferences.instance.putStringSet(GoodsConstants.SP_SEARCH_HISTORY,
                            mutableSetOf(inputValue))
                    ARouter.getInstance()
                            .build(RoutePath.GoodsCenter.SEARCH_GOODS_BY_KEYWORD)
                            .withString(GoodsConstants.EXTRA_KEY_WORDS, inputValue)
                            .navigation()

                }
            }

        }
    }
}