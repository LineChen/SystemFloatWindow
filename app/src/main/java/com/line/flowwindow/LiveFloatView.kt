package com.line.flowwindow

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import com.line.systemfloat.BaseFloatDragView

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
        val inflate =
            LayoutInflater.from(context).inflate(R.layout.lauout_float_window_small, this, false)
        addView(inflate)
        val view = findViewById<View>(R.id.small_window_layout)

        view.findViewById<View>(R.id.iv_close)
            .setOnClickListener {
                removeFloatView(this)
            }
    }

    override fun onSingleTap() {
        Log.d(TAG, "onSingleTap")
    }

}