package com.example.converge.note.androidheight.plugin;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.converge.R;

public class ProxyActivity extends Activity {
    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proxy);
        Log.i("ProxyActivity", "ProxyActivity onCreate: "+getResources());
    }
}
