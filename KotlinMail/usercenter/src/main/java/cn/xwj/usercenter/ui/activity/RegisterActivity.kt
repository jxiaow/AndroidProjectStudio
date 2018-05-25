package cn.xwj.usercenter.ui.activity

import android.os.Bundle
import cn.xwj.baselibrary.ui.activity.BaseMvpActivity
import cn.xwj.usercenter.R
import cn.xwj.usercenter.presenter.RegisterPresenter
import cn.xwj.usercenter.presenter.view.RegisterView

/**
 * 注册
 */
class RegisterActivity : BaseMvpActivity<RegisterPresenter>(), RegisterView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

    }
}
