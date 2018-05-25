package cn.xwj.baselibrary.presenter.view

/**
 * Author: xw
 * Date: 2018-05-25 14:59:48
 * Description: BaseView: MVP 中的 V.
 */
interface BaseView {
    fun showLoading()
    fun hideLoading()
    fun showError(text: String)
}