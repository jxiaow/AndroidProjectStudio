package cn.xwj.baselibrary.data.net

import cn.xwj.baselibrary.common.BaseConstants
import retrofit2.Retrofit

/**
 * Author: xw
 * Date: 2018-05-28 17:30:21
 * Description: RetrofiFactory: .
 */
class RetrofiFactory {


    companion object {
        val instance: RetrofiFactory by lazy { RetrofiFactory() }
    }

    private val retrofit: Retrofit

    init {
        retrofit = Retrofit.Builder()
                .baseUrl(BaseConstants.BASE_URL)
                .addCallAdapterFactory()

    }
}