package com.example.hotfix.note.class16.outer;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

/**
 * 外部拦截法，在外部父view里拦截，直接重写SwipeRefreshLayout的onInterceptTouchEvent方法，
 * 在ACTION_MOVE的时候，判断如果是水平滑动的话，不拦截事件，把事件交由子View也就是ViewPager处理
 */
public class OuterInterceptSwipeRefreshLayout extends SwipeRefreshLayout {

    public static final String TAG = "dispatch";

    private int lastX = 0;

    private int lastY = 0;

    public OuterInterceptSwipeRefreshLayout(@NonNull Context context) {
        super(context);
    }

    public OuterInterceptSwipeRefreshLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.i(TAG, "CustomSwipeRefreshLayout onInterceptTouchEvent" + ev.getAction());
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.i(TAG, "CustomSwipeRefreshLayout onInterceptTouchEvent MotionEvent.ACTION_DOWN");
                lastX = (int) ev.getX();
                lastY = (int) ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i(TAG, "CustomSwipeRefreshLayout onInterceptTouchEvent MotionEvent.ACTION_MOVE");
                int deltaX = x - lastX;
                int deltaY = y - lastY;
                Log.i(TAG, "CustomSwipeRefreshLayout onInterceptTouchEvent MotionEvent.ACTION_MOVE" + deltaX + "    " + deltaY);
                if (Math.abs(deltaX) > Math.abs(deltaY)) {
                    return false;
                }
                break;
            case MotionEvent.ACTION_UP:
                Log.i(TAG, "CustomSwipeRefreshLayout onInterceptTouchEvent MotionEvent.ACTION_UP");
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Log.i(TAG, "CustomSwipeRefreshLayout onTouchEvent" + ev.getAction());
        return super.onTouchEvent(ev);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i(TAG, "CustomSwipeRefreshLayout dispatchTouchEvent" + ev.getAction());
        return super.dispatchTouchEvent(ev);
    }
}
