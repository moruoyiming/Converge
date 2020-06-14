package com.cocos.base.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.lang.reflect.Field;

public class UIUtils {
    private Context context;
    private static final String DIME_CLASS = "com.android.internal.R$dimen";
    //标准值
    private static final float STANDRD_WIDTH = 1080F;
    private static final float STANDRD_HEIGHT = 1920F;
    //实际值
    private static float displayMetricsWidth;
    private static float displayMetricsHeight;

    private static UIUtils instance;

    public UIUtils(Context context) {
        this.context = context;
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (displayMetricsHeight == 0.0f || displayMetricsWidth == 0.0f) {
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            int systemBarHeight = getSystemBarHeight(context);
            if (displayMetrics.widthPixels > displayMetrics.heightPixels) {
                //横屏
                displayMetricsWidth = (float) displayMetrics.heightPixels;
                displayMetricsHeight = (float) displayMetrics.widthPixels - systemBarHeight;
            } else {
                //竖屏
                displayMetricsWidth = (float) displayMetrics.widthPixels;
                displayMetricsHeight = (float) displayMetrics.heightPixels - systemBarHeight;
            }
        }

    }

    public static UIUtils getInstance(Context context) {
        if (instance == null) {
            instance = new UIUtils(context);
        }
        return instance;
    }

    //用于反射系统的属性
    private int getSystemBarHeight(Context context) {
        return getValue(context, DIME_CLASS, "system_bar_height", 48);
    }

    private int getValue(Context context, String dimeClass, String system_bar_height, int value) {
        try {
            Class<?> clz = Class.forName(dimeClass);
            Object obj = clz.newInstance();
            Field field = clz.getField(system_bar_height);
            int id = Integer.valueOf(field.get(obj).toString());
            return context.getResources().getDimensionPixelSize(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public float getHorValue() {
        return ((float) displayMetricsWidth) / STANDRD_WIDTH;
    }

    public float getverValue() {
        return ((float) displayMetricsHeight) / STANDRD_HEIGHT - getSystemBarHeight(context);
    }
}
