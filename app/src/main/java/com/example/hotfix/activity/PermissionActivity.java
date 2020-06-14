package com.example.hotfix.activity;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.cocos.aop.activity.PermissionRequestActivity;
import com.cocos.aop.annotation.PermissionDenied;
import com.cocos.aop.annotation.PermissionDeniedForever;
import com.cocos.aop.annotation.PermissionNeed;
import com.cocos.aop.interfaces.IPermissionCallback;
import com.example.hotfix.R;

/**
 * <pre>
 *     author: jian
 *     Date  : 2020/6/4 6:13 PM
 *     Description:
 * </pre>
 */
public class PermissionActivity extends AppCompatActivity implements View.OnClickListener {
    public static String TAG = "Permission";
    TextView banner, interstitial;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mopub);
        banner = findViewById(R.id.banner);
        banner.setOnClickListener(this);
        interstitial = findViewById(R.id.interstitial);
        interstitial.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.banner:
                requestLocation();
                break;
            case R.id.interstitial:
                requestLocation2();
                break;
        }
    }

    /**
     * 这里写的要特别注意，denied方法，必须是带有一个int参数的方法，下面的也一样
     *
     * @param requestCode
     */
    @PermissionDenied
    public void denied(int requestCode) {
        Log.e(TAG, "权限请求拒绝");
    }

    @PermissionDeniedForever
    public void deniedForever(int requestCode) {
        Log.e(TAG, "权限请求拒绝，用户永久拒绝");
    }

    @PermissionNeed(permissions = Manifest.permission.WRITE_EXTERNAL_STORAGE, requestCode = 1)
    public void requestLocation() {
        Log.i("Permission", "权限请求成功");
    }

    @PermissionNeed(permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.SEND_SMS}, requestCode = 2)
    public void requestLocation2() {
        Log.i("Permission", "权限请求成功");
    }
}
