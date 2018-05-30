package cn.xwj.usercenter.ui.activity

import android.os.Bundle
import cn.xwj.baselibrary.ext.content
import cn.xwj.baselibrary.ext.enable
import cn.xwj.baselibrary.ui.activity.BaseMvpActivity
import cn.xwj.usercenter.R
import cn.xwj.usercenter.di.component.DaggerUserComponent
import cn.xwj.usercenter.di.module.UserModule
import cn.xwj.usercenter.presenter.ResetPwdPresenter
import cn.xwj.usercenter.presenter.view.ResetPwdView
import kotlinx.android.synthetic.main.activity_reset_pwd.*
import org.jetbrains.anko.clearTop
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.singleTop
import org.jetbrains.anko.toast

/**
 * 注册
 */
class ResetPwdActivity : BaseMvpActivity<ResetPwdPresenter>(), ResetPwdView {
    override fun onResetPwdResult(result: String) {
        toast(result)
        startActivity(intentFor<LoginActivity>().singleTop().clearTop())
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
        setContentView(R.layout.activity_reset_pwd)

        initView()
    }

    private fun initView() {

        mConfirmBtn.enable(mPwdEt) { btnEnable() }
        mConfirmBtn.enable(mPwdConfirmEt) { btnEnable() }

        mConfirmBtn.setOnClickListener {
            mPresenter.resetPwd(intent.getStringExtra("mobile"), mPwdEt.content)
        }
    }

    /**
     * 判断注册按钮是否可用
     */
    private fun btnEnable(): Boolean = mPwdEt.content.isNotEmpty()
            && mPwdConfirmEt.content.isNotEmpty()
}
