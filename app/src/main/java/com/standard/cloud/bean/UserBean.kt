package com.standard.cloud.bean

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * 登陆人用户信息
 */
@Parcelize
data class UserBean  (val code: Int, val msg: String, val userInfo: UserInfo) : Parcelable
@Parcelize
data class UserInfo(val age: Int, val name: String) : Parcelable
