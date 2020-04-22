package com.standard.cloud.api


import androidx.lifecycle.LiveData
import com.standard.base.base.BaseResponse
import com.standard.cloud.bean.UserBean
import retrofit2.http.GET

/**
 * 通类接口
 */
interface ApiService {
    //这里进行进行接口的书写
    @GET("login")
    fun login(): LiveData<BaseResponse<UserBean>>

}