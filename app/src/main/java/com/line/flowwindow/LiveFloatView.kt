package com.line.flowwindow

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View

/**
 * Created by chenliu on 2020/10/22.
 */
class LiveFloatView : BaseFloatDragView {

    private val TAG = "LiveFloatView"

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
        FlowWindowSmallView.viewWidth = view.layoutParams.width
        FlowWindowSmallView.viewHeight = view.layoutParams.height
        Log.d(
            TAG,
            "FlowWindowSmallView: width:" + FlowWindowSmallView.viewWidth + "," + FlowWindowSmallView.viewHeight
        )

        view.findViewById<View>(R.id.iv_close)
            .setOnClickListener {
                removeFloatView(this)
            }

    }

}