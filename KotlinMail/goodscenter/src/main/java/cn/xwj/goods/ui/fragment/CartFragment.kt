package cn.xwj.goods.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.xwj.baselibrary.ext.setVisible
import cn.xwj.baselibrary.ext.startLoading
import cn.xwj.baselibrary.ui.fragment.BaseMvpFragment
import cn.xwj.baselibrary.utils.AppPreferences
import cn.xwj.goods.R
import cn.xwj.goods.common.GoodsConstants
import cn.xwj.goods.data.protocol.CartGoods
import cn.xwj.goods.di.component.DaggerCartComponent
import cn.xwj.goods.di.module.CartModule
import cn.xwj.goods.event.CartAllCheckedEvent
import cn.xwj.goods.event.UpdateCartSizeEvent
import cn.xwj.goods.event.UpdateTotalPriceEvent
import cn.xwj.goods.presenter.CartListPresenter
import cn.xwj.goods.presenter.view.CartListView
import cn.xwj.goods.ui.adapter.CartGoodsAdapter
import cn.xwj.baselibrary.utils.YuanFenConverter
import cn.xwj.provider.common.RoutePath
import com.alibaba.android.arouter.launcher.ARouter
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.kennyc.view.MultiStateView
import com.kotlin.provider.common.ProviderConstant
import kotlinx.android.synthetic.main.fragment_cart.*
import org.jetbrains.anko.support.v4.toast

/**
 * Author: xw
 * Email:i.xiaowujiang@gmail.com
 * Date: 2018-06-03 2018/6/3
 * Description: CategoryFragment
 */
class CartFragment : BaseMvpFragment<CartListPresenter>(), CartListView {


    private lateinit var cartGoodsAdapter: CartGoodsAdapter
    private var mTotalPrice: Long = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObserve()
    }

    /*
            加载数据
         */
    override fun onStart() {
        super.onStart()
        loadData()
    }

    private fun initView() {
        mCartGoodsRv.layoutManager = LinearLayoutManager(context)
        cartGoodsAdapter = CartGoodsAdapter(context!!)
        mCartGoodsRv.adapter = cartGoodsAdapter

        mHeaderBar.getRightView().setOnClickListener {
            refreshEditStatus()
        }

        //全选按钮事件
        mAllCheckedCb.setOnClickListener {
            cartGoodsAdapter.dataList.forEach {
                it.isSelected = mAllCheckedCb.isChecked
            }
            cartGoodsAdapter.notifyDataSetChanged()
            updateTotalPrice()
        }

        //删除按钮事件
        mDeleteBtn.setOnClickListener {
            val cartIdList: MutableList<Int> = arrayListOf()
            cartGoodsAdapter.dataList.filter { it.isSelected }
                    .mapTo(cartIdList) { it.id }
            if (cartIdList.size == 0) {
                toast("请选择需要删除的数据")
            } else {
                mPresenter.deleteCartList(cartIdList)
            }
        }

        //结算按钮事件
        mSettleAccountsBtn.setOnClickListener {
            val cartGoodsList: MutableList<CartGoods> = arrayListOf()
            cartGoodsAdapter.dataList.filter { it.isSelected }
                    .mapTo(cartGoodsList) { it }
            if (cartGoodsList.size == 0) {
                toast("请选择需要提交的数据")
            } else {
                mPresenter.submitCart(cartGoodsList, mTotalPrice)
            }
        }
    }

    override fun injectComponent() {
        DaggerCartComponent.builder().cartModule(CartModule(this))
                .activityComponent(mActivityComponent)
                .build().inject(this)
    }

    /*
           刷新是否为编辑状态
        */
    private fun refreshEditStatus() {
        val isEditStatus = getString(R.string.common_edit) == mHeaderBar.getRightText()
        mTotalPriceTv.setVisible(isEditStatus.not())
        mSettleAccountsBtn.setVisible(isEditStatus.not())
        mDeleteBtn.setVisible(isEditStatus)

        mHeaderBar.getRightView().text = if (isEditStatus) getString(R.string.common_complete) else getString(R.string.common_edit)


    }

    /*
           注册监听
        */
    private fun initObserve() {
        Bus.observe<CartAllCheckedEvent>().subscribe { t: CartAllCheckedEvent ->
            run {
                mAllCheckedCb.isChecked = t.isAllChecked
                updateTotalPrice()
            }
        }
                .registerInBus(this)

        Bus.observe<UpdateTotalPriceEvent>().subscribe {
            updateTotalPrice()
        }
                .registerInBus(this)

    }

    /*
        取消监听
     */
    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }

    /*
        更新总价
     */
    private fun updateTotalPrice() {
        mTotalPrice = cartGoodsAdapter.dataList
                .filter { it.isSelected }
                .map { it.goodsCount * it.goodsPrice }
                .sum()

        mTotalPriceTv.text = "合计:${YuanFenConverter.changeF2YWithUnit(mTotalPrice)}"
    }

    /*
        删除购物车回调
     */
    override fun onDeleteCartListResult(result: Boolean) {
        toast("删除成功")
        refreshEditStatus()
        loadData()
    }

    /*
        提交购物车回调
     */
    override fun onSubmitCartListResult(result: Int) {
        ARouter.getInstance().build(RoutePath.OrderCenter.PATH_ORDER_CONFIRM)
                .withInt(ProviderConstant.KEY_ORDER_ID, result)
                .navigation()
    }

    override fun onGetCartListResult(result: MutableList<CartGoods>?) {
        if (result != null && result.size > 0) {
            cartGoodsAdapter.setData(result)
            mHeaderBar.getRightView().setVisible(true)
            mAllCheckedCb.isChecked = false
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        } else {
            mHeaderBar.getRightView().setVisible(false)
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }

        //本地存储并发送事件刷新UI
        AppPreferences.instance.put(GoodsConstants.SP_CART_SIZE, result?.size ?: 0)
        Bus.send(UpdateCartSizeEvent())
        //更新总价
        updateTotalPrice()
    }

    /*
        设置Back是否可见
     */
    fun setBackVisible(isVisible: Boolean) {
        mHeaderBar.getLeftView().setVisible(isVisible)
    }

    /*
           加载数据
        */
    private fun loadData() {
        mMultiStateView.startLoading()
        mPresenter.getCartList()
    }
}