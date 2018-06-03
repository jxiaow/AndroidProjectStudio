package cn.xwj.baselibrary.ui.fragment

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.xwj.baselibrary.common.BaseApplication
import cn.xwj.baselibrary.di.component.ActivityComponent
import cn.xwj.baselibrary.di.component.DaggerActivityComponent
import cn.xwj.baselibrary.di.module.ActivityModule
import cn.xwj.baselibrary.di.module.LifecycleOwnerModule
import cn.xwj.baselibrary.presenter.BasePresenter
import cn.xwj.baselibrary.presenter.view.BaseView
import com.kotlin.base.widgets.ProgressLoading
import org.jetbrains.anko.support.v4.toast
import javax.inject.Inject

/**
 * Author: xw
 * Date: 2018-05-25 15:16:54
 * Description: BaseMvpFragment: .
 */
abstract class BaseMvpFragment<T : BasePresenter<*>> : BaseFragment(), BaseView {

    @Inject
    lateinit var mPresenter: T

    lateinit var mActivityComponent: ActivityComponent
    private lateinit var mProgressLoading: ProgressLoading

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        injectActivityComponent()
        injectComponent()
        mProgressLoading = ProgressLoading.create(context!!)

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun injectActivityComponent() {
        mActivityComponent = DaggerActivityComponent.builder().activityModule(ActivityModule(activity!!))
                .appComponent((activity!!.application as BaseApplication).appComponent)
                .lifecycleOwnerModule(LifecycleOwnerModule(this))
                .build()

    }

    override fun showLoading() {
        mProgressLoading.showLoading()
    }

    override fun hideLoading() {
        mProgressLoading.hideLoading()
    }

    override fun showError(text: String) {
        toast(text)
    }

    abstract fun injectComponent()

}