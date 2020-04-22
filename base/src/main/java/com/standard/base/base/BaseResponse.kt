package com.standard.base.base

/**
 *  Create by zsy on 2020/4/20
 */
class BaseResponse<T> {

    var code: Int = 0
    var msg: String = ""
    var data: T? = null
}