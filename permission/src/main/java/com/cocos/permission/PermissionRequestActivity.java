package com.cocos.permission;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.cocos.permission.aop.IPermission;
import com.cocos.permission.util.PermissionUtil;

/**
 * <pre>
 *     author: jian
 *     Date  : 2020/6/12 8:58 PM
 *     Description:
 * </pre>
 */
public class PermissionRequestActivity extends Activity {
    private static final String PERMISSION = "permission";
    private static final String REQUEST_CODE = "request_code";
    private static IPermission iPermission;

    public static void startPermissionRequest(Context context, String[] permission, int requestCode, IPermission iPermission) {
        Intent intent = new Intent(context, PermissionRequestActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        Bundle bundle = new Bundle();
        bundle.putStringArray(PERMISSION, permission);
        bundle.putInt(REQUEST_CODE, requestCode);
        intent.putExtras(bundle);
        context.startActivity(intent);
        if (context instanceof Activity) {
            ((Activity) context).overridePendingTransition(0, 0);
        }

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        String[] permission = bundle.getStringArray(PERMISSION);
        int requestCode = bundle.getInt(REQUEST_CODE);
        requestPermission(permission, requestCode);
    }

    public void requestPermission(String[] permission, int requestCode) {

        if (PermissionUtil.hasSelfPermissions(this, permission)) {
            iPermission.onPermissionGranted();
            finish();
        } else {
            ActivityCompat.requestPermissions(this, permission, requestCode);
        }
    }

    public void onRequestPermissionResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (PermissionUtil.verifyPermissions()) {

        } else {

        }
        finish();
        overridePendingTransition(0, 0);
    }
}
