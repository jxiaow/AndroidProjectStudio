package cn.xwj.baselibrary.ext

import android.text.Editable
import android.widget.EditText
import cn.xwj.baselibrary.widget.DefaultTextWatcher

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