package cn.xwj.baselibrary.ext

import android.text.Editable
import android.widget.EditText
import cn.xwj.baselibrary.common.BaseConstants.Companion.REQUEST_SUCCESS
import cn.xwj.baselibrary.data.protocol.BaseResp
import cn.xwj.baselibrary.widget.DefaultTextWatcher
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.Android

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

fun EditText.content(): String {
    return this.text.toString()
}

fun <T> Observable<BaseResp<T>>.convertBoolean(): Observable<Boolean> {
    return this.flatMap { it ->
        when (it.status) {
            REQUEST_SUCCESS -> Observable.just(true)
            else -> Observable.error(BaseException(it.status, it.message))
        }
    }
}

fun <T> Observable<T>.execute() {
    this.observeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe()
}