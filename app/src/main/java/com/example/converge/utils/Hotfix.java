package com.example.converge.utils;

import android.app.Application;
import android.content.Context;
import android.os.Build;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Hotfix {

    private static final String TAG = "Hotfix";

    private static File initHack(Context context) {
        File hackFile = new File(context.getExternalFilesDir(""), "hack.dex");
        FileOutputStream fos = null;
        InputStream is = null;
        try {
            fos = new FileOutputStream(hackFile);
            is = context.getAssets().open("hack.dex");
            int len;
            byte[] buffer = new byte[2048];
            while ((len = is.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return hackFile;
    }

    /**
     * 1、获取程序的PathClassLoader对象
     * 2、反射获得PathClassLoader父类BaseDexClassLoader的pathList对象
     * 3、反射获取pathList的dexElements对象 （oldElement）
     * 4、把补丁包变成Element数组：patchElement（反射执行makePathElements）
     * 5、合并patchElement+oldElement = newElement （Array.newInstance）
     * 6、反射把oldElement赋值成newElement
     *
     * @param application
     * @param patch
     */
    public static void installPatch(Application application, File patch) {
        File hackDex = initHack(application);
        List<File> patchs = new ArrayList<>();
        patchs.add(hackDex);
        if (patch.exists()) {
            patchs.add(patch);
        }

        //1、获取程序的PathClassLoader对象
        ClassLoader classLoader = application.getClassLoader();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            try {
                ClassLoaderInjector.inject(application, classLoader, patchs);
            } catch (Throwable throwable) {
            }
            return;
        }
        //2、反射获得PathClassLoader父类BaseDexClassLoader的pathList对象
        try {
            Field pathListField = ShareReflectUtils.findField(classLoader, "pathList");
            Object pathList = pathListField.get(classLoader);
            //3、反射获取pathList的dexElements对象 （oldElement）
            Field dexElementsField = ShareReflectUtils.findField(pathList, "dexElements");
            Object[] oldElements = (Object[]) dexElementsField.get(pathList);
            //4、把补丁包变成Element数组：patchElement（反射执行makePathElements）
            Object[] patchElements = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                Method makePathElements = ShareReflectUtils.findMethod(pathList, "makePathElements",
                        List.class, File.class,
                        List.class);
                ArrayList<IOException> ioExceptions = new ArrayList<>();
                patchElements = (Object[])
                        makePathElements.invoke(pathList, patchs, application.getCacheDir(), ioExceptions);

            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                Method makePathElements = ShareReflectUtils.findMethod(pathList, "makeDexElements",
                        ArrayList.class, File.class, ArrayList.class);
                ArrayList<IOException> ioExceptions = new ArrayList<>();
                patchElements = (Object[])
                        makePathElements.invoke(pathList, patchs, application.getCacheDir(), ioExceptions);
            }


            //5、合并patchElement+oldElement = newElement （Array.newInstance）
            //创建一个新数组，大小 oldElements+patchElements
//                int[].class.getComponentType() ==int.class
            Object[] newElements = (Object[]) Array.newInstance(oldElements.getClass().getComponentType(),
                    oldElements.length + patchElements.length);

            System.arraycopy(patchElements, 0, newElements, 0, patchElements.length);
            System.arraycopy(oldElements, 0, newElements, patchElements.length, oldElements.length);
            //6、反射把oldElement赋值成newElement
            dexElementsField.set(pathList, newElements);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
        File output = application.getFilesDir();
        try {
//            1. 获取当前应用pathclassloader
            ClassLoader classLoader = application.getClassLoader();
//            2. 反射获取DexPathlist属性对象的pathList
            Field pathListField = ShareReflectUtils.findField(classLoader, "pathList");
            Object pathList = pathListField.get(classLoader);
//            3. 反射修改pathlist的dexElements
//            3.1.把补丁包中的patch.jar转化为Element[](patch)
            Method makePathElementsMethod = ShareReflectUtils.findMethod(pathList, "makePathElements", List.class, File.class, List.class);
            ArrayList<IOException> suppressedExceptions = new ArrayList<>();
            Object[] patchElements = (Object[]) makePathElementsMethod.invoke(null, files, output, suppressedExceptions);
//            3.2.获得pathlist的dexElements属性(old)
            Field dexElementsField = ShareReflectUtils.findField(pathList, "dexElements");
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

