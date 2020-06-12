package com.cocos.aop.constants;

import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.cocos.aop.constants.PermissionConstants.NEED_PERMISSIONS;
import static com.cocos.aop.constants.PermissionConstants.REQUEST_CODE;

@StringDef({NEED_PERMISSIONS,REQUEST_CODE})
@Retention(RetentionPolicy.SOURCE)
public @interface PermissionConstants {
    String NEED_PERMISSIONS = "need_permission";
    String REQUEST_CODE = "request_code";
}