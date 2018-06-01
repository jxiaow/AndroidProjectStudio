package cn.xwj.kotlinmail.widget

import android.content.Context
import android.util.AttributeSet
import cn.xwj.kotlinmail.R
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.ashokvarma.bottomnavigation.ShapeBadgeItem
import com.ashokvarma.bottomnavigation.TextBadgeItem

/**
 * Author: xw
 * Date: 2018-05-31 15:54:46
 * Description: BottomNavBar: .
 */
class BottomNavBar @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : BottomNavigationBar(context, attrs, defStyleAttr) {

    private val mCartBadgeItem: TextBadgeItem
    private val mMsgBadgeItem: ShapeBadgeItem

    init {

        //添加tab
        //主页
        addBottomItem(R.drawable.btn_nav_home_press, R.string.nav_bar_home, R.drawable.btn_nav_home_normal)
        //分类
        addBottomItem(R.drawable.btn_nav_category_press, R.string.nav_bar_category, R.drawable.btn_nav_category_normal)
        //购物车
        val cartBottomItem = addBottomItem(R.drawable.btn_nav_cart_press, R.string.nav_bar_cart, R.drawable.btn_nav_cart_normal)
        //添加角标
        mCartBadgeItem = TextBadgeItem()
        cartBottomItem.setBadgeItem(mCartBadgeItem)

        //消息
        val messageBottomItem = addBottomItem(R.drawable.btn_nav_msg_press, R.string.nav_bar_msg, R.drawable.btn_nav_msg_normal)
        //添加角标
        mMsgBadgeItem = ShapeBadgeItem()
        mMsgBadgeItem.setShape(ShapeBadgeItem.SHAPE_OVAL)
        messageBottomItem.setBadgeItem(mMsgBadgeItem)

        //我
        addBottomItem(R.drawable.btn_nav_user_press, R.string.nav_bar_user, R.drawable.btn_nav_user_normal)

        //设置各种属性
        setMode(BottomNavigationBar.MODE_FIXED)
        setBackgroundStyle(BACKGROUND_STYLE_STATIC)
        setBarBackgroundColor(R.color.common_white)
        this.setFirstSelectedPosition(0)
        this.initialise()
    }


    private fun addBottomItem(selectIconResource: Int, title: Int, unSelectIconResource: Int): BottomNavigationItem {
        val bottomItem: BottomNavigationItem =
                BottomNavigationItem(selectIconResource, resources.getString(title))
                        .setInactiveIconResource(unSelectIconResource)
                        .setInActiveColor(R.color.text_normal)
                        .setActiveColor(R.color.common_blue_light)
        addItem(bottomItem)
        return bottomItem
    }


    fun checkCartBadge(count: Int) {
        when {
            count <= 0 -> mCartBadgeItem.hide()
            else -> {
                mCartBadgeItem.show()
                mCartBadgeItem.setText("$count")
            }
        }
    }

    /*
        检查消息Tab是否显示标签
     */
    fun checkMsgBadge(isVisibility: Boolean) {
        if (isVisibility) mMsgBadgeItem.show() else mMsgBadgeItem.hide()
    }
}