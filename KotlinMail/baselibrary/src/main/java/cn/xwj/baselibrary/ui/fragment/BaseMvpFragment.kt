package cn.xwj.baselibrary.ui.fragment

import cn.xwj.baselibrary.presenter.BasePresenter
import cn.xwj.baselibrary.presenter.view.BaseView

/**
 * Author: xw
 * Date: 2018-05-25 15:16:54
 * Description: BaseMvpFragment: .
 */
open class BaseMvpFragment<T : BasePresenter<*>> : BaseFragment(), BaseView {

    lateinit var mPresenter: T

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showError(text: String) {
    }

}