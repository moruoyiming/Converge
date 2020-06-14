package com.cocos.aop.aspectj;

import android.content.Context;
import android.util.Log;

import com.cocos.aop.activity.PermissionRequestActivity;
import com.cocos.aop.annotation.PermissionDenied;
import com.cocos.aop.annotation.PermissionDeniedForever;
import com.cocos.aop.annotation.PermissionNeed;
import com.cocos.aop.interfaces.IPermissionCallback;
import com.cocos.aop.util.ApplicationUtil;
import com.cocos.aop.util.PermissionUtil;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;


@Aspect
public class PermissionAspect {

//    @Pointcut("execution(@com.cocos.aop.annotation.PermissionNeed * *(..)) && @annotation(permissionNeed)")
//    public void requestLocation(PermissionNeed permissionNeed) {
//        Log.i("Permission","requestLocation");
//    }
//
//
//    @Around("requestPermission(permissionNeed)")
//    public void aroundJoinPoint(final ProceedingJoinPoint joinPoint, PermissionNeed permissionNeed) {
//        Log.i("Permission","aroundJoinPoint");
//        PermissionRequestActivity.startPermissionRequest(getContext(joinPoint), permissionNeed.permissions(), permissionNeed.requestCode(), new IPermissionCallback() {
//            @Override
//            public void granted(int requestCode) {
//                try {
//                    joinPoint.proceed();
//                } catch (Throwable e) {
//                    e.printStackTrace();
//                }
//
//            }
//
//            @Override
//            public void denied(int requestCode) {
//                PermissionUtil.invokeAnnotation(joinPoint.getThis(), PermissionDenied.class, requestCode);
//            }
//
//            @Override
//            public void deniedForever(int requestCode) {
//                PermissionUtil.invokeAnnotation(joinPoint.getThis(), PermissionDeniedForever.class, requestCode);
//            }
//        });
//    }

    //@注解 访问权限 返回值类型 类名 函数名 (参数)
    @Around("execution(@com.cocos.aop.annotation.PermissionNeed * *(..)) && @annotation(permissionNeed)")
    public void doPermission(final ProceedingJoinPoint joinPoint, PermissionNeed permissionNeed) {
        Log.i("Permission", "startPermissionRequest");
        PermissionRequestActivity.startPermissionRequest(getContext(joinPoint), permissionNeed.permissions(), permissionNeed.requestCode(), new IPermissionCallback() {
            @Override
            public void granted(int requestCode) {
                try {
                    joinPoint.proceed();
                } catch (Throwable e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void denied(int requestCode) {
                PermissionUtil.invokeAnnotation(joinPoint.getThis(), PermissionDenied.class, requestCode);
            }

            @Override
            public void deniedForever(int requestCode) {
                PermissionUtil.invokeAnnotation(joinPoint.getThis(), PermissionDeniedForever.class, requestCode);
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
