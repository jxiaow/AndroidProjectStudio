package cn.xwj.kotlinmail.utils

import android.content.Context
import android.widget.ImageView
import cn.xwj.baselibrary.ext.loadUrl
import com.youth.banner.loader.ImageLoader

/**
 * Author: xw
 * Date: 2018-06-01 17:23:31
 * Description: BannerImageLoader: .
 */
class BannerImageLoader : ImageLoader() {

    override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {
        imageView?.loadUrl(path.toString())
    }

}