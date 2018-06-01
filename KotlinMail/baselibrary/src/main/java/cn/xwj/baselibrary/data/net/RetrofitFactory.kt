package cn.xwj.baselibrary.data.net

import cn.xwj.baselibrary.common.BaseConstants
import cn.xwj.baselibrary.utils.AppPreferences
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Author: xw
 * Date: 2018-05-28 17:30:21
 * Description: RetrofitFactory: .
 */
class RetrofitFactory {


    companion object {
        val instance: RetrofitFactory by lazy { RetrofitFactory() }
    }

    private val retrofit: Retrofit
    private val interceptor: Interceptor

    init {

        interceptor = Interceptor { chain ->
            val request = chain.request()
                    .newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .addHeader("charset", "utf-8")
                    .addHeader("token", AppPreferences.instance.get(BaseConstants.KEY_SP_TOKEN, ""))
                    .build()
            chain.proceed(request)
        }

        retrofit = Retrofit.Builder()
                .baseUrl(BaseConstants.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(initClient())
                .build()

    }

    private fun initClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(initInterceptor())
                .addInterceptor(interceptor)
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build()
    }

    private fun initInterceptor(): Interceptor {
        val loggerInterceptor = HttpLoggingInterceptor()
        loggerInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggerInterceptor
    }

    fun <T> create(clazz: Class<T>): T {
        return retrofit.create(clazz)
    }
}