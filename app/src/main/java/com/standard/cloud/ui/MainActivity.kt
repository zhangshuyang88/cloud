package com.standard.cloud.ui

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.standard.base.base.BaseActivity
import com.standard.base.net.RetrofitUtils
import com.standard.base.utils.ToastUtils
import com.standard.base.widget.CommentNoticeDialog
import com.standard.cloud.R
import com.standard.cloud.api.ApiService
import com.standard.cloud.databinding.ActivityMainBinding
import com.standard.cloud.viewmodel.MainViewModel

/**
 * create by zsy on 2020/4/22
 * 主页
 */
class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {


    //dialog 成员变量
    var noticeDialog: CommentNoticeDialog? = null

    lateinit var apiService: ApiService

    //布局
    override fun getLayoutId(): Int {

        return R.layout.activity_main

    }

    @SuppressLint("CommitPrefEdits")
    override fun initView() {
        //循环遍历数据
        apiService = RetrofitUtils.mInstance.create(ApiService::class.java)

    }


    override fun initData() {
        login()
    }

    override fun loadData() {

    }

    override fun initViewModel(): MainViewModel {

        return ViewModelProvider(this).get(MainViewModel::class.java)
    }

    /**
     * 登陆操作
     */
    private fun login() {
        //这是登陆操作
        dialog.showDialog(this)
        apiService.login().observe(this, Observer {
            if (it?.data == null) return@Observer

            if (it.code == 200) {
                ToastUtils.showToast(this, "登陆成功")
                val data = it.data
                Log.e("登陆人姓名：", "${data?.userInfo?.name}")
                Log.e("登陆人年龄：", "${data?.userInfo?.age}")
                //做相关的业务逻辑
                dialog.dissmisstion()
            }
        })
    }

    /**
     * 创建通用的dialog
     */
    private fun mainCreateDlialog() {
        noticeDialog = CommentNoticeDialog.Builder(this)
            .setTitls("提示")
            .setcontents("这是第一次尝试封装通用类公告diaog")
            .setDialogListener("确定按钮", View.OnClickListener {
                noticeDialog?.dismiss()
            }).create()

        noticeDialog!!.show()
    }
}
