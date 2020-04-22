package com.standard.base.rxjava

/**
 * 根据后台数据格式封装的统一数据格式的一个baseBean类
 */
data class Response<T>(var code: Int?, var msg: String?, var data: T?)
