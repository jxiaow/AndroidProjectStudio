package cn.xwj.baselibrary.ext

import android.app.Activity
import android.arch.lifecycle.LifecycleOwner
import android.text.Editable
import android.widget.Button
import android.widget.EditText
import cn.xwj.baselibrary.common.BaseConstants.Companion.REQUEST_SUCCESS
import cn.xwj.baselibrary.data.protocol.BaseResp
import cn.xwj.baselibrary.rx.BaseException
import cn.xwj.baselibrary.rx.BaseSubscriber
import cn.xwj.baselibrary.widget.DefaultTextWatcher
import com.trello.rxlifecycle2.android.lifecycle.kotlin.bindToLifecycle
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Author: xw
 * Date: 2018-05-28 16:44:23
 * Description: Extensions: .
 */

fun EditText.afterTextChanged(f: (text: String?) -> Unit) {

    this.addTextChangedListener(object : DefaultTextWatcher() {
        override fun afterTextChanged(s: Editable?) {
            f(s!!.toString())
        }
    })
}

val EditText.content: String
    get() = this.text.toString()


fun Button.enable(editText: EditText, f: () -> Boolean) {
    editText.afterTextChanged { this.isEnabled = f() }
}


fun <T> Observable<BaseResp<T>>.convertBoolean(): Observable<Boolean> {
    return this.flatMap { it ->
        when (it.status) {
            REQUEST_SUCCESS -> Observable.just(true)
            else -> Observable.error(BaseException(it.status, it.message))
        }
    }
}

fun <T> Observable<T>.execute(subscriber: BaseSubscriber<T>, owner: LifecycleOwner) {
    this.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .bindToLifecycle(owner)
            .subscribe(subscriber)
}