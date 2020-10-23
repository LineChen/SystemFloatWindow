package com.line.flowwindow

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast

/**
 * Created by chenliu on 2020/10/22.
 */
class LiveFloatView : BaseFloatDragView {

    private val TAG = "LiveFloatView"

    private var viewWidth = 0

    private var viewHeight = 0


    constructor (context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int) : super(
        context,
        attributeSet,
        defStyleAttr
    )

    init {
        LayoutInflater.from(context).inflate(R.layout.lauout_float_window_small, this)
        val view = findViewById<View>(R.id.small_window_layout)
        viewWidth = view.layoutParams.width
        viewHeight = view.layoutParams.height
        Log.d(
            TAG,
            "LiveFloatView size:$viewWidth,$viewHeight"
        )

        view.findViewById<View>(R.id.iv_close)
            .setOnClickListener {
                removeFloatView(this)
            }
    }

    override fun getViewWidth(): Int {
        return viewWidth
    }

    override fun getViewHeight(): Int {
        return viewHeight
    }

    override fun onSingleTap() {
        Log.d(TAG, "onSingleTap")
    }

}