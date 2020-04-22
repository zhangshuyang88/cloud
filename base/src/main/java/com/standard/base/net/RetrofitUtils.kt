package com.standard.base.net

import com.standard.base.livedata.LiveDataCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Retrofit 工具类
 */
class RetrofitUtils {

    //给retrofit 进行初始化配置
    constructor() {
        initRetrofit()
    }

    companion object {
        //baseUrl
        private val BASE_URL = "https://www.fastmock.site/mock/ba8bb1e56dfc65a13568aac175a6b5ae/cloud/"
        //超时时间
        private val TIMEOUT = 60
        private var mRetrofit: Retrofit? = null
        val mInstance: RetrofitUtils by lazy {
            RetrofitUtils()
        }
    }

    /**
     * 初始化Retrofit
     */
    private fun initRetrofit() {
        val client = OkHttpClient
            .Builder()
            .connectTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor())//请求日志
            .build()

        mRetrofit = Retrofit.Builder()
            .baseUrl(BASE_URL) // 设置请求的域名
            .addConverterFactory(GsonConverterFactory.create())// 设置解析转换工厂，用自己定义的
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .client(client)
            .build()
    }

    /**
     * 创建API
     */
    fun <T> create(clazz: Class<T>?): T {
        return mRetrofit!!.create(clazz)
    }
}