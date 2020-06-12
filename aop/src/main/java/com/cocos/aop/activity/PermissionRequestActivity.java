package com.cocos.aop.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.cocos.aop.constants.PermissionConstants;
import com.cocos.aop.interfaces.IPermissionCallback;
import com.cocos.aop.util.PermissionUtil;

public class PermissionRequestActivity extends AppCompatActivity {
    private static IPermissionCallback mPermissionCallback;

    public static void startPermissionRequest(Context context, String[] permission, int requestCode, IPermissionCallback callback) {

        if (context == null) {
            return;
        }
        mPermissionCallback = callback;

        Intent intent = new Intent(context, PermissionRequestActivity.class);

        //开启新任务栈并且清除栈顶
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        Bundle bundle = new Bundle();
        bundle.putStringArray(PermissionConstants.NEED_PERMISSIONS, permission);
        bundle.putInt(PermissionConstants.REQUEST_CODE, requestCode);
        intent.putExtras(bundle);
        context.startActivity(intent);

        //如果是activity启动时，屏蔽掉activity切换动画
        if (context instanceof Activity) {
            ((Activity) context).overridePendingTransition(0, 0);
        }

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        String[] permission = bundle.getStringArray(PermissionConstants.NEED_PERMISSIONS);
        int requestCode = bundle.getInt(PermissionConstants.REQUEST_CODE, 0);
        requestPermission(permission, requestCode);
    }

    public void requestPermission(String[] permission, int requestCode) {
        if (permission == null) {
            return;
        }
        if (PermissionUtil.hasSelfPermissions(this, permission)) {
            mPermissionCallback.granted(requestCode);
            finish();
            overridePendingTransition(0, 0);
        } else {
            ActivityCompat.requestPermissions(this, permission, requestCode);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //获取权限的申请结果，本Activity只做申请操作，需要将申请结果通知外界，结果的处理需外界传递
        boolean granted = PermissionUtil.verifyPermissions();
        if (granted) {
            mPermissionCallback.granted(requestCode);
        } else {
            if (PermissionUtil.shouldShowRequestPermissionRationale(this, permissions)) {
                mPermissionCallback.denied(requestCode);
            } else {
                mPermissionCallback.deniedForever(requestCode);
            }
        }
        finish();
        overridePendingTransition(0, 0);
    }
}
