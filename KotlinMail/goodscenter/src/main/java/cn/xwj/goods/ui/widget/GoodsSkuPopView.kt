package cn.xwj.goods.ui.widget

import android.content.Context
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import cn.xwj.baselibrary.ext.loadUrl
import cn.xwj.baselibrary.widget.DefaultTextWatcher
import cn.xwj.goods.R
import cn.xwj.goods.common.GoodsConstants
import cn.xwj.goods.data.protocol.GoodsSku
import cn.xwj.goods.utils.YuanFenConverter
import kotlinx.android.synthetic.main.layout_sku_pop.view.*
import org.jetbrains.anko.editText

/**
 * Author: xw
 * Date: 2018-06-05 14:15:00
 * Description: GoodsSkuPop: .
 */
class GoodsSkuPopView(context: Context) : PopupWindow(context), View.OnClickListener {

    private val mRootView: View = LayoutInflater.from(context)
            .inflate(R.layout.layout_sku_pop, null)
    private val mContext: Context = context
    private val mSkuViewList = arrayListOf<SkuView>()

    init {
        initView()
        this.contentView = mRootView
        this.width = ViewGroup.LayoutParams.MATCH_PARENT
        this.height = ViewGroup.LayoutParams.MATCH_PARENT
        this.isFocusable = true
        this.animationStyle = R.style.AnimBottom
        this.background.alpha = 150

        mRootView.setOnTouchListener { _, event ->
            val height = mRootView.mPopView.top
            val y = event.y.toInt()
            if (event.action == MotionEvent.ACTION_UP) {
                if (y < height) {
                    dismiss()
                }
            }
            true
        }

    }

    private fun initView() {
        with(mRootView) {
            mCloseIv.setOnClickListener(this@GoodsSkuPopView)
            mAddCartBtn.setOnClickListener(this@GoodsSkuPopView)
            mSkuCountBtn.setCurrentNumber(1)
            mSkuCountBtn.editText().addTextChangedListener(object : DefaultTextWatcher() {
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    super.onTextChanged(s, start, before, count)
                    //发送信息
                }
            })
        }
    }


    /*
        设置商品图标
     */
    fun setGoodsIcon(text: String) {
        mRootView.mGoodsIconIv.loadUrl(text)
    }

    /*
        设置商品价格
     */
    fun setGoodsPrice(text: Long) {
        mRootView.mGoodsPriceTv.text = YuanFenConverter.changeF2YWithUnit(text)
    }

    /*
        设置商品编号
     */
    fun setGoodsCode(text: String) {
        mRootView.mGoodsCodeTv.text = "商品编号:" + text
    }

    /*
        设置商品SKU
     */
    fun setSkuData(list: List<GoodsSku>) {
        for (goodSku in list) {
            val skuView = SkuView(mContext)
            skuView.setSkuData(goodSku)

            mSkuViewList.add(skuView)
            mRootView.mSkuView.addView(skuView)
        }
    }

    /*
        获取选中的SKU
     */
    fun getSelectSku(): String {
        var skuInfo = ""
        for (skuView in mSkuViewList) {
            skuInfo += skuView.getSkuInfo().split(GoodsConstants.SKU_SEPARATOR)[1] + GoodsConstants.SKU_SEPARATOR
        }
        return skuInfo.take(skuInfo.length - 1)//刪除最后一个分隔
    }

    /*
        获取商品数量
     */
    fun getSelectCount() = mRootView.mSkuCountBtn.number

    override fun onClick(v: View) {
        when (v.id) {
            R.id.mCloseIv -> dismiss()
            R.id.mAddCartBtn -> {
                dismiss()
            }
        }
    }

}


}