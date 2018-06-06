package cn.xwj.kotlinmail.ui.activity

import android.os.Bundle
import cn.xwj.baselibrary.ui.activity.BaseActivity
import cn.xwj.kotlinmail.R
import cn.xwj.usercenter.utils.UserPrefsUtils
import kotlinx.android.synthetic.main.activity_setting.*
import org.jetbrains.anko.toast

class SettingActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        mUserProtocolTv.setOnClickListener {
            toast("用户协议")
        }
        mFeedBackTv.setOnClickListener {
            toast("反馈意见")
        }
        mAboutTv.setOnClickListener {
            toast("关于")
        }

        //退出登录，清空本地用户数据
        mLogoutBtn.setOnClickListener {
            UserPrefsUtils.putUserInfo(null)
            finish()
        }
    }
}
