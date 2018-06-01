package cn.xwj.usercenter.utils

import cn.xwj.baselibrary.common.BaseConstants
import cn.xwj.baselibrary.utils.AppPreferences
import cn.xwj.usercenter.data.protocol.UserInfo
import com.kotlin.provider.common.ProviderConstant

/**
 * Author: xw
 * Date: 2018-05-31 14:24:25
 * Description: UserPreferences: .
 */
/*
    本地存储用户相关信息
 */
object UserPrefsUtils {

    /*
        退出登录时，传入null,清空存储
     */
    fun putUserInfo(userInfo: UserInfo?) {
        with(AppPreferences.instance) {
            put(BaseConstants.KEY_SP_TOKEN, userInfo?.id ?: "")
            put(ProviderConstant.KEY_SP_USER_ICON, userInfo?.userIcon ?: "")
            put(ProviderConstant.KEY_SP_USER_NAME, userInfo?.userName ?: "")
            put(ProviderConstant.KEY_SP_USER_MOBILE, userInfo?.userMobile ?: "")
            put(ProviderConstant.KEY_SP_USER_GENDER, userInfo?.userGender ?: "")
            put(ProviderConstant.KEY_SP_USER_SIGN, userInfo?.userSign ?: "")
        }
    }

    /*
        获取userInfo
     */
    fun getUserInfo(): UserInfo? {
        with(AppPreferences.instance) {
            val token = get(BaseConstants.KEY_SP_TOKEN, "")
            val userIcon = get(ProviderConstant.KEY_SP_USER_ICON, "")
            val userName = get(ProviderConstant.KEY_SP_USER_NAME, "")
            val userMobile = get(ProviderConstant.KEY_SP_USER_MOBILE, "")
            val userGender = get(ProviderConstant.KEY_SP_USER_GENDER, "")
            val userSign = get(ProviderConstant.KEY_SP_USER_SIGN, "")

            if (token.isEmpty()) {
                return null
            }
            return UserInfo(token, userName, userMobile, "", userIcon, userGender, userSign)
        }
    }
}
