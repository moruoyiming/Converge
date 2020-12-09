package com.example.plugin;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;


public class PluginActivity extends BaseActivity {
    private Button tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("ProxyActivity", "PluginActivity onCreate: ");//+getResources().getString(R.string.name)
        View view = LayoutInflater.from(mContext).inflate(R.layout.activity_plugin, null);
        setContentView(view);
        tv= findViewById(R.id.title);
        tv.setText(mContext.getResources().getString(R.string.name));
    }
}
