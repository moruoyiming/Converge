package com.example.hotfix.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

public class LoadUtils {

    private final static String apkPath = "/sdcard/plugin-debug.apk";

    public static void loadClass(Context context) {
        /**
         * 宿主dexElements = 宿主dexElements + 插件dexElements
         *
         * 1.获取宿主dexElements
         * 2.获取插件dexElements
         * 3.合并两个dexElements
         * 4.将新的dexElements 赋值到 宿主dexElements
         *
         * 目标：dexElements  -- DexPathList类的对象 -- BaseDexClassLoader的对象，类加载器
         *
         * 获取的是宿主的类加载器  --- 反射 dexElements  宿主
         *
         * 获取的是插件的类加载器  --- 反射 dexElements  插件
         */
        try {
            Class<?> clazz = Class.forName("dalvik.system.BaseDexClassLoader");
            Field pathListField = clazz.getDeclaredField("pathList");
            pathListField.setAccessible(true);
            Class<?> dexPathListClass = Class.forName("dalvik.system.DexPathList");
            Field dexElementsField = dexPathListClass.getDeclaredField("dexElements");
            dexElementsField.setAccessible(true);
            //宿主的类加载器
            ClassLoader pathClassLoader = context.getClassLoader();
            //宿主的DexPathList类的对象
            Object hostPathList = pathListField.get(pathClassLoader);
            //宿主的dexElements
            Object[] hostDexElements = (Object[]) dexElementsField.get(hostPathList);

            //插件的类加载器
            ClassLoader dexClassLoader = new DexClassLoader(apkPath, context.getCacheDir().getAbsolutePath(),
                    null, pathClassLoader);
            //插件的DexPathList对象
            Object pluginPathList = pathListField.get(dexClassLoader);
            //插件的dexElements
            Object[] pluginDexElements = (Object[]) dexElementsField.get(pluginPathList);

            // 宿主dexElements = 宿主dexElements + 插件dexElements
            //Object[] obj = new Object[]; // 不行
            //创建新数组
            Object[] newDexElements = (Object[]) Array.newInstance(hostDexElements.getClass().getComponentType(),
                    hostDexElements.length + pluginDexElements.length);
            System.arraycopy(hostDexElements, 0, newDexElements, 0, hostDexElements.length);
            System.arraycopy(pluginDexElements, 0, newDexElements, hostDexElements.length, pluginDexElements.length);
            // 赋值
            // hostDexElements = newDexElements
            dexElementsField.set(hostPathList, newDexElements);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * performLaunchActivity->createBaseContextForActivity->ContextImpl.createActivityContext
     * -> context.setResources->createResources->createResourcesImpl->createAssetManager
     *
     * 1.宿主资源+插件资源方案容易引起资源冲突
     * 2.反射替换Resources方案
     * @param context
     * @return
     */
    public static Resources loadResource(Context context) {
        try {
            //asset.addAssetPath(key.mResDir)
            Class<?> assetManagerClass = AssetManager.class;
            AssetManager assetManager = (AssetManager) assetManagerClass.newInstance();
            //让这个AssetManager对象加载的资源为插件资源
            Method addAssetPathMethod = assetManagerClass.getDeclaredMethod("addAssetPath", String.class);
            addAssetPathMethod.setAccessible(true);
            addAssetPathMethod.invoke(assetManager, apkPath);
            Resources resources = context.getResources();
            //加载插件的资源的resources
            return new Resources(assetManager, resources.getDisplayMetrics(), resources.getConfiguration());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
