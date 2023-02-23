package com.jzyc.instock.ui.stroll;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.jzyc.instock.bean.BaseResponses;
import com.jzyc.instock.bean.NewBean;
import com.jzyc.instock.bean.NewBeanResponses;
import com.jzyc.instock.network.MNetworkApi;
import com.jzyc.instock.network.MnApiInterface;

import java.util.List;
import java.util.Objects;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class StrollViewModel extends ViewModel {

    private MutableLiveData<List<NewBean>> mData;

    public StrollViewModel() {
        mData = new MutableLiveData<>();
    }

    public LiveData<List<NewBean>> getLiveData() {
        return mData;
    }

    public void getStrollData() {
        MNetworkApi.getService(MnApiInterface.class)
                .getNewsList("top", 1, 30, "0", "b8dff355287e73181bf8dba949555f54")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new io.reactivex.Observer<BaseResponses<NewBeanResponses>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResponses<NewBeanResponses> baseResponse) {
                        Log.i("sdfasdfa", Objects.requireNonNull(baseResponse.getResult()).toString());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}