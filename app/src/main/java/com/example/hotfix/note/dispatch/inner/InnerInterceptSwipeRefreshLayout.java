package com.example.hotfix.note.dispatch.inner;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class InnerInterceptSwipeRefreshLayout extends SwipeRefreshLayout {

    public static final String TAG = "dispatch";

    public InnerInterceptSwipeRefreshLayout(@NonNull Context context) {
        super(context);
    }

    public InnerInterceptSwipeRefreshLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 因为在源码里是down事件的时候会执行resetTouchState();重置mGroupFlags标志，导致一定会执行
     * intercepted = onInterceptTouchEvent(ev);这条语句，所以，在内部拦截法的时候，记得在外部父view里
     * 重写onInterceptTouchEvent，并且在Down的时候返回false。
     *
     * @param ev
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.i(TAG, "CustomSwipeRefreshLayout onInterceptTouchEvent" + ev.getAction());
        if(ev.getAction()==MotionEvent.ACTION_DOWN){
            super.onInterceptTouchEvent(ev);
            return false;
        }
        return true;
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

    /**
     * SwipeRefreshLayout里确实重写了requestDisallowInterceptTouchEvent方法，并且加了判断，
     * requestDisallowInterceptTouchEvent方法失效，也就是没有调用到super.requestDisallowInterceptTouchEvent(b);
     * SwipeRefreshLayout继承自ViewGroup，也就是没有调用到ViewGroup的requestDisallowInterceptTouchEvent，
     * 所以应该是前面的if判断没通过，我们运行的虚拟机是大于21的，这个VERSION.SDK_INT >= 21是满足的，
     * 因为SwipeRefreshLayout里包含了一个ViewPager ，所以SwipeRefreshLayout里有子view，也就是this.mTarget是不等于null的
     * @param b
     */
    @Override
    public void requestDisallowInterceptTouchEvent(boolean b) {
        Log.i(TAG, "CustomSwipeRefreshLayout requestDisallowInterceptTouchEvent" + b);
        super.requestDisallowInterceptTouchEvent(b);
    }
}
