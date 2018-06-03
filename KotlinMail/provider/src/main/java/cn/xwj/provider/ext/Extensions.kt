package cn.xwj.provider.ext

import cn.xwj.baselibrary.common.BaseConstants
import cn.xwj.baselibrary.utils.AppPreferences
import cn.xwj.provider.common.RoutePath
import com.alibaba.android.arouter.launcher.ARouter

/**
 * Author: xw
 * Email:i.xiaowujiang@gmail.com
 * Date: 2018-06-03 2018/6/3
 * Description: Extensions
 */

fun isLogin(): Boolean = AppPreferences.instance
        .get(BaseConstants.KEY_SP_TOKEN, "").isNotEmpty()

fun afterLogin(code: () -> Unit) {

    if (isLogin()) {
        code()
    } else {
        ARouter.getInstance().build(RoutePath.UserCenter.LOGIN_PATH).navigation()
    }
}