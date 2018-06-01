package cn.xwj.kotlinmail.ui.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import cn.xwj.baselibrary.ui.activity.BaseActivity
import cn.xwj.kotlinmail.R
import cn.xwj.kotlinmail.ui.fragment.HomeFragment
import cn.xwj.kotlinmail.widget.DefaultOnTabSelectedListener
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : BaseActivity() {


    private val mStack = Stack<Fragment>()

    private val mHomeFragment: HomeFragment by lazy { HomeFragment() }
    private val mCategoryFragment: HomeFragment by lazy { HomeFragment() }
    private val mCartFragment: HomeFragment by lazy { HomeFragment() }
    private val mMsgFragment: HomeFragment by lazy { HomeFragment() }
    private val mMyFragment: HomeFragment by lazy { HomeFragment() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initFragment()
        initBottomNavBar()
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
