package cn.xwj.usercenter.data.protocol

/**
 * Author: xw
 * Email:i.xiaowujiang@gmail.com
 * Date: 2018-05-28 2018/5/28
 * Description: RegisterReq
 */

data class RegisterReq(val mobile: String, val pwd: String, val verifyCode: String)