package com.example.converge.note.androidbasics.dispatch.inner;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.viewpager.widget.ViewPager;

public class InnerInterceptViewPager extends ViewPager {

    public static final String TAG = "dispatch";

    private int startX;

    private int startY;

    public InnerInterceptViewPager(@NonNull Context context) {
        super(context);
    }

    public InnerInterceptViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.i(TAG, "CustomViewPager onInterceptTouchEvent" + ev.getAction());

        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Log.i(TAG, "CustomViewPager onTouchEvent" + ev.getAction());
        return super.onTouchEvent(ev);
    }

    /**
     * 直接重写ViewPager的dispatchTouchEvent，在Down事件的时候，请求SwipeRefreshLayout不要拦截，
     * 只有在ACTION_MOVE事件的时候，并且判断是垂直滑动的话，才请求SwipeRefreshLayout拦截。当然，
     * 还要记得重写父view也就是SwipeRefreshLayout的onInterceptTouchEvent，并且在Down的时候返回false，
     * 因为在Down的时候，是一定会去走onInterceptTouchEvent(ev);方法的。
     *
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
//        Log.i(TAG, "CustomViewPager dispatchTouchEvent" + ev.getAction());
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.i(TAG, "CustomViewPager dispatchTouchEvent MotionEvent.ACTION_DOWN");
                startX = (int) ev.getX();
                startY = (int) ev.getY();
                ViewCompat.setNestedScrollingEnabled(this,true);
                getParent().requestDisallowInterceptTouchEvent(true);//通知父类，禁止拦截
                break;
            case MotionEvent.ACTION_MOVE:
                int x = (int) ev.getX();
                int y = (int) ev.getY();
                int deltaX = x - startX;
                int deltaY = y - startY;
                Log.i(TAG, "CustomViewPager dispatchTouchEvent MotionEvent.ACTION_MOVE"+(Math.abs(deltaX)< Math.abs(deltaY)));
                if (Math.abs(deltaX)< Math.abs(deltaY)) {
                    getParent().requestDisallowInterceptTouchEvent(false);//通知父类，拦截
                }
                break;
            case MotionEvent.ACTION_UP:
                Log.i(TAG, "CustomViewPager dispatchTouchEvent MotionEvent.ACTION_UP");
                break;
        }
        return super.dispatchTouchEvent(ev);
    }
}
