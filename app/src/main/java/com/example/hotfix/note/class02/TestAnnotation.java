package com.example.hotfix.note.class02;

import androidx.annotation.DrawableRes;
import androidx.annotation.IntDef;

import com.example.hotfix.R;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解
 */
public class TestAnnotation {

    private static WeekDay weekDay;

    private static final int MONDAY = 1;

    private static final int TUESDAY = 2;

    private static final int WEDNESDAY = 3;

    private static final int THURSDAY = 4;

    private static final int FRIDAY = 5;

    private static final int SATURDAY = 6;

    private static final int SUNDAY = 7;

    @WeekDays
    private static final int setCurrentDay = 0;

    //占用内存，每个enum 都是一个对象。
//    一个对象占用大小：12字节信息头 +  类成员  + 8字节对齐
    enum WeekDay {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }

    public static void setDrawable(@DrawableRes int resId) {

    }

    public static void setCurrentDay(WeekDay day) {

    }

    public static void main(String[] args) {
//        语法检查
//        setDrawable(112231);
        setDrawable(R.drawable.ic_launcher_background);
        setCurrentDay(WeekDay.FRIDAY);
        setCurrentDay(MONDAY);
    }


    public static void setCurrentDay(@WeekDays int day) {

    }

    @Target({ElementType.FIELD, ElementType.PARAMETER})
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY})
    @interface WeekDays {

    }

}
