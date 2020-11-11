package com.line.dragview

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.GestureDetector
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import android.view.ViewConfiguration
import android.widget.FrameLayout
import kotlin.math.abs

/**
 * Created by chenliu on 2020/10/23.
 */
abstract class BaseDragView : FrameLayout {

    companion object {
        const val TAG = "BaseDragView"
    }

    constructor (context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int) : super(
        context,
        attributeSet,
        defStyleAttr
    )

    private var gestureDetector: GestureDetector
    private var rawX: Int = 0
    private var rawY: Int = 0

    init {
        val onGestureListener: SimpleOnGestureListener = object : SimpleOnGestureListener() {
            override fun onSingleTapConfirmed(e: MotionEvent): Boolean {
                onSingleTap()
                return true
            }

            override fun onDown(e: MotionEvent): Boolean {
                Log.d(TAG, "onDown: (x,y) = ${e.rawX}, ${e.rawY}")
                rawX = e.rawX.toInt()
                rawY = e.rawY.toInt()
                return true
            }

            override fun onScroll(
                e1: MotionEvent,
                e2: MotionEvent,
                distanceX: Float,
                distanceY: Float
            ): Boolean {
//                Log.d(
//                    TAG,
//                    "onScroll: e1(x,y)= ${e1.x},${e1.y}  e1(rawx, rawy)= ${e1.rawX},${e1.rawY}"
//                )
//                Log.d(
//                    TAG,
//                    "onScroll: e2(x,y)= ${e2.x},${e2.y}  e2(rawx, rawy)= ${e2.rawX},${e2.rawY}"
//                )
                val dX = e2.rawX.toInt() - rawX
                val dY = e2.rawY.toInt() - rawY
                onMove(e1, e2, dX, dY)
                rawX = e2.rawX.toInt()
                rawY = e2.rawY.toInt()
                return true
            }

        }
        gestureDetector = GestureDetector(context, onGestureListener)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return gestureDetector.onTouchEvent(event)
    }


    protected abstract fun onMove(
        e1: MotionEvent,
        e2: MotionEvent,
        distanceX: Int,
        distanceY: Int
    )

    protected abstract fun onSingleTap()

}