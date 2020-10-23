package com.line.flowwindow;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Created by chenliu on 2020/10/22.
 */
public abstract class BaseDragView extends FrameLayout {

    private static final String TAG = "BaseDragView";

    private GestureDetector gestureDetector;

    public BaseDragView(@NonNull Context context) {
        this(context, null, 0);
    }

    public BaseDragView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseDragView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        GestureDetector.SimpleOnGestureListener onGestureListener = new GestureDetector.SimpleOnGestureListener() {

            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                onSingleTap();
                return true;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                onMove(e1, e2, distanceX, distanceY);
                return true;
            }

            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }
        };
        gestureDetector = new GestureDetector(context, onGestureListener);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    protected abstract void onSingleTap();

    protected abstract void onMove(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY);
}
