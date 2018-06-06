package cn.xwj.usercenter.ui.activity

import android.os.Bundle
import android.view.View
import cn.xwj.baselibrary.ext.content
import cn.xwj.baselibrary.ext.enable
import cn.xwj.baselibrary.ui.activity.BaseMvpActivity
import cn.xwj.provider.common.RoutePath
import cn.xwj.usercenter.R
import cn.xwj.usercenter.di.component.DaggerUserComponent
import cn.xwj.usercenter.di.module.UserModule
import cn.xwj.usercenter.presenter.LoginPresenter
import cn.xwj.usercenter.presenter.view.LoginView
import com.alibaba.android.arouter.facade.annotation.Route
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * 注册
 */
@Route(path = RoutePath.UserCenter.LOGIN_PATH)
class LoginActivity : BaseMvpActivity<LoginPresenter>(), LoginView, View.OnClickListener {
    override fun onLoginResult(result: String) {
        toast(result)
        finish()
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
        setContentView(R.layout.activity_login)

        initView()
    }

    private fun initView() {

        mLoginBtn.enable(mMobileEt) { registerBtnEnable() }
        mLoginBtn.enable(mPwdEt) { registerBtnEnable() }

        mLoginBtn.setOnClickListener(this)
        mForgetPwdTv.setOnClickListener(this)
        mHeaderBar.getRightView().setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.mLoginBtn -> mPresenter.login(mMobileEt.content, mPwdEt.content, "")
            R.id.mRightTv -> startActivity<RegisterActivity>()
            R.id.mForgetPwdTv -> startActivity<ForgetPwdActivity>()

        }
    }

    /**
     * 判断注册按钮是否可用
     */
    private fun registerBtnEnable(): Boolean = mMobileEt.content.isNotEmpty()
            && mPwdEt.content.isNotEmpty()
}
