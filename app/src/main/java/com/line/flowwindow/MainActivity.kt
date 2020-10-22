package com.line.flowwindow

import android.graphics.PixelFormat
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


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

//            if (float_window_type === FLOAT_WINDOW_TYPE_DIALOG) {
            //wmParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_ATTACHED_DIALOG;
            wmParams.type = WindowManager.LayoutParams.TYPE_TOAST
//            } else if (float_window_type === FLOAT_WINDOW_TYPE_ALERT_WINDOW) {
//                //需要权限
//                wmParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT
//            }

            wmParams.format = PixelFormat.RGBA_8888
            wmParams.gravity = Gravity.START or Gravity.TOP
            val flowWindowView = getFlowWindowView()
            wmParams.width = 100
            wmParams.height = 100
            wmParams.x = 100
            wmParams.y = 100
            windowManager.addView(flowWindowView, wmParams)
        }
    }

    private fun getFlowWindowView(): View {
        return LayoutInflater.from(this).inflate(R.layout.layout_flow_window_view, null, false)
    }
}