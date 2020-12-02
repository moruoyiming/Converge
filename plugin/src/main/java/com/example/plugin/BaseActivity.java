package com.example.plugin;

import android.app.Activity;
import android.content.res.Resources;


public abstract class BaseActivity extends Activity {

    @Override
    public Resources getResources() {
        if (getApplication() != null && getApplication().getResources() != null) {
            //  因为宿主重写了该方法，所以获取的将是新创建的resources对象
            return getApplication().getResources();
        }
        return super.getResources();
    }
}
