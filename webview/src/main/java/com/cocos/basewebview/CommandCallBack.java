package com.cocos.basewebview;

/**
 * <pre>
 *     author: jian
 *     Date  : 2020/5/25 2:13 PM
 *     Description:
 * </pre>
 */
public interface CommandCallBack {
    void onResult(int status, String action, Object result);
}
