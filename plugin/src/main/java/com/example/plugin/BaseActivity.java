package com.example.plugin;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.ContextThemeWrapper;

import androidx.annotation.Nullable;

import java.lang.reflect.Field;


public abstract class BaseActivity extends Activity {


    protected Context mContext;
//    1.会影响宿主，使用宿主的PathClassLoader
//    2.宿主和插件的Application是一个，
//    @Override
//    public Resources getResources() {
//        if (getApplication() != null && getApplication().getResources() != null) {
//            return getApplication().getResources();
//        }
//        return super.getResources();

//        Resources resources = LoadUtil.getResources(getApplication());
//        // 如果插件作为一个单独的app，返回 super.getResources()
//        return resources == null ? super.getResources() : resources;
//    }

    // 不会影响到宿主
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Resources resources = LoadUtils.getResources(getApplication());

        mContext = new ContextThemeWrapper(getBaseContext(), 0);

        Class<? extends Context> clazz = mContext.getClass();
        try {
            Field mResourcesField = clazz.getDeclaredField("mResources");
            mResourcesField.setAccessible(true);
            mResourcesField.set(mContext, resources);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
