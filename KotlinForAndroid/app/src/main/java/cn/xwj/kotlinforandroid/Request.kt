package cn.xwj.kotlinforandroid

import android.util.Log
import java.net.URL

/**
 * Author: xw
 * Email:i.xiaowujiang@gmail.com
 * Date: 2018-05-15 2018/5/15
 * Description: Request
 */
class Request(val url: String) {
    public fun run() {
        val forecastJsonStr = URL(url).readText()
        Log.d(javaClass.simpleName, forecastJsonStr)
    }
}