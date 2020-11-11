package com.line.systemfloat

import android.content.Context
import android.graphics.PixelFormat
import android.os.Build
import android.util.AttributeSet
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.widget.FrameLayout

/**
 * Created by chenliu on 2020/11/11.
 * 使用自定义TouchDelegate解决拖动抖动问题
 */
open class BaseFloatDragView3 : FrameLayout, TouchDelegate.OnTouchEventListener {
    constructor (context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int) : super(
        context,
        attributeSet,
        defStyleAttr
    )

    private var mWindowManager: WindowManager? = null

    private lateinit var mWindowParams: WindowManager.LayoutParams

    private val touchDelegate by lazy { TouchDelegate(this) }


    init {
        mWindowManager =
            context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    }

    protected fun removeFloatView(view: View) {
        mWindowManager?.removeViewImmediate(view)
    }

    fun show(initX: Int, initY: Int) {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager?
        mWindowParams = WindowManager.LayoutParams()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mWindowParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
        } else {
            mWindowParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT
        }
        mWindowParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
        mWindowParams.format = PixelFormat.TRANSPARENT
        mWindowParams.gravity = Gravity.START or Gravity.TOP
        mWindowParams.width = WindowManager.LayoutParams.WRAP_CONTENT
        mWindowParams.height = WindowManager.LayoutParams.WRAP_CONTENT
        mWindowParams.x = initX
        mWindowParams.y = initY
        windowManager?.addView(this, mWindowParams)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return touchDelegate.onTouchEvent(this, event!!)
    }

    override fun onMove(x: Int, y: Int, dx: Int, dy: Int) {
        mWindowParams.x += dx
        mWindowParams.y += dy
        mWindowManager?.updateViewLayout(this, mWindowParams)
    }

    override fun onUp(x: Int, y: Int) {
    }

    override fun onDown(x: Int, y: Int) {
    }

}