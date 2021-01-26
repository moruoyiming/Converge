package com.example.hotfix.note.frame.hilt.httpprocessor;

/**
 * 顶层的回调接口   string---->json,xml,protobuff
 */
public interface ICallback {
    void onSuccess(String result);
    void onFailure(String e);
}
