package com.cocos.base.model;

public interface IBaseModelListener<T> {
    void onLoadSuccess(MvvmBaseModel model, T data, PagingResult... pageResult);

    void onLoadFail(MvvmBaseModel model, String prompt, PagingResult... pageResult);
}