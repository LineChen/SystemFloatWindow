package com.line.systemfloat

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.PixelFormat
import android.os.Build
import android.util.Log
import android.view.Gravity
import android.view.KeyEvent
import android.view.View
import android.view.WindowManager
import android.widget.FrameLayout

/**
 * Created by wanqijun on 2020/10/23.
 */
abstract class AbsFloatingView : IFloatingView, TouchDelegate.OnTouchEventListener {
    companion object {
        private const val TAG = "AbsFloatingView"
    }

    private var rootView: FrameLayout? = null
    private var childView: View? = null
    private val touchDelegate by lazy { TouchDelegate(this) }
    private lateinit var windowManager: WindowManager
    private var windowLayoutParams: WindowManager.LayoutParams? = null

    override fun onMove(x: Int, y: Int, dx: Int, dy: Int) {
        Log.d(TAG, "TouchDelegate onMove: (dx,dy)=$dx,$dy")
        if (!canDrag()) {
            return
        }
        if (false/*isNormalMode()*/) {
//            mFrameLayoutParams.leftMargin += dx
//            mFrameLayoutParams.topMargin += dy
            //更新图标位置
//            updateViewLayout(mTag, false)
        } else {
            windowLayoutParams?.let {
                it.x += dx
                it.y += dy
                windowManager.updateViewLayout(rootView, windowLayoutParams)
            }
        }
    }

    override fun onUp(x: Int, y: Int) {
        if (!canDrag()) {
            return
        }

    }

    override fun onDown(x: Int, y: Int) {
        if (!canDrag()) {
            return
        }
    }

    fun getRootView() = rootView
    fun getWindowLayoutParams() = windowLayoutParams

    override fun onCreate(context: Context) {
        windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun performCreate(context: Context) = try {
        onCreate(context)
        rootView = InnerFrameLayout(context, this)
        childView = onCreateView(context, rootView!!)
        rootView!!.addView(childView)
        //设置根布局的手势拦截
        rootView!!.setOnTouchListener { v, event ->
            if (getRootView() != null) {
                touchDelegate.onTouchEvent(v, event)
            } else {
                false
            }
        }
        onViewCreated(rootView!!)
        windowLayoutParams = WindowManager.LayoutParams()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //android 8.0
            windowLayoutParams!!.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
        } else {
            windowLayoutParams!!.type = WindowManager.LayoutParams.TYPE_PHONE
        }
        if (!shouldDealBackKey()) {
            windowLayoutParams!!.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
//            mDokitViewLayoutParams.flags = DokitViewLayoutParams.FLAG_NOT_FOCUSABLE
        }
        windowLayoutParams!!.format = PixelFormat.TRANSPARENT
        windowLayoutParams!!.gravity = Gravity.LEFT or Gravity.TOP
//        mDokitViewLayoutParams.gravity = Gravity.LEFT or Gravity.TOP

        onSystemLayoutParamsCreated()
//
    } catch (throwable: Throwable) {
        Log.e(TAG, "performCreate error", throwable)
    }

    private fun onSystemLayoutParamsCreated() {
        windowLayoutParams?.let {
//        params.flags = mDokitViewLayoutParams.flags
            it.gravity = Gravity.LEFT or Gravity.TOP//mDokitViewLayoutParams.gravity
            it.width = WindowManager.LayoutParams.WRAP_CONTENT//mDokitViewLayoutParams.width
            it.height = WindowManager.LayoutParams.WRAP_CONTENT//mDokitViewLayoutParams.height
//            val point: Point = DokitViewManager.getInstance().getDokitViewPos(mTag)
//            if (point != null) {
            it.x = 100//point.x
            it.y = 100//point.y
//            } else {
//                it.x = mDokitViewLayoutParams.x
//                it.y = mDokitViewLayoutParams.y
//            }
            onFirstPosition(it)
        }
    }

    private class InnerFrameLayout(context: Context, val floatingView: IFloatingView) :
        FrameLayout(context) {
        override fun dispatchKeyEvent(event: KeyEvent?): Boolean {
            if (event == null) return super.dispatchKeyEvent(event)
            if (event.action == KeyEvent.ACTION_UP && floatingView.shouldDealBackKey()) {
                //监听返回键
                if (event.keyCode == KeyEvent.KEYCODE_BACK || event.keyCode == KeyEvent.KEYCODE_HOME) {
                    return floatingView.onBackPressed()
                }
            }
            return super.dispatchKeyEvent(event)
        }
    }

    override fun show(context: Context) {
        performCreate(context)
        windowManager.addView(getRootView(), getWindowLayoutParams())
    }


    fun close() {
        windowManager.removeView(rootView)
    }

}