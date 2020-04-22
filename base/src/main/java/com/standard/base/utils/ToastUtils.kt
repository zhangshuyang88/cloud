package com.standard.base.utils

import android.content.Context
import android.widget.Toast

/**
 *  Create by zsy on 2020/4/20
 *  Toast 提示工具类
 */
class ToastUtils {

    companion object {

        /**
         * 根据字符串提示
         */
        fun showToast(context: Context, string: String) {
            Toast.makeText(context, string, Toast.LENGTH_LONG).show()
        }

        /**
         * 根据resId关联的字符串
         */
        fun showToastOfResID(context: Context, resId: Int) {
            Toast.makeText(context, resId, Toast.LENGTH_LONG).show()
        }
    }

}