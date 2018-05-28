package cn.xwj.baselibrary.data.protocol

/**
 * Author: xw
 * Email:i.xiaowujiang@gmail.com
 * Date: 2018-05-28 2018/5/28
 * Description: BaseResp
 */
data class BaseResp<out T>(val status: Int, val message: String, val data: T)