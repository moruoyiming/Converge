package com.cocos.aop.aspectj;

import android.content.Context;
import android.util.Log;

import com.cocos.aop.activity.PermissionRequestActivity;
import com.cocos.aop.annotation.PermissionCancel;
import com.cocos.aop.annotation.PermissionDenied;
import com.cocos.aop.annotation.Permission;
import com.cocos.aop.interfaces.IPermissionCallback;
import com.cocos.aop.util.ApplicationUtil;
import com.cocos.aop.util.PermissionUtil;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;


@Aspect
public class PermissionAspect {
    public static String TAG = "Permission";

    @Pointcut("execution(@com.cocos.aop.annotation.Permission * *(..)) && @annotation(permission)")
    public void doPermission(Permission permission) {
        Log.e(TAG, "doPermission");
    }

    @Around("doPermission(permission)")
    public void proceedingJoinPoint(final ProceedingJoinPoint point, Permission permission) {
        Log.e(TAG, "dowhat");
        PermissionRequestActivity.startPermissionRequest(getContext(point), permission.permissions(),
                permission.requestCode(), new IPermissionCallback() {
                    @Override
                    public void granted(int requestCode) {
                        // 如果授予，那么执行joinPoint原方法体
                        try {
                            point.proceed();
                        } catch (Throwable throwable) {
                            throwable.printStackTrace();
                        }
                    }

                    @Override
                    public void denied(int requestCode) {
                        PermissionUtil.invokeAnnotation(point.getThis(), PermissionCancel.class, requestCode);
                    }

                    @Override
                    public void deniedForever(int requestCode) {
                        PermissionUtil.invokeAnnotation(point.getThis(), PermissionDenied.class, requestCode);
                    }
                });
    }


    private Context getContext(final ProceedingJoinPoint joinPoint) {
        final Object obj = joinPoint.getThis();
        // 如果切入点是一个类？那么这个类的对象是不是context？
        if (obj instanceof Context) {
            return (Context) obj;
        } else {
            // 如果切入点不是Context的子类呢？ //jointPoint.getThis，其实是得到切入点所在类的对象
            Object[] args = joinPoint.getArgs();
            if (args.length > 0) {
                //看看第一个参数是不是context
                if (args[0] instanceof Context) {
                    return (Context) args[0];
                } else {
                    //如果不是，那么就只好hook反射了
                    return ApplicationUtil.getApplication();
                }
            } else {
                //如果不是，那么就只好hook反射了
                return ApplicationUtil.getApplication();
            }
        }
    }
}
