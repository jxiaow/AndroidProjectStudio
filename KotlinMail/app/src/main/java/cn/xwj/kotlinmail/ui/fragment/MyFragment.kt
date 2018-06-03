package cn.xwj.kotlinmail.ui.fragment

import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.xwj.baselibrary.common.BaseConstants
import cn.xwj.baselibrary.ext.loadUrl
import cn.xwj.baselibrary.ui.fragment.BaseFragment
import cn.xwj.baselibrary.utils.AppPreferences
import cn.xwj.kotlinmail.R
import cn.xwj.kotlinmail.ui.adapter.HomeDiscountAdapter
import cn.xwj.kotlinmail.ui.adapter.TopicAdapter
import cn.xwj.kotlinmail.utils.BannerImageLoader
import cn.xwj.provider.common.RoutePath
import cn.xwj.provider.ext.afterLogin
import cn.xwj.provider.ext.isLogin
import com.alibaba.android.arouter.launcher.ARouter
import com.kotlin.mall.common.MainConstant.Companion.HOME_BANNER_FOUR
import com.kotlin.mall.common.MainConstant.Companion.HOME_BANNER_ONE
import com.kotlin.mall.common.MainConstant.Companion.HOME_BANNER_THREE
import com.kotlin.mall.common.MainConstant.Companion.HOME_BANNER_TWO
import com.kotlin.mall.common.MainConstant.Companion.HOME_DISCOUNT_FIVE
import com.kotlin.mall.common.MainConstant.Companion.HOME_DISCOUNT_FOUR
import com.kotlin.mall.common.MainConstant.Companion.HOME_DISCOUNT_ONE
import com.kotlin.mall.common.MainConstant.Companion.HOME_DISCOUNT_THREE
import com.kotlin.mall.common.MainConstant.Companion.HOME_DISCOUNT_TWO
import com.kotlin.mall.common.MainConstant.Companion.HOME_TOPIC_FIVE
import com.kotlin.mall.common.MainConstant.Companion.HOME_TOPIC_FOUR
import com.kotlin.mall.common.MainConstant.Companion.HOME_TOPIC_ONE
import com.kotlin.mall.common.MainConstant.Companion.HOME_TOPIC_THREE
import com.kotlin.mall.common.MainConstant.Companion.HOME_TOPIC_TWO
import com.kotlin.provider.common.ProviderConstant
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_me.*
import me.crosswall.lib.coverflow.CoverFlow
import org.jetbrains.anko.imageResource
import org.jetbrains.anko.support.v4.toast

/**
 * Author: xw
 * Date: 2018-06-01 14:34:17
 * Description: HomeFragment: .
 */
class MyFragment : BaseFragment(), View.OnClickListener {
    override fun onClick(v: View) {
        when (v.id) {
            R.id.mUserIconIv, R.id.mUserNameTv ->
                afterLogin {
                    ARouter.getInstance().build(RoutePath.UserCenter.USER_INFO_PATH)
                }

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_me, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
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

    private fun initView() {
        mUserIconIv.setOnClickListener(this)
        mUserNameTv.setOnClickListener(this)
    }
}