package cn.xwj.baselibrary.presenter

import cn.xwj.baselibrary.presenter.view.BaseView
import javax.inject.Inject

/**
 * Author: xw
 * Date: 2018-05-25 15:01:08
 * Description: BasePresenter: Mvp 中的 P.
 */
open class BasePresenter<T : BaseView> {

    @Inject
    lateinit var mView: T

}