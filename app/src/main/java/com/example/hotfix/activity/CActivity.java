package com.example.hotfix.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hotfix.R;

/**
 * <pre>
 *     author: jian
 *     Date  : 2020/6/3 5:23 PM
 *     Description:
 * </pre>
 */
public class CActivity extends AppCompatActivity {
    private static final String TAG = "LifecycleCallback";
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_a);
        Log.i(TAG,"C Activity onCreate");
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG,"C Activity onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG,"C Activity onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG,"C Activity onResume");
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG,"C Activity onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG,"C Activity onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"C Activity onDestroy");
    }
}
