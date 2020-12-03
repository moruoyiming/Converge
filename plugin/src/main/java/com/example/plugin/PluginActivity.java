package com.example.plugin;

import android.os.Bundle;
import android.util.Log;


public class PluginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plugin);
        Log.i("ProxyActivity", "DemoActivity onCreate: "+getResources().getString(R.string.name));
    }
}
