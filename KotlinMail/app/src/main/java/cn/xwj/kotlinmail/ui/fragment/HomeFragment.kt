package cn.xwj.kotlinmail.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.xwj.baselibrary.ui.fragment.BaseFragment
import cn.xwj.kotlinmail.R

/**
 * Author: xw
 * Date: 2018-06-01 14:34:17
 * Description: HomeFragment: .
 */
class HomeFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
}