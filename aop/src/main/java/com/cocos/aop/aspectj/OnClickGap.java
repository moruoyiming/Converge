package com.cocos.aop.aspectj;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class OnClickGap {
    /**
     * 500ms内不响应
     */
    private static final int CLICK_GAP_RESPONSE = 500;
    private static long clickGapTime = 0;

    /**
     * 判断是否应该执行，true执行 ，false执行
     *
     * @return
     */
    private boolean clickGapFilter() {
        long currentTimeMillis = System.currentTimeMillis();
        long durationTime = currentTimeMillis - clickGapTime;
        if (durationTime < CLICK_GAP_RESPONSE) {
            return false;
        }
        clickGapTime = currentTimeMillis;
        return true;
    }

    @Around("execution(@com.cocos.aop.annotation.OnClickGap void *(..))")
    public void clickGapAspect(ProceedingJoinPoint joinPoint) throws Throwable {
        if (clickGapFilter()) {
            joinPoint.proceed();
        }
    }
}
