package com.example.hotfix;

import android.util.Log;

public class Utils {
    private static String TAG = Utils.class.getName();

    public static void what() {
//        Log.i(TAG,"代码修复了！");
        throw new NullPointerException("程序出错了！");
    }
}
