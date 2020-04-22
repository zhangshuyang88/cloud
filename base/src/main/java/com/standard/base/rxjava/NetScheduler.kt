package com.standard.base.rxjava

import androidx.lifecycle.LifecycleOwner
//import com.trello.rxlifecycle3.android.lifecycle.kotlin.bindToLifecycle
import io.reactivex.ObservableTransformer
//import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

import java.util.concurrent.TimeUnit

/**
 * 封装的调度器
 */
object NetScheduler {
//
//    fun <T> compose(lifecycleOwner: LifecycleOwner): ObservableTransformer<T, T> {
//
//        return ObservableTransformer {
//            it.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .bindToLifecycle(lifecycleOwner)
//                .debounce(1, TimeUnit.SECONDS) //防止1秒内重复请求
//        }
//    }
}