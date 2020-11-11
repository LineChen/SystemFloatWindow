package com.line.flowwindow

import android.content.Context
import android.graphics.PixelFormat
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.*
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.line.flowwindow.netro.NextLiveWindowView
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.log


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        /* btnStartFlowWindow.setOnClickListener {
             val checkPermission = checkPermission()
             Toast.makeText(this, "是否开启权限$checkPermission", Toast.LENGTH_SHORT).show()

             val flowWindowView = getFlowWindowView()

             val windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager

             val windowLayoutParams = WindowManager.LayoutParams()
             if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                 windowLayoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
             } else {
                 windowLayoutParams.type = WindowManager.LayoutParams.TYPE_PHONE
             }
             windowLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
             windowLayoutParams.format = PixelFormat.TRANSPARENT
             windowLayoutParams.gravity = Gravity.START or Gravity.TOP
             windowLayoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT
             windowLayoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT
             windowLayoutParams.x = 100//point.x
             windowLayoutParams.y = 100//point.y
             flowWindowView.setParams(windowLayoutParams)
             windowManager.addView(flowWindowView, windowLayoutParams)
         }*/

//        btnStartFlowWindow.setOnClickListener {
//            NextLiveWindowView().show(this)
//        }

        btnStartFlowWindow.setOnClickListener {
            LiveFloatView2(this).show(300, 300)

            NextLiveWindowView().show(this)
        }

    }

    private fun getFlowWindowView(): LiveFloatView {
//        val view = LiveFloatView(this)
//        val container = FrameLayout(this)
//        val childView =
//            LayoutInflater.from(this).inflate(R.layout.lauout_float_window_small, container, false)
//        container.addView(childView)
//        Log.d("FlowWindowSmallView", "getFlowWindowView ")
        return LiveFloatView(this)
    }

    private fun checkPermission(): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return Settings.canDrawOverlays(this)
        } else {

        }
        return false
    }
}