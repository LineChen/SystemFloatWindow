package com.line.systemfloat

import android.content.Context
import android.view.View
import android.view.WindowManager
import android.widget.FrameLayout

/**
 * Created by wanqijun on 2020/10/23.
 */
interface IFloatingView {

    fun onCreate(context: Context)

    fun onCreateView(context: Context, parent: FrameLayout): View

    fun onViewCreated(parent: FrameLayout) {}

    /**
     * 搭配shouldDealBackKey使用
     */
    fun onBackPressed() = false

    /**
     * 默认不自己处理返回按键
     */
    fun shouldDealBackKey() = false

    /**
     * 浮标控件是否可以拖动
     */
    fun canDrag() = true

    fun show(context: Context)

    fun onFirstPosition(params: WindowManager.LayoutParams){}

}