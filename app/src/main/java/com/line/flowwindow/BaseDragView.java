package com.line.flowwindow;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
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
                Log.d(TAG, "onSingleTapConfirmed: ");
                onSingleTap();
                return true;
            }

            /**
             *
             * @param e1
             * @param e2
             * @param distanceX
             * @param distanceY
             * @return
             */
            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                onMove(e2.getRawX() - e1.getX(), e2.getRawY() - e1.getY());
                return true;
            }

            @Override
            public boolean onDown(MotionEvent e) {
                Log.d(TAG, "onDown: (" + e.getX() + "," + e.getY() + ")");
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

    protected abstract void onMove(float distanceX, float distanceY);
}
