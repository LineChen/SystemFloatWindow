package com.line.systemfloat

import android.content.Context
import android.graphics.PixelFormat
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import com.line.dragview.BaseDragView

/**
 * Created by chenliu on 2020/10/22.
 */
@Deprecated("拖动会抖动")
abstract class BaseFloatDragView2 : BaseDragView {

    private var mWindowManager: WindowManager? = null

    private lateinit var mWindowParams: WindowManager.LayoutParams

    constructor (context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int) : super(
        context,
        attributeSet,
        defStyleAttr
    )

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

    override fun onMove(e1: MotionEvent, e2: MotionEvent, distanceX: Int, distanceY: Int) {
        Log.d(TAG, "onMove: (distanceX,distanceY)=${distanceX}, $distanceY")
        mWindowParams.x = (mWindowParams.x + distanceX)
        mWindowParams.y = (mWindowParams.y + distanceY)
//        Log.d(TAG, "onMove:(x,y)=${mWindowParams.x}, ${mWindowParams.y}")
        mWindowManager?.updateViewLayout(this, mWindowParams)
    }

    override fun onSingleTap() {

    }
}