package cn.xwj.baselibrary.ui.activity

import android.os.Bundle
import cn.xwj.baselibrary.common.BaseApplication
import cn.xwj.baselibrary.di.component.ActivityComponent
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
abstract class BaseMvpActivity<T : BasePresenter<*>> : BaseActivity(), BaseView {


    @Inject
    lateinit var mPresenter: T

    lateinit var activityComponent: ActivityComponent


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivityComponent()
        initPerComponent()
    }


    private fun initActivityComponent() {
        activityComponent = DaggerActivityComponent.builder()
                .appComponent((application as BaseApplication).appComponent)
                .activityModule(ActivityModule(this))
                .build()
    }


    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showError(text: String) {
    }


    /**
     * 初始化每个dagger2
     */
    abstract fun initPerComponent()
}