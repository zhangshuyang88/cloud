package com.standard.base.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.standard.base.widget.CommentLoadingDialog

/**
 *  Create by zsy on 2020/4/22
 *
 * 没有viewmodel 的activity基础类
 */
abstract class BaseNoVMActivity<DB : ViewDataBinding> : AppCompatActivity() {

    var dataBinding: DB? = null

    /**
     * 数据加载的loading
     */
    protected val dialog: CommentLoadingDialog by lazy {
        CommentLoadingDialog(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //数据绑定 用databingding+liveData


        dataBinding = getDataBinding(getLayoutId())
        //获取VM对象，初始化前面执行，可以在初始化中进行调用。
        getVM()
        initView()
        initData()
        loadData()
    }

    /**
     * 获取databinding对象，绑定视图
     */
    fun getDataBinding(resId: Int): DB {

        return DataBindingUtil.setContentView(this, getLayoutId())
    }
    //初始化id
    abstract fun getLayoutId(): Int

    //初始化view
    abstract fun initView()

    //初始化数据
    abstract fun initData()

    //家在数据
    abstract fun loadData()


    //展示相应的操作
    fun showDialog() {
        dialog.show()
    }

    //隐藏
    fun dissDialog() {
        dialog.dismiss()
    }

    /**
     * 初始化VM对象
     */
    open protected fun getVM() {


    }

    override fun onDestroy() {
        super.onDestroy()
        if (dialog != null && dialog.isShowing) {
            dialog.dismiss()
        }

        if (dataBinding != null) {
            dataBinding!!.unbind()
        }
    }

}