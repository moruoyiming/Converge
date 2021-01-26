package com.example.hotfix.note.viewpager;

import android.app.Application;
import android.util.Log;

import com.bumptech.glide.Glide;

public class MyApplication extends Application {
    private static final String TAG = "MyApplication";
    @Override
    public void onCreate() {
        super.onCreate();
        new Thread(new Runnable() {
            @Override
            public void run() {
                long time = System.currentTimeMillis();
                Log.d(TAG, "run1: " + System.currentTimeMillis());
                Glide.get(getApplicationContext());
                Log.d(TAG, "run2: " + (System.currentTimeMillis() - time));
                Log.d(TAG, "run1: " + System.currentTimeMillis());
                time = System.currentTimeMillis();
                Log.d(TAG, "run1: " + System.currentTimeMillis());
                Glide.get(getApplicationContext());
                Log.d(TAG, "run2: " + (System.currentTimeMillis() - time));
            }
        }).start();
    }
}
