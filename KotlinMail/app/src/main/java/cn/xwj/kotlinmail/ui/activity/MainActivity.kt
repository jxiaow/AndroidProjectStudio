package cn.xwj.kotlinmail.ui.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import cn.xwj.baselibrary.ui.activity.BaseActivity
import cn.xwj.goods.ui.fragment.CategoryFragment
import cn.xwj.kotlinmail.R
import cn.xwj.kotlinmail.ui.fragment.HomeFragment
import cn.xwj.kotlinmail.ui.fragment.MyFragment
import cn.xwj.kotlinmail.widget.DefaultOnTabSelectedListener
import cn.xwj.provider.common.RoutePath
import com.alibaba.android.arouter.launcher.ARouter
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : BaseActivity() {


    private val mStack = Stack<Fragment>()

    private val mHomeFragment: HomeFragment by lazy { HomeFragment() }
    private val mCategoryFragment: CategoryFragment by lazy { CategoryFragment() }
    private val mCartFragment: HomeFragment by lazy { HomeFragment() }
    private val mMsgFragment: HomeFragment by lazy { HomeFragment() }
    private val mMyFragment: MyFragment by lazy { MyFragment() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initFragment()
        initBottomNavBar()
        changeFragment(0)


    }

    private fun initBottomNavBar() {

        mBottomNavBar.setTabSelectedListener(object : DefaultOnTabSelectedListener() {
            override fun onTabSelected(position: Int) {
                changeFragment(position)
            }
        })
        mBottomNavBar.checkMsgBadge(false)
    }

    private fun changeFragment(position: Int) {

        with(supportFragmentManager.beginTransaction()) {
            for (fragment in mStack) {
                hide(fragment)
            }

            show(mStack[position])
            commit()
        }
    }

    private fun initFragment() {

        with(supportFragmentManager.beginTransaction()) {
            add(R.id.mContainer, mHomeFragment)
            add(R.id.mContainer, mCategoryFragment)
            add(R.id.mContainer, mCartFragment)
            add(R.id.mContainer, mMsgFragment)
            add(R.id.mContainer, mMyFragment)
            commit()
        }

        with(mStack) {
            add(mHomeFragment)
            add(mCategoryFragment)
            add(mCartFragment)
            add(mMsgFragment)
            add(mMyFragment)
        }
    }
}
