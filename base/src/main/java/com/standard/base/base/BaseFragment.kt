package com.standard.base.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.standard.base.widget.CommentLoadingDialog

/**
 * Fragment基类
 */
abstract class BaseFragment : Fragment() {

    var fragmentContext: Context? = null
    /**
     * 数据加载的loading
     */
    protected val dialog: CommentLoadingDialog by lazy {
        CommentLoadingDialog(this.fragmentContext!!)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.fragmentContext = context


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initView()
        initData()
        loadData()
        return inflater.inflate(getLayoutId(), container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    abstract fun getLayoutId(): Int
    abstract fun initView()
    abstract fun initData()
    abstract fun loadData()

    //显示dialog
    fun showDialog() = dialog.show()


    //隐藏dialog
    fun dissDialog() = dialog.dismiss()


}