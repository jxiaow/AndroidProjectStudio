package cn.xwj.kotlinmail.ui.adapter

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.xwj.baselibrary.ext.loadUrl
import cn.xwj.kotlinmail.R
import kotlinx.android.synthetic.main.layout_topic_item.view.*

/**
 * Author: xw
 * Email:i.xiaowujiang@gmail.com
 * Date: 2018-06-02 2018/6/2
 * Description: TopicAdapter
 */
class TopicAdapter(private val context: Context, private val dataList: MutableList<String>)
    : PagerAdapter() {
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(context)
                .inflate(R.layout.layout_topic_item, null)
        view.mTopicIv.loadUrl(dataList[position])
        container.addView(view)
        return view

    }


    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun getCount(): Int = dataList.size
}