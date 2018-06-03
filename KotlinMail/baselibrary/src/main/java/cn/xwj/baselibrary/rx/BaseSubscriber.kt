package cn.xwj.baselibrary.rx

import android.util.Log
import cn.xwj.baselibrary.presenter.view.BaseView
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * Author: xw
 * Date: 2018-05-29 09:58:49
 * Description: BaseSubscriber: .
 */
open class BaseSubscriber<T>(val view: BaseView) : Observer<T> {
    private var disposable: Disposable? = null

    override fun onComplete() {
        view.hideLoading()
        Log.d("TAG", "disposed: ${disposable?.isDisposed}")
        disposable?.dispose()
    }

    override fun onSubscribe(d: Disposable) {
        this.disposable = d
    }

    override fun onNext(t: T) {
    }

    override fun onError(e: Throwable) {
        if (e is BaseException) {
            view.showError(e.text)
        }
        view.hideLoading()
        disposable?.dispose()
        Log.d("TAG", "disposed: ${disposable?.isDisposed}")
    }

}