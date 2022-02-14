package com.example.converge.note.androidbasics.activitylifecycle;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.converge.R;

/**
 * <pre>
 *     author: jian
 *     Date  : 2020/6/3 5:23 PM
 *     Description:
 * </pre>
 */
public class AActivity extends AppCompatActivity {
    private static final String TAG = "LifecycleCallback";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "A Activity onCreate");
        setContentView(R.layout.activity_a);
//        findViewById(R.id.title).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent2 = new Intent(AActivity.this, BActivity.class);
//                startActivity(intent2);
//            }
//        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        Log.i(TAG, "A Activity onSaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG, "A Activity onRestoreInstanceState");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "A Activity onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "A Activity onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "A Activity onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "A Activity onPause");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i(TAG, "A Activity onNewIntent");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "A Activity onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "A Activity onDestroy");
    }
}
