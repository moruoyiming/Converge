package com.example.plugin;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.Nullable;


public abstract class BaseActivity extends Activity {

//    @Override
//    public Resources getResources() {
//        if (getApplication() != null && getApplication().getResources() != null) {
//            //  因为宿主重写了该方法，所以获取的将是新创建的resources对象
//            return getApplication().getResources();
//        }
//        return super.getResources();
//    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Resources resources = LoadUtils.get
    }
}
