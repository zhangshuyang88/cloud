package com.standard.base.widget

import android.app.Dialog
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import com.standard.base.R

/**
 * 通用公告Dialog
 */
class CommentNoticeDialog : Dialog {
    //后面这个this ->调用两个参数的构造函数
    constructor(context: Context) : this(context, 0)

    constructor(context: Context, themeResId: Int) : super(context, R.style.dialog) {

        //设置dialog 在布局中的位置
        window?.setGravity(Gravity.CENTER)
        window?.setLayout(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        )
    }

    /**
     * 有一个疑问，就是在构造者中能不能获取布局文件中的控件就能给控件设置相关的点击事件
     */
    //构建器
    class Builder(private val context: Context) {

        private var titles: String? = null
        private var contents: String? = null
        private var buttonContents: String? = null
        private var confirmLister: OnClickListener? = null


        //设置标题
        fun setTitls(titls: String): Builder {
            this.titles = titls
            return this

        }

        //设置公告内容
        fun setcontents(contents: String): Builder {
            this.contents = contents
            return this
        }

        //给控件设置监听事件
        fun setDialogListener(
            buttonContents: String,
            listerener: OnClickListener
        ): Builder {
            this.buttonContents = buttonContents
            this.confirmLister = listerener
            return this
        }

        /**
         * 创建noticeDialog，布局文件初始化获取控件，这里做了控件点击事件设置，dialog 属性设置
         */
        fun create(): CommentNoticeDialog {

            val inflater = context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val view = inflater.inflate(R.layout.notice_dialog_layout, null)
            view.findViewById<Button>(R.id.confirm).setOnClickListener(confirmLister)

            val noticeDialog = CommentNoticeDialog(context)
            noticeDialog.setContentView(view)
            noticeDialog.setCanceledOnTouchOutside(false)
            view.findViewById<Button>(R.id.confirm).setOnClickListener(confirmLister)
            view.findViewById<TextView>(R.id.noticeTitle).setText(titles)
            view.findViewById<TextView>(R.id.notice_content).setText(contents)
            view.findViewById<Button>(R.id.confirm).setText(buttonContents)

            return noticeDialog
        }
    }
}