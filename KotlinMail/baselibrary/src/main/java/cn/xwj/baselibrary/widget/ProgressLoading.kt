package com.kotlin.base.widgets

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.view.Gravity
import android.widget.ImageView
import cn.xwj.baselibrary.R
import org.jetbrains.anko.find

/*
    加载对话框封装
 */
class ProgressLoading private constructor(context: Context, theme: Int) : Dialog(context, theme) {

    companion object {
        private var mDialog: ProgressLoading? = null
        private var animDrawable: AnimationDrawable? = null

        /*
            创建加载对话框
         */
        fun create(context: Context): ProgressLoading {
            mDialog?.let {
                if (mDialog!!.isShowing) {
                    mDialog!!.dismiss()
                }
            }
            //样式引入
            mDialog = ProgressLoading(context, R.style.LightProgressDialog)
            with(mDialog!!) {
                //设置布局
                setContentView(R.layout.progress_dialog)
                setCancelable(true)
                setCanceledOnTouchOutside(false)
                window.attributes.gravity = Gravity.CENTER
                val lp = window.attributes
                lp.dimAmount = 0.2f
                //设置属性
                window.attributes = lp

                //获取动画视图
                val loadingView = find<ImageView>(R.id.iv_loading)
                animDrawable = loadingView.background as AnimationDrawable
                return this
            }
        }
    }

    /*
        显示加载对话框，动画开始
     */
    fun showLoading() {
        super.show()
        animDrawable?.start()
    }

    /*
        隐藏加载对话框，动画停止
     */
    fun hideLoading() {
        super.dismiss()
        animDrawable?.stop()
    }
}
