package com.line.flowwindow

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager

/**
 * Created by chenliu on 2020/10/22.
 */
abstract class BaseFloatDragView : BaseDragView {

    private var windowManager: WindowManager? = null

    private var mParams: WindowManager.LayoutParams? = null

    constructor (context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int) : super(
        context,
        attributeSet,
        defStyleAttr
    )

    init {
        windowManager =
            context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    }

    fun setParams(params: WindowManager.LayoutParams?) {
        mParams = params
    }

    protected fun removeFloatView(view: View) {
        windowManager?.removeViewImmediate(view)
    }

    override fun onMove(e1: MotionEvent, e2: MotionEvent, distanceX: Float, distanceY: Float) {
        if (mParams != null) {
            mParams!!.x = (e2.rawX - e1.x).toInt()
            mParams!!.y = (e2.rawY - e1.y).toInt()
        }
        windowManager?.updateViewLayout(this, mParams)
    }

    override fun onSingleTap() {

    }

    abstract fun getViewWidth(): Int

    abstract fun getViewHeight(): Int

}