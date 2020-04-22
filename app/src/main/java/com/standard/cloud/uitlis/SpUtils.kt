package com.standard.cloud.uitlis

import android.content.Context
import android.content.SharedPreferences
import com.standard.base.IApplication
import com.standard.base.constant.Constants

/**
 *  Create by zsy on 2020/4/21
 */
class SpUtils {
    companion object {
        private val SHAREDNAME = Constants.prefsName


        fun saveData(key: String?, value: Any) {

            val sp: SharedPreferences =
                IApplication.getInstance().getSharedPreferences(SHAREDNAME, Context.MODE_PRIVATE)
            val editor = sp.edit()
            if (value is String) {
                editor.putString(key, value.toString())
            } else if (value is Boolean) {
                editor.putBoolean(key, java.lang.Boolean.parseBoolean(value.toString()))
            } else if (value is Float) {
                editor.putFloat(key, value.toString().toFloat())
            } else if (value is Int) {
                editor.putInt(key, value.toString().toInt())
            } else if (value is Long) {
                editor.putLong(key, value.toString().toLong())
            } else {
                editor.putString(key, value.toString())
            }
            editor.commit()
        }

        fun removeData(key: String?) {
            val sp: SharedPreferences =
                IApplication.getInstance().getSharedPreferences(SHAREDNAME, 0)
            if (sp.contains(key)) sp.edit().remove(key).commit()
        }

        fun getString(key: String?, defaultValue: String?): String? {
            val sp: SharedPreferences =
                IApplication.getInstance().getSharedPreferences(SHAREDNAME, 0)
            return sp.getString(key, defaultValue)
        }

        fun getBoolean(key: String?, defaultValue: Boolean): Boolean {
            val sp: SharedPreferences =
                IApplication.getInstance().getSharedPreferences(SHAREDNAME, 0)
            return sp.getBoolean(key, defaultValue)
        }

        fun getFloat(key: String?, defaultValue: Float): Float {
            val sp: SharedPreferences =
                IApplication.getInstance().getSharedPreferences(SHAREDNAME, 0)
            return sp.getFloat(key, defaultValue)
        }

        fun getInt(key: String?, defaultValue: Int): Int {
            val sp: SharedPreferences =
                IApplication.getInstance().getSharedPreferences(SHAREDNAME, 0)
            return sp.getInt(key, defaultValue)
        }

        fun getLong(key: String?, defaultValue: Long): Long {
            val sp: SharedPreferences =
                IApplication.getInstance().getSharedPreferences(SHAREDNAME, 0)
            return sp.getLong(key, defaultValue)
        }
    }
}