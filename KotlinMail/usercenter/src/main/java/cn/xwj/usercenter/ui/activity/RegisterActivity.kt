package cn.xwj.usercenter.ui.activity

import android.os.Bundle
import cn.xwj.baselibrary.ext.afterTextChanged
import cn.xwj.baselibrary.ext.content
import cn.xwj.baselibrary.ext.enable
import cn.xwj.baselibrary.ui.activity.BaseMvpActivity
import cn.xwj.usercenter.R
import cn.xwj.usercenter.R.id.*
import cn.xwj.usercenter.di.component.DaggerUserComponent
import cn.xwj.usercenter.di.module.UserModule
import cn.xwj.usercenter.presenter.RegisterPresenter
import cn.xwj.usercenter.presenter.view.RegisterView
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * 注册
 */
class RegisterActivity : BaseMvpActivity<RegisterPresenter>(), RegisterView {
    override fun onRegisterResult(result: String) {
        toast(result)
        finish()
        startActivity<LoginActivity>()
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

        initView()
    }

    private fun initView() {

        mRegisterBtn.enable(mMobileEt) { registerBtnEnable() }
        mRegisterBtn.enable(mVerifyCodeEt) { registerBtnEnable() }
        mRegisterBtn.enable(mPwdEt) { registerBtnEnable() }
        mRegisterBtn.enable(mPwdConfirmEt) { registerBtnEnable() }


        mVerifyCodeBtn.setOnClickListener {
            mVerifyCodeBtn.requestSendVerifyNumber()
            mPresenter.sendVerifyCode(mMobileEt.content)
        }

        mRegisterBtn.setOnClickListener {
            mVerifyCodeBtn.resetCounter()
            mPresenter.register(mMobileEt.content, mPwdEt.content, mVerifyCodeEt.content)
        }
    }


    /**
     * 判断注册按钮是否可用
     */
    private fun registerBtnEnable(): Boolean = mMobileEt.content.isNotEmpty()
            && mVerifyCodeEt.content.isNotEmpty()
            && mPwdEt.content.isNotEmpty()
            && mPwdConfirmEt.content.isNotEmpty()
}
