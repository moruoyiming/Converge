package com.example.plugin;

import android.os.Bundle;
import android.util.Log;


public class DemoActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("ProxyActivity", "DemoActivity onCreate: "+getResources().getString(R.string.name));
    }
}
