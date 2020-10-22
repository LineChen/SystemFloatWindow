package com.line.flowwindow

import android.graphics.PixelFormat
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.log


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnStartFlowWindow.setOnClickListener {
            val windowManager = windowManager

            val wmParams = WindowManager.LayoutParams()
            wmParams.packageName = packageName
            wmParams.flags = (WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                    or WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                    or WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                    or WindowManager.LayoutParams.FLAG_SCALED
                    or WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR
                    or WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                wmParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
            } else {
                wmParams.type = WindowManager.LayoutParams.TYPE_PHONE
            }

            wmParams.format = PixelFormat.RGBA_8888
            wmParams.gravity = Gravity.START or Gravity.TOP
            val flowWindowView = getFlowWindowView()
            wmParams.width = (200 * resources.displayMetrics.density).toInt()
            wmParams.height = (300 * resources.displayMetrics.density).toInt()
            wmParams.x = 300
            wmParams.y = 300
            flowWindowView.setParams(wmParams)
            windowManager.addView(flowWindowView, wmParams)
        }
    }

    private fun getFlowWindowView(): LiveFloatView {
        val view = LiveFloatView(this)
//            LayoutInflater.from(this).inflate(R.layout.layout_flow_window_view, null, false)
        Log.d("FlowWindowSmallView", "getFlowWindowView ")
        return view
    }
}