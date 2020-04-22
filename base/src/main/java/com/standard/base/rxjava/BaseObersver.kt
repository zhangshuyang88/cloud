package com.standard.base.rxjava


import android.util.Log
import io.reactivex.Observer
import io.reactivex.disposables.Disposable


/**
 * 定义的base观察者
 */
abstract class BaseObersver<T> : Observer<Response<T>> {


    override fun onComplete() {
        Log.e("onComplete:", "onComplete")


    }

    override fun onSubscribe(d: Disposable) {
        Log.e("onSubscribe:", "onSubscribe")
        startLoading()


    }

    /**
     * 在这里可以根据状态码分装一些抽象函数作为处理
     */
    override fun onNext(t: Response<T>) {
        Log.e("onNext:", "onNext")

        Log.e("在baseobserver中收到的总数据", "${t}")

        if (t.code == 200) {
            success(t.data)

        } else {
            failed(t.msg)

        }
    }

    override fun onError(e: Throwable) {
        Log.e("onError:", "onError")

        Log.e("aaa", e.toString())

    }

    //成功回调方法
    abstract fun success(data: T?)

    //失败回调方法
    abstract fun failed(errorMsg: String?)

    abstract fun startLoading()


}