package com.cocos.network.observer;

import com.cocos.base.model.MvvmBaseModel;
import com.cocos.base.model.MvvmNetworkObserver;
import com.cocos.network.errorhandler.ExceptionHandle;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class BaseObserver<T> implements Observer<T> {
    MvvmBaseModel baseModel;
    MvvmNetworkObserver<T> mvvmNetworkObserver;
    public BaseObserver(MvvmBaseModel baseModel, MvvmNetworkObserver<T> mvvmNetworkObserver) {
        this.baseModel = baseModel;
        this.mvvmNetworkObserver = mvvmNetworkObserver;
    }
    @Override
    public void onError(Throwable e) {
        if(e instanceof ExceptionHandle.ResponeThrowable){
            mvvmNetworkObserver.onFailure(e);
        } else {
            mvvmNetworkObserver.onFailure(new ExceptionHandle.ResponeThrowable(e, ExceptionHandle.ERROR.UNKNOWN));
        }
    }

    @Override
    public void onNext(T t) {
        mvvmNetworkObserver.onSuccess(t, false);
    }

    @Override
    public void onSubscribe(Disposable d) {
        if(baseModel != null){
            baseModel.addDisposable(d);
        }
    }

    @Override
    public void onComplete() {
    }
}
