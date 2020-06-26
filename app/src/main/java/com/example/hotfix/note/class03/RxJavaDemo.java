package com.example.hotfix.note.class03;


import android.app.Activity;
import android.graphics.Bitmap;


import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RxJavaDemo extends Activity {
    /**
     * RxJava 发布订阅模式 + 变异的观察者模式
     * 多个被观察者  一个观察者
     */
    public void RxjavaMethods() {
        //TODO 2.开始分发事件
        Observable.just("Hello world!")
                //TODO 3.String -> Bitamp
                .map(new Function<String, Bitmap>() {
                    @Override
                    public Bitmap apply(@NonNull String s) throws Exception {
                        return null;
                    }
                })
                .map(new Function<Bitmap, Bitmap>() {
                    @Override
                    public Bitmap apply(@NonNull Bitmap s) throws Exception {
                        return null;
                    }
                })
                //子线程 处理耗时操作
                .subscribeOn(Schedulers.io())
                //主线程 更新UI
                .observeOn(AndroidSchedulers.mainThread())
                //TODO 1 起点终点 订阅 主线程执行
                .subscribe(new Observer<Bitmap>() {

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        //TODO 起点终点关联成功
                    }

                    @Override
                    public void onNext(@NonNull Bitmap bitmap) {
                        //TODO 4.终点
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        //TODO 5.结束
                    }
                });
    }


    /**
     * 主线程/io线程切换
     */
    public void IoMainThread() {
        //观察者设计模式   多个被观察者   一个观察者

    }
}
