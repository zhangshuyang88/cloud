package com.standard.base.base

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel

/**
 * Create by zsy on 2020/4/22
 * 所有带有 ViewModel 的Activity 基类
 */
abstract class BaseActivity<VM : BaseViewModel, DB : ViewDataBinding> : BaseNoVMActivity<DB>() {

    var viewModel: VM? = null

    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    /**
     * 子类创建 VM 对象
     */
    abstract fun initViewModel(): VM

    /**
     * 初始化VM对象
     */
    override fun getVM() {
        viewModel = initViewModel()
    }


}