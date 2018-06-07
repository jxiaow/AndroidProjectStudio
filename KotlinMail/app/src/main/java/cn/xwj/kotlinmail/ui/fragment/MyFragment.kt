package cn.xwj.kotlinmail.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.xwj.baselibrary.ext.loadUrl
import cn.xwj.baselibrary.ui.fragment.BaseFragment
import cn.xwj.baselibrary.utils.AppPreferences
import cn.xwj.kotlinmail.R
import cn.xwj.kotlinmail.ui.activity.SettingActivity
import cn.xwj.order.common.OrderConstant
import cn.xwj.order.common.OrderStatus
import cn.xwj.order.ui.activity.OrderActivity
import cn.xwj.order.ui.activity.ShipAddressActivity
import cn.xwj.provider.common.RoutePath
import cn.xwj.provider.ext.afterLogin
import cn.xwj.provider.ext.isLogin
import com.alibaba.android.arouter.launcher.ARouter
import com.kotlin.provider.common.ProviderConstant
import kotlinx.android.synthetic.main.fragment_me.*
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast

/**
 * Author: xw
 * Date: 2018-06-01 14:34:17
 * Description: HomeFragment: .
 */
class MyFragment : BaseFragment(), View.OnClickListener {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_me, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onStart() {
        super.onStart()
        loadData()
    }

    private fun loadData() {
        if (isLogin()) {
            val userName = AppPreferences.instance.get(ProviderConstant.KEY_SP_USER_NAME, "")
            val userIcon = AppPreferences.instance.get(ProviderConstant.KEY_SP_USER_ICON, "")

            if (userName.isNotEmpty()) {
                mUserNameTv.text = userName
            }

            if (userIcon.isNotEmpty()) {
                mUserIconIv.loadUrl(userIcon)
            }
        } else {
            mUserIconIv.setImageResource(R.drawable.icon_default_user)
            mUserNameTv.text = getString(R.string.un_login_text)
        }

    }

    /*
        初始化视图
     */
    private fun initView() {
        mUserIconIv.setOnClickListener(this)
        mUserNameTv.setOnClickListener(this)

        mWaitPayOrderTv.setOnClickListener(this)
        mWaitConfirmOrderTv.setOnClickListener(this)
        mCompleteOrderTv.setOnClickListener(this)
        mAllOrderTv.setOnClickListener(this)
        mAddressTv.setOnClickListener(this)
        mShareTv.setOnClickListener(this)
        mSettingTv.setOnClickListener(this)

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.mUserIconIv, R.id.mUserNameTv ->
                afterLogin {
                    ARouter.getInstance().build(RoutePath.UserCenter.USER_INFO_PATH).navigation()
                }

            R.id.mShareTv -> {
                toast(R.string.coming_soon_tip)
            }
            R.id.mSettingTv -> {
                startActivity<SettingActivity>()
            }

            R.id.mWaitPayOrderTv -> {
                startActivity<OrderActivity>(OrderConstant.KEY_ORDER_STATUS to OrderStatus.ORDER_WAIT_PAY)
            }
            R.id.mWaitConfirmOrderTv -> {
                startActivity<OrderActivity>(OrderConstant.KEY_ORDER_STATUS to OrderStatus.ORDER_WAIT_CONFIRM)
            }
            R.id.mCompleteOrderTv -> {
                startActivity<OrderActivity>(OrderConstant.KEY_ORDER_STATUS to OrderStatus.ORDER_COMPLETED)
            }
            R.id.mAllOrderTv -> {
                afterLogin {
                    startActivity<OrderActivity>()
                }
            }

            R.id.mAddressTv -> {
                afterLogin {
                    startActivity<ShipAddressActivity>()
                }
            }
            R.id.mShareTv -> {
                toast(R.string.coming_soon_tip)
            }
            R.id.mSettingTv -> {
                startActivity<SettingActivity>()
            }
        }
    }

}