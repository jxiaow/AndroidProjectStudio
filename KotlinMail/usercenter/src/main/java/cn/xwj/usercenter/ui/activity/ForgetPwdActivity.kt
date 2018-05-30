package cn.xwj.usercenter.ui.activity

import android.os.Bundle
import android.view.View
import cn.xwj.baselibrary.ext.content
import cn.xwj.baselibrary.ext.enable
import cn.xwj.baselibrary.ui.activity.BaseMvpActivity
import cn.xwj.usercenter.R
import cn.xwj.usercenter.di.component.DaggerUserComponent
import cn.xwj.usercenter.di.module.UserModule
import cn.xwj.usercenter.presenter.ForgetPwdPresenter
import cn.xwj.usercenter.presenter.view.ForgetPwdView
import kotlinx.android.synthetic.main.activity_forget_pwd.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * 注册
 */
class ForgetPwdActivity : BaseMvpActivity<ForgetPwdPresenter>(), ForgetPwdView, View.OnClickListener {
    override fun onForgetPwdResult(result: String) {
        toast(result)
        startActivity<ResetPwdActivity>("mobile" to mMobileEt.content)
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
        setContentView(R.layout.activity_forget_pwd)

        initView()
    }

    private fun initView() {

        mNextBtn.enable(mMobileEt) { btnEnable() }
        mNextBtn.enable(mVerifyCodeEt) { btnEnable() }

        mNextBtn.setOnClickListener(this)
        mVerifyCodeBtn.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.mNextBtn -> mPresenter.forgetPwd(mMobileEt.content, mVerifyCodeEt.content)
            R.id.mVerifyCodeBtn -> {
                mVerifyCodeBtn.requestSendVerifyNumber()
                toast("验证码发送成功")
            }
        }
    }

    /**
     * 判断注册按钮是否可用
     */
    private fun btnEnable(): Boolean = mMobileEt.content.isNotEmpty()
            && mVerifyCodeEt.content.isNotEmpty()
}
