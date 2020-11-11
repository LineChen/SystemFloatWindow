package com.line.systemfloat

import android.view.MotionEvent
import android.view.View
import kotlin.math.abs

/**
 * Created by wanqijun on 2020/10/23.
 */
class TouchDelegate(private var mEventListener: OnTouchEventListener?) {
    private var mLastX = 0
    private var mLastY = 0
    private var mStartX = 0
    private var mStartY = 0
    private var mState = TouchState.STATE_STOP
    fun setEventListener(eventListener: OnTouchEventListener?) {
        mEventListener = eventListener
    }

    private enum class TouchState {
        STATE_MOVE, STATE_STOP
    }

    fun onTouchEvent(v: View, event: MotionEvent): Boolean {
        val distance = (v.resources.displayMetrics.density * MIN_DISTANCE_MOVE).toInt()
        val x = event.rawX.toInt()
        val y = event.rawY.toInt()
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                mStartX = x
                mStartY = y
                mLastY = y
                mLastX = x
                mEventListener?.onDown(x, y)
            }
            MotionEvent.ACTION_MOVE -> {
                val result = if (abs(x - mStartX) < distance && abs(y - mStartY) < distance) {
                    mState != TouchState.STATE_STOP
                } else if (mState != TouchState.STATE_MOVE) {
                    mState = TouchState.STATE_MOVE
                    true
                } else {
                    true
                }
                if (result) {
                    mEventListener?.onMove(mLastX, mLastY, x - mLastX, y - mLastY)
                    mLastY = y
                    mLastX = x
                    mState = TouchState.STATE_MOVE
                }
            }
            MotionEvent.ACTION_UP -> {
                mEventListener?.onUp(x, y)
                if (mState != TouchState.STATE_MOVE && event.eventTime - event.downTime < MIN_TAP_TIME) {
                    v.performClick()
                }
                mState = TouchState.STATE_STOP
            }
            else -> {
            }
        }
        return true
    }

    interface OnTouchEventListener {
        fun onMove(x: Int, y: Int, dx: Int, dy: Int)
        fun onUp(x: Int, y: Int)
        fun onDown(x: Int, y: Int)
    }

    companion object {
        private const val MIN_DISTANCE_MOVE = 4
        private const val MIN_TAP_TIME = 1000
    }
}