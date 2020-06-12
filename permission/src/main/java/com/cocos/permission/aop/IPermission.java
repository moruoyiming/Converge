package com.cocos.permission.aop;

/**
 * <pre>
 *     author: jian
 *     Date  : 2020/6/12 8:38 PM
 *     Description:
 * </pre>
 */
public interface IPermission {
    /**
     * 授予权限
     */
    void onPermissionGranted(int requestCode);

    /**
     * 这次拒绝，但是并没有勾选"以后不再提示"
     */
    void onPermissionDenied(int requestCode);

    /**
     * 勾选"以后不再提示"，并且拒绝
     */
    void onPermissionCancel(int requestCode);

}
