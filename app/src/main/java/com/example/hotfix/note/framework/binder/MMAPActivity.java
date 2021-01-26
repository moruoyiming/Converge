package com.example.hotfix.note.framework.binder;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hotfix.R;

public class MMAPActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl);

        //写 使用 mmap 映射文件 在内存当中 并操作这块内存 往这块内存中写入一块数据
        writeTest();
        readTest();
    }

    public native void writeTest();

    public native void readTest();
}

