package com.example.hotfix.note.class01;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

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
public class BActivity extends AppCompatActivity {
    private static final String TAG = "LifecycleCallback";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "B Activity onCreate");
        setContentView(R.layout.activity_a);
        findViewById(R.id.title).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(BActivity.this, AActivity.class);
                startActivity(intent2);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "B Activity onStart");
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "B Activity onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "B Activity onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "B Activity onPause");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i(TAG, "B Activity onNewIntent");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "B Activity onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "B Activity onDestroy");
    }
}
