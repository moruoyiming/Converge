package com.cocos.base.model;

public interface MvvmNetworkObserver<F> {
    void onSuccess(F t, boolean isFromCache);
    void onFailure(Throwable e);
}
