package com.standard.base.livedata

import androidx.lifecycle.LiveData
import com.standard.base.base.BaseResponse
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 *  Create by zsy on 2020/4/20
 *  LiveDataCallAdapter 工厂类
 */
class LiveDataCallAdapterFactory : CallAdapter.Factory() {
    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        if (getRawType(returnType) != LiveData::class.java) return null
        val observableType = getParameterUpperBound(0, returnType as ParameterizedType)
        val rawType = getRawType(observableType)

        if (rawType != BaseResponse::class.java) {
            throw IllegalArgumentException("type must be BaseResponse")
        }

        if (observableType !is ParameterizedType) {
            throw IllegalArgumentException("resource must be parameterized")
        }
        //还没有返回LiveDataCallAdapter
        return LiveDataCallAdapter<Any>(observableType)

    }

}