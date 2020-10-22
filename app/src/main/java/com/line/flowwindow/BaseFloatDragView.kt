package com.line.flowwindow

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.WindowManager

/**
 * Created by chenliu on 2020/10/22.
 */
open class BaseFloatDragView : BaseDragView {
    /**
     * 用于更新小悬浮窗的位置
     */
    private var windowManager: WindowManager? = null

    /**
     * 小悬浮窗的参数
     */
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

    override fun onMove(distanceX: Float, distanceY: Float) {
        if (mParams != null) {
            mParams!!.x = distanceX.toInt()
            mParams!!.y = distanceY.toInt()
        }
        windowManager?.updateViewLayout(this, mParams)
    }

    override fun onSingleTap() {

    }

}