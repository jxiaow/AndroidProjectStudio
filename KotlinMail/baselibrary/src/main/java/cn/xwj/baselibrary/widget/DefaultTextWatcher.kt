package cn.xwj.baselibrary.widget

import android.text.Editable
import android.text.TextWatcher

/**
 * Author: xw
 * Date: 2018-05-28 16:38:49
 * Description: DefaultTextWatcher: .
 */
open class DefaultTextWatcher : TextWatcher {
    override fun afterTextChanged(s: Editable?) {
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }
}