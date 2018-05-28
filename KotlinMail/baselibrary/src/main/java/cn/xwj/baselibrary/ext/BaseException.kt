package cn.xwj.baselibrary.ext

/**
 * Author: xw
 * Email:i.xiaowujiang@gmail.com
 * Date: 2018-05-28 2018/5/28
 * Description: BaseException
 */
class BaseException(val status: Int, val text: String) : Throwable()