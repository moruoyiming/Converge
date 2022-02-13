package com.example.converge.note.androidbasics;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.converge.R;

/**
 * @Date: 2022/2/10
 * @Time: 4:02 下午
 * @Author: Jian
 */
public class BActivity extends Activity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bb);
        findViewById(R.id.jump).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BActivity.this,AActivity.class);
                startActivity(intent);
            }
        });
        Log.i("TESTS","BActivity onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("TESTS","BActivity onStart");
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.i("TESTS","BActivity onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("TESTS","BActivity onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("TESTS","BActivity onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("TESTS","BActivity onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("TESTS","BActivity onDestroy");
    }
}
