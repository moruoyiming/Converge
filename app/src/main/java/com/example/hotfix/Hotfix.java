package com.example.hotfix;

import android.app.Application;

import java.io.File;
import java.lang.reflect.Field;

public class Hotfix {


    public static void fix(Application application, String patch) {

//        1. 获取当前应用pathclassloader
//        2. 反射获取DexPathlist属性对象的pathList
//        3. 反射修改pathlist的dexElements
//          1.把补丁包中的patch.jar转化为Element[](patch)
//          2.获得pathlist的dexElements属性(old)
//          3.patch + old 合并 反射赋值给pathlist的dexElements
        File file = new File(patch);
        if (!file.exists()) {
            return;
        }
        ClassLoader classLoader = application.getClassLoader();
        Field pathList=ShareReflectUtils.findFiled(classLoader,"pathList");


    }
}

