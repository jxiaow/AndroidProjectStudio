package cn.xwj.kotlinmail.ui.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import cn.xwj.baselibrary.ui.activity.BaseActivity
import cn.xwj.kotlinmail.R
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : BaseActivity() {


    private val mStack = Stack<Fragment>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mBottomNavBar.checkCartBadge(10)
        mBottomNavBar.checkMsgBadge(true)
    }
}
