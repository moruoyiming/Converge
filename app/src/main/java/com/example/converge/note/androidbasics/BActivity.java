package com.example.converge.note.androidbasics;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.converge.R;
import com.example.converge.note.ndk.Person;
import com.example.converge.note.ndk.Student;

/**
 * @Date: 2022/2/10
 * @Time: 4:02 下午
 * @Author: Jian
 */
public class BActivity extends Activity {

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Student temp = (Student) msg.obj;
            Log.i("handle","msg what "+ msg.what+ "  arg1 "+ msg.arg1 + "  temp "+temp.toString());
        }

        @Override
        public void dispatchMessage(Message msg) {
            Log.i("handle","msg "+msg.getCallback());
            super.dispatchMessage(msg);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bb);
        findViewById(R.id.jump).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(BActivity.this,AActivity.class);
//                startActivity(intent);
                Message message = new Message();
                message.what = 1;
                Student student = new Student();
                student.name = "周易";
                student.age = 123;
                message.obj = student;
                message.arg1 = 2;
                message.arg2 = 3;
                Thread thread = new Thread(){
                    @Override
                    public void run() {
                        handler.sendMessage(message);
                    }
                };
                thread.start();
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        Log.i("handle","thread execute ");
                    }
                };
                Log.i("handle","thread runnable "+runnable);
                handler.postDelayed(runnable,3333);
            }
        });
        

        Log.i("TESTS","BActivity onCreate");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
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
