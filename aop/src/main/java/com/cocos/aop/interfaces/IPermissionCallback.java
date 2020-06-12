package com.cocos.aop.interfaces;

public interface IPermissionCallback {
    /**
     * 授予权限
     */
    void granted(int requestCode);

    /**
     * 这次拒绝，但是并没有勾选"以后不再提示"
     */
    void denied(int requestCode);

    /**
     * 勾选"以后不再提示"，并且拒绝
     */
    void deniedForever(int requestCode);

}
