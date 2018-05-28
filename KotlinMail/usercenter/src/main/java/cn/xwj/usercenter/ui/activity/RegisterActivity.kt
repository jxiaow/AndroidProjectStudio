package cn.xwj.usercenter.ui.activity

import android.os.Bundle
import cn.xwj.baselibrary.ext.afterTextChanged
import cn.xwj.baselibrary.ext.content
import cn.xwj.baselibrary.ui.activity.BaseMvpActivity
import cn.xwj.usercenter.R
import cn.xwj.usercenter.di.component.DaggerUserComponent
import cn.xwj.usercenter.di.module.UserModule
import cn.xwj.usercenter.presenter.RegisterPresenter
import cn.xwj.usercenter.presenter.view.RegisterView
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast

/**
 * 注册
 */
class RegisterActivity : BaseMvpActivity<RegisterPresenter>(), RegisterView {
    override fun onRegisterResult(result: String) {
        toast("注册成功")
    }

    override fun onSendVerifyCode(result: Boolean) {
        if (result) {
            toast("请求成功")
        }
    }

    override fun initPerComponent() {
        DaggerUserComponent.builder()
                .userModule(UserModule(this))
                .activityComponent(activityComponent)
                .build()
                .inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        mConfirmPwdET.afterTextChanged {
            if (it.equals(mPwdET.content()).not()) toast("两次密码不一致")
        }

        mSendVerifyCodeBtn.setOnClickListener {
            mPresenter.sendVerifyCode(mMobileNumberET.content())
        }

        mRegisterBtn.setOnClickListener {
            mPresenter.register(mMobileNumberET.content(), mPwdET.content(), mVerifyCodeET.content())
        }
    }
}
