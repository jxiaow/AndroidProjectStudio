package cn.xwj.baselibrary.presenter

import cn.xwj.baselibrary.presenter.view.BaseView

/**
 * Author: xw
 * Date: 2018-05-25 15:01:08
 * Description: BasePresenter: Mvp 中的 P.
 */
open class BasePresenter<T : BaseView> {


    lateinit var mView: T

}