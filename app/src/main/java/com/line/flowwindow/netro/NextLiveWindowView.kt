package com.line.flowwindow.netro

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import com.line.flowwindow.R
import com.line.systemfloat.AbsFloatingView

/**
 * Created by chenliu on 2020/10/23.
 */
class NextLiveWindowView : AbsFloatingView() {

    override fun onCreateView(context: Context, parent: FrameLayout): View {
        val root = LayoutInflater.from(context)
            .inflate(R.layout.lauout_float_window_small, parent, false)
        root.findViewById<View>(R.id.iv_close).setOnClickListener {
            close()
        }
        return root
    }

}