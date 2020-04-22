package com.standard.base.livedata

import androidx.lifecycle.LiveData
import com.standard.base.base.BaseResponse
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type
import java.util.concurrent.atomic.AtomicBoolean

/**
 *  Create by zsy on 2020/4/20
 *  LiveData回调解析器
 */
class LiveDataCallAdapter<T>(private val reponseType: Type) : CallAdapter<T, LiveData<T>> {

    override fun adapt(call: Call<T>): LiveData<T> {

        return object : LiveData<T>() {
            private val started = AtomicBoolean(false)

            override fun onActive() {
                super.onActive()

                if (started.compareAndSet(false, true)) {
                    //这里进行网络判断
//                    if (!Utils.netIsOk()) {
//                        ToastUtil().show("网络未连接，请检查网络设置")
//                        return
//                    }

                    call.enqueue(object : Callback<T> {
                        override fun onFailure(call: Call<T>, t: Throwable) {
                            //数据异常处理，创建一个BaseResponse类，给属性赋值，在把对象发送到主线程中
                            val value = BaseResponse<T>()
                            value.data = null
                            value.code = -1
                            value.msg = t.message ?: ""
                            postValue(value as T)

                        }

                        override fun onResponse(call: Call<T>, response: Response<T>) {

                            postValue(response.body())


                        }
                    })
                }
            }

            override fun onInactive() {
                super.onInactive()

                if (call.isExecuted) call.cancel()
            }
        }
    }

    /**
     * 响应类型
     */
    override fun responseType(): Type {
        return reponseType

    }
}