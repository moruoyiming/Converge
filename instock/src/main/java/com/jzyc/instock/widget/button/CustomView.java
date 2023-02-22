package com.jzyc.instock.widget.button;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;

/**
 * @Date: 2022/2/7
 * @Time: 10:53 上午
 * @Author: Jian
 */
public class CustomView extends Button {

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //   public static final int ACTION_BUTTON_PRESS = 11;
    //    public static final int ACTION_BUTTON_RELEASE = 12;
    //    public static final int ACTION_CANCEL = 3;
    //    public static final int ACTION_DOWN = 0;
    //    public static final int ACTION_HOVER_ENTER = 9;
    //    public static final int ACTION_HOVER_EXIT = 10;
    //    public static final int ACTION_HOVER_MOVE = 7;
    //    public static final int ACTION_MASK = 255;
    //    public static final int ACTION_MOVE = 2;
    //    public static final int ACTION_OUTSIDE = 4;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case 0:
                Log.i("sdsdsdsds","onTouchEvent  ACTION_DOWN ");
                break;
            case 2:
                Log.i("sdsdsdsds","onTouchEvent  ACTION_MOVE " );
                break;
            case 3:
                Log.i("sdsdsdsds","onTouchEvent  ACTION_CANCEL ");
            case 4:
                Log.i("sdsdsdsds","onTouchEvent  ACTION_OUTSIDE ");
                break;
            case 1:
                Log.i("sdsdsdsds","onTouchEvent  ACTION_UP ");
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case 0:
                Log.i("sdsdsdsds","dispatchTouchEvent  ACTION_DOWN ");
                break;
            case 2:
                Log.i("sdsdsdsds","dispatchTouchEvent  ACTION_MOVE " );
                break;
            case 3:
                Log.i("sdsdsdsds","dispatchTouchEvent  ACTION_CANCEL ");
            case 4:
                Log.i("sdsdsdsds","dispatchTouchEvent  ACTION_OUTSIDE ");
                break;
            case 1:
                Log.i("sdsdsdsds","dispatchTouchEvent  ACTION_UP ");
        }
        return super.dispatchTouchEvent(event);
    }
}
