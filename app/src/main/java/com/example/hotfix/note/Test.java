package com.example.hotfix.note;

import androidx.annotation.DrawableRes;

import com.example.hotfix.R;

/**
 * 注解
 */
public class Test {
    private static WeekDay weekDay;

    enum WeekDay {
        MONDAY, TUSDAY
    }

    public static void setDrawable(@DrawableRes int resId){

    }

    public static void main(String[] args) {
//        语法检查
//        setDrawable(112231);

        setDrawable(R.drawable.ic_launcher_background);
    }
}
