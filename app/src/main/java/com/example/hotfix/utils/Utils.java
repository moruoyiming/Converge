package com.example.hotfix.utils;

import android.util.Log;

public class Utils {
    private static String TAG = Utils.class.getName();

    //提前 cd 到 com\example\hotfix 父目录下，并执行; 例如 D:\project\Hotfix\app\build\intermediates\javac\debug\classes
    //加载patch.jar 需要文件读取权限，需手动开启。
    public static void what() {//dx --dex --output=D:\patch.jar com\example\hotfix\Utils.class
//        Log.i(TAG,"代码修复了！");
        throw new NullPointerException("程序出错了！");
    }
}
