package cn.xwj.baselibrary.ui.activity

import android.os.Bundle
import cn.xwj.baselibrary.di.component.DaggerActivityComponent
import cn.xwj.baselibrary.di.module.ActivityModule
import cn.xwj.baselibrary.di.scope.ActivityScope
import cn.xwj.baselibrary.presenter.BasePresenter
import cn.xwj.baselibrary.presenter.view.BaseView
import javax.inject.Inject

/**
 * Author: xw
 * Date: 2018-05-25 15:03:33
 * Description: BaseMvpActivity: .
 */
@ActivityScope
open class BaseMvpActivity<T : BasePresenter<*>> : BaseActivity(), BaseView {


    @Inject
    lateinit var mPresenter: T


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivityComponent()
    }

    private fun initActivityComponent() {
        DaggerActivityComponent.builder().activityModule(ActivityModule(this)).build()
    }


    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showError(text: String) {
    }
}