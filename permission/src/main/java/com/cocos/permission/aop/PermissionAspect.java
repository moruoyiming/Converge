package com.cocos.permission.aop;

import android.content.Context;

import com.cocos.permission.PermissionRequestActivity;
import com.cocos.permission.annotation.PermissionNeed;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * <pre>
 *     author: jian
 *     Date  : 2020/6/12 8:44 PM
 *     Description:
 * </pre>
 */
@Aspect
public class PermissionAspect {

    @Pointcut("execution(@com.cocos.permission.PermissionNeed * *(..) && @annotation(permissionNeed))")
    public void requestPermission(PermissionNeed permissionNeed) {

    }

    @Around("requestPermission(permissionNeed)")
    public void aroundJoinPoint(ProceedingJoinPoint joinPoint, PermissionNeed permissionNeed) {
        Object obj = joinPoint.getThis();
        Context context= (Context) obj;
        PermissionRequestActivity.startPermissionRequest(context, permissionNeed.value(), permissionNeed.requestCode(), new IPermission() {
            @Override
            public void onPermissionGranted(int requestCode) {

            }

            @Override
            public void onPermissionDenied(int requestCode) {

            }

            @Override
            public void onPermissionCancel(int requestCode) {

            }
        });
    }
}
