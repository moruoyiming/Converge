package com.cocos.aop.aspectj;

import android.content.Context;

import com.cocos.aop.activity.PermissionRequestActivity;
import com.cocos.aop.annotation.PermissionNeed;
import com.cocos.aop.interfaces.IPermissionCallback;
import com.cocos.aop.util.ApplicationUtil;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;


@Aspect
public class PermissionAspect {

    @Around("execution(@com.cocos.aop.PermissionNeed * *(..) && @annotation(permissionNeed))")
    public void doPermission(ProceedingJoinPoint joinPoint, PermissionNeed permissionNeed) {

        PermissionRequestActivity.startPermissionRequest(getContext(joinPoint), permissionNeed.permissions(), permissionNeed.requestCode(), new IPermissionCallback() {
            @Override
            public void granted(int requestCode) {

            }

            @Override
            public void denied(int requestCode) {

            }

            @Override
            public void deniedForever(int requestCode) {

            }
        });
    }

    private Context getContext(ProceedingJoinPoint joinPoint) {
        Object obj = joinPoint.getThis();
        //如果切入点是一个类？那么这个类的对象是不是context？
        if (obj instanceof Context) {
            return (Context) obj;
        } else {
            //如果切入点不是Context的子类呢？//joinPoint.getThis,其实得到切入点所在类的对象
            Object[] args = joinPoint.getArgs();
            if (args.length > 0) {
                //判断第一个参数是否为context
                if (args[0] instanceof Context) {
                    return (Context) args[0];
                } else {
                    //如果不是，hook反射
                    return ApplicationUtil.getApplication();
                }
            } else {
                //如果不是，hook反射
                return ApplicationUtil.getApplication();
            }
        }

    }
}
