package com.standard.base

import android.app.Application

class IApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this


    }

    /**
     * 静态变量
     */
    companion object {
        private var instance: IApplication? = null
        fun getInstance() = instance!!

    }
}