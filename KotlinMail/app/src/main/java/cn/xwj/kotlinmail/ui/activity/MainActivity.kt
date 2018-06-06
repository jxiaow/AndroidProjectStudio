package cn.xwj.kotlinmail.ui.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import cn.xwj.baselibrary.ui.activity.BaseActivity
import cn.xwj.baselibrary.utils.AppManager
import cn.xwj.baselibrary.utils.AppPreferences
import cn.xwj.goods.common.GoodsConstants
import cn.xwj.goods.event.UpdateCartSizeEvent
import cn.xwj.goods.ui.fragment.CartFragment
import cn.xwj.goods.ui.fragment.CategoryFragment
import cn.xwj.kotlinmail.R
import cn.xwj.kotlinmail.ui.fragment.HomeFragment
import cn.xwj.kotlinmail.ui.fragment.MyFragment
import cn.xwj.kotlinmail.widget.DefaultOnTabSelectedListener
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import java.util.*

class MainActivity : BaseActivity() {


    private val mStack = Stack<Fragment>()
    private var pressTime = 0L

    private val mHomeFragment: HomeFragment by lazy { HomeFragment() }
    private val mCategoryFragment: CategoryFragment by lazy { CategoryFragment() }
    private val mCartFragment: CartFragment by lazy { CartFragment() }
    private val mMsgFragment: HomeFragment by lazy { HomeFragment() }
    private val mMyFragment: MyFragment by lazy { MyFragment() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initFragment()
        initBottomNavBar()
        changeFragment(0)
        loadCartSize()
        initObserve()

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

    /*
        初始化监听，购物车数量变化及消息标签是否显示
     */
    private fun initObserve() {
        Bus.observe<UpdateCartSizeEvent>()
                .subscribe {
                    loadCartSize()
                }.registerInBus(this)

//        Bus.observe<MessageBadgeEvent>()
//                .subscribe { t: MessageBadgeEvent ->
//                    run {
//                        mBottomNavBar.checkMsgBadge(t.isVisible)
//                    }
//                }.registerInBus(this)
    }

    /*
        加载购物车数量
     */
    private fun loadCartSize() {
        mBottomNavBar.checkCartBadge(AppPreferences.instance.get(GoodsConstants.SP_CART_SIZE, 0))
    }

    /*
        取消Bus事件监听
     */
    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }

    /*
        重写Back事件，双击退出
     */
    override fun onBackPressed() {
        val time = System.currentTimeMillis()
        if (time - pressTime > 2000) {
            toast("再按一次退出程序")
            pressTime = time
        } else {
            AppManager.instances.exitApp()
        }
    }
}

