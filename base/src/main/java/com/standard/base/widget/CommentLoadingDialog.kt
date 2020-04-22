package com.standard.base.widget

import android.app.Dialog
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import com.standard.base.R

/**
 * 通用加载Dialog
 */
class CommentLoadingDialog : Dialog {
    var commentLoading: CommentLoadingDialog? = null

    constructor(context: Context) : this(context, 0)
    constructor(context: Context, themeResId: Int) : super(context, R.style.LoadingDialog) {

        //设置dialog 在布局中的位置
        window?.setGravity(Gravity.CENTER)
        window?.setLayout(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        )
    }

    /**
     * 带文本loading
     *  p1:上下文
     *  p2：展示文本内容
     */
    fun showDlialog(
        context: Context,
        contents: String
    ) {

        val inflater = context
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.comment_loading_layout, null)
        if (contents != null) {
            view.findViewById<TextView>(R.id.tv_loading_content).setText(contents)
        }

        commentLoading = CommentLoadingDialog(context)
        commentLoading!!.setContentView(view)
        commentLoading!!.setCancelable(true)
        commentLoading!!.setCanceledOnTouchOutside(false)
        commentLoading!!.show()
    }

    /**
     * 不带文本loading
     */
    fun showDialog(context: Context) {

        val inflater = context
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.comment_loading_layout, null)

        val tvLoadingcontent = view.findViewById<TextView>(R.id.tv_loading_content)
        tvLoadingcontent.visibility = View.GONE
        commentLoading = CommentLoadingDialog(context)
        commentLoading!!.setContentView(view)
        commentLoading!!.setCancelable(true)
        commentLoading!!.setCanceledOnTouchOutside(false)
        commentLoading!!.show()

    }

    /**
     * 取消dialog
     */
    fun dissmisstion() {
        if (commentLoading != null) {
            commentLoading!!.dismiss()
        }
    }
}


