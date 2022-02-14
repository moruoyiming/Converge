package com.example.converge.note.androidadvanced.plugin;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.converge.R;
import com.example.converge.utils.HookUtils;
import com.example.converge.utils.LoadUtils;

import java.lang.reflect.Method;

public class PluginTestActivity extends AppCompatActivity {

    private static final String TAG = "Plugin";

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plugin);
        findViewById(R.id.dianwo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadUtils.loadClass(PluginTestActivity.this);
                HookUtils.hookAMS();
                HookUtils.hookHandler();
                printCLassLoader();
                invokeMethod();

            }
        });
        findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(PluginActivity.this, ProxyActivity.class));
                Intent intent = new Intent();
                intent.setComponent(new ComponentName("com.example.plugin","com.example.plugin.PluginActivity"));
                startActivity(intent);
            }
        });

    }

    public void invokeMethod() {
        try {
            Class<?> clazz = Class.forName("com.example.plugin.Test");
            Method method = clazz.getMethod("print");
            method.invoke(null);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void printCLassLoader() {
        ClassLoader classLoader = getClassLoader();
        while (classLoader != null) {
            Log.i(TAG, "printCLassLoader: " + classLoader);
            classLoader = classLoader.getParent();
        }
        // PathClassLoader 和 BootClassLoader 分别加载什么类？
        Log.i(TAG, "Activity的classLoader: " + Activity.class.getClassLoader());

        Log.i(TAG, "AppCompatActivity的classLoader: " + AppCompatActivity.class.getClassLoader());
    }
}
