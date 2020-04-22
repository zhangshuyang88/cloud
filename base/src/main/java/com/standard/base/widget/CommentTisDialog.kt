package com.standard.base.widget

import android.app.Dialog
import android.content.Context

/**
 *  Create by zsy on 2020/4/20
 *  通用提示dialog
 *  封装要点：
 *  1、提示语，提示标题，确定 取消按钮 语言灵活可变
 *  2、按钮点击事件回调监听
 *  3、提示语言Textview 可以切换成功edittext 控件
 */
class CommentTisDialog : Dialog {

    constructor(context: Context) : this(context, 0)
    constructor(context: Context, themeId: Int) : super(context, themeId)

}