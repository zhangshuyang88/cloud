package com.standard.base.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import androidx.core.app.NotificationManagerCompat


/**
 *  Create by zsy on 2020/4/21
 *  通用工具类
 */
class CommentUtils {

    companion object {

        /**
         * 判断网络是否打开
         */
        fun isOpenNet(context: Context): Boolean {

            val connectivityManager = context
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (connectivityManager == null) {

            } else {
                val mNetworkInfo: NetworkInfo = connectivityManager.getActiveNetworkInfo()

                if (mNetworkInfo != null) {
                    return mNetworkInfo.isAvailable
                }
            }
            return false
        }

        /**
         * 判断是否已经打开通知栏
         */
        fun isOpenNotice(context: Context): Boolean {
            var isOpened: Boolean = false

            try {
                isOpened = NotificationManagerCompat.from(context).areNotificationsEnabled();
            } catch (e: Exception) {
                e.printStackTrace();
                isOpened = false;
            }
            return isOpened;
        }

        /**
         * 打开手机设置页面工具类
         */
        fun goToSetting(context: Context) {

            val intent = Intent()
            if (Build.VERSION.SDK_INT >= 26) {
                // android 8.0引导
                intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
                intent.putExtra("android.provider.extra.APP_PACKAGE", getPackageName(context));
            } else if (Build.VERSION.SDK_INT >= 21) {
                // android 5.0-7.0
                intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS")
                intent.putExtra("app_package", getPackageName(context))
                intent.putExtra("app_uid", getUid(context))
            }
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)

        }

        /**
         * 获取包名
         */
        fun getPackageName(context: Context): String {

            try {
                val packageManager = context.packageManager
                val packageInfo = packageManager.getPackageInfo(
                    context.packageName, 0
                )
                return packageInfo.packageName
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return ""
        }

        /**
         * 获取版本号
         */

        fun getVersionNum(context: Context): Int {

            try {
                val packageManager = context.packageManager
                val packageInfo = packageManager.getPackageInfo(
                    context.packageName, 0
                )
                return packageInfo.versionCode
            } catch (e: Exception) {

                e.printStackTrace()
            }
            return 0
        }

        /**
         * 获取应用名称
         */

        fun getAppName(context: Context): String {

            try {
                val packageManager = context.packageManager
                val packageInfo = packageManager.getPackageInfo(
                    context.packageName, 0
                )
                val labelRes = packageInfo.applicationInfo.labelRes
                return context.resources.getString(labelRes)
            } catch (e: Exception) {
                e.printStackTrace()

            }
            return ""
        }

        /**
         * 获取用户的UID,版本号是与包有关
         */

        @SuppressLint("WrongConstant")
        fun getUid(context: Context): Int {
            try {
                val packageManager = context.packageManager
                val applicationInfo =
                    packageManager.getApplicationInfo(
                        "com.standard.cloud",
                        PackageManager.GET_ACTIVITIES
                    )

                return applicationInfo.uid
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return -1
        }
    }
}