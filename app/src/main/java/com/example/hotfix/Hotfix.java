package com.example.hotfix;

import android.app.Application;
import android.renderscript.Element;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

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
        List<File> files = new ArrayList<>();
        files.add(file);
        File output=application.getFilesDir();
        try {
//            1. 获取当前应用pathclassloader
            ClassLoader classLoader = application.getClassLoader();
//            2. 反射获取DexPathlist属性对象的pathList
            Field pathListField = ShareReflectUtils.findFiled(classLoader, "pathList");
            Object pathList = pathListField.get(classLoader);
//            3. 反射修改pathlist的dexElements
//            3.1.把补丁包中的patch.jar转化为Element[](patch)
            Method makePathElementsMethod = ShareReflectUtils.findMethod(pathList, "makePathElements", List.class, File.class, List.class);
            ArrayList<IOException> suppressedExceptions = new ArrayList<>();
            Object[] patchElements = (Object[]) makePathElementsMethod.invoke(null,files,output,suppressedExceptions);
//            3.2.获得pathlist的dexElements属性(old)
            Field dexElementsField = ShareReflectUtils.findFiled(pathList, "dexElements");
            Object[] dexElments = (Object[]) dexElementsField.get(pathList);
            //创建新的 Element 数组
            Object newElments = Array.newInstance(dexElments.getClass().getComponentType(), dexElments.length + patchElements.length);
            System.arraycopy(patchElements, 0, newElments, 0, patchElements.length);
            System.arraycopy(dexElments, 0, newElments, patchElements.length, dexElments.length);
//            3.3 patch + old 合并 反射赋值给pathlist的dexElements
            dexElementsField.set(pathList, newElments);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}

