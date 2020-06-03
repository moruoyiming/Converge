package com.cocos.base.model;

import android.text.TextUtils;

import androidx.annotation.CallSuper;

import com.cocos.base.preference.BasicDataPreferenceUtil;
import com.cocos.base.utils.GsonUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by plout on 2017/7/20.
 * 保留所有版权，未经允许请不要分享到互联网和其他人
 */
public abstract class MvvmBaseModel<F, T extends ArrayList> implements MvvmNetworkObserver<F>{
    private CompositeDisposable compositeDisposable;
    protected ReferenceQueue<IBaseModelListener> mReferenceQueue;
    protected ConcurrentLinkedQueue<WeakReference<IBaseModelListener>> mWeakListenerArrayList;
    private BaseCachedData<F> mData;
    protected boolean isRefresh = true;
    protected int pageNumber = 0;
    private String mCachedPreferenceKey;
    private String mApkPredefinedData;
    private boolean mIsPaging;
    private Class<F> clazz;

    public MvvmBaseModel(Class<F> clazz, boolean isPaging, String cachePreferenceKey, String apkPredefinedData, int... initPageNumber) {
        this.mIsPaging = isPaging;
        this.clazz = clazz;
        if(initPageNumber != null && initPageNumber.length == 1) {
            this.pageNumber = initPageNumber[0];
        }
        this.mCachedPreferenceKey = cachePreferenceKey;
        this.mApkPredefinedData = apkPredefinedData;
        mReferenceQueue = new ReferenceQueue<>();
        mWeakListenerArrayList = new ConcurrentLinkedQueue<>();
        if (mCachedPreferenceKey != null) {
            mData = new BaseCachedData<F>();
        }
    }

    public boolean isPaging() {
        return mIsPaging;
    }

    /**
     * 注册监听
     *
     * @param listener
     */
    public void register(IBaseModelListener listener) {
        if (listener == null) {
            return;
        }

        synchronized (this) {
            // 每次注册的时候清理已经被系统回收的对象
            Reference<? extends IBaseModelListener> releaseListener = null;
            while ((releaseListener = mReferenceQueue.poll()) != null) {
                mWeakListenerArrayList.remove(releaseListener);
            }

            for (WeakReference<IBaseModelListener> weakListener : mWeakListenerArrayList) {
                IBaseModelListener listenerItem = weakListener.get();
                if (listenerItem == listener) {
                    return;
                }
            }
            WeakReference<IBaseModelListener> weakListener = new WeakReference<IBaseModelListener>(listener, mReferenceQueue);
            mWeakListenerArrayList.add(weakListener);
        }

    }

    /**
     * 取消监听
     *
     * @param listener
     */
    public void unRegister(IBaseModelListener listener) {

        if (listener == null) {
            return;
        }
        synchronized (this) {
            for (WeakReference<IBaseModelListener> weakListener : mWeakListenerArrayList) {
                IBaseModelListener listenerItem = weakListener.get();
                if (listener == listenerItem) {
                    mWeakListenerArrayList.remove(weakListener);
                    break;
                }
            }
        }
    }

    /**
     * 由于渠道处在App的首页，为了保证app打开的时候由于网络慢或者异常的情况下tablayout不为空，
     * 所以app对渠道数据进行了预制；
     * 加载完成以后会立即进行网络请求，同时缓存在本地，今后app打开都会从preference读取，而不在读取
     * apk预制数据，由于渠道数据变化没那么快，在app第二次打开的时候会生效，并且是一天请求一次。
     */
    protected void saveDataToPreference(F data) {
        if(data != null) {
            mData.data = data;
            mData.updateTimeInMills = System.currentTimeMillis();
            BasicDataPreferenceUtil.getInstance().setString(mCachedPreferenceKey, GsonUtils.toJson(mData));
        }
    }

    public abstract void refresh();

    protected abstract void load();

    /**
     * 是否更新数据，可以在这里设计策略，可以是一天一次，一月一次等等，
     * 默认是每次请求都更新
     */
    protected boolean isNeedToUpdate() {
        return true;
    }

    @CallSuper
    public void cancel() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }


    public void addDisposable(Disposable d) {
        if (d == null) {
            return;
        }

        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }

        compositeDisposable.add(d);
    }

    public void getCachedDataAndLoad() {
        if (mCachedPreferenceKey != null) {
            String saveDataString = BasicDataPreferenceUtil.getInstance().getString(mCachedPreferenceKey);
            if (!TextUtils.isEmpty(saveDataString)) {
                try {
                    F savedData = GsonUtils.fromLocalJson(new JSONObject(saveDataString).getString("data"), clazz);
                    if (savedData != null && savedData != null) {
                        onSuccess(savedData, true);
                        if (isNeedToUpdate()) {
                            load();
                        }
                        return;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            if (mApkPredefinedData != null) {
                F savedData = GsonUtils.fromLocalJson(mApkPredefinedData, clazz);
                if(savedData != null && savedData != null) {
                    onSuccess(savedData, true);
                }
            }
        }
        load();
    }

    /**
     * 发消息给UI线程
     */
    protected void loadSuccess(F networkResonseBean, T data, boolean isFromCache) {
        synchronized (this) {
            for (WeakReference<IBaseModelListener> weakListener : mWeakListenerArrayList) {
                if (weakListener.get() instanceof IBaseModelListener) {
                    IBaseModelListener listenerItem = weakListener.get();
                    if (listenerItem != null) {
                        if (isPaging()) {
                            listenerItem.onLoadFinish(this, data,
                                    isFromCache? new PagingResult(false, true, true) :
                                            new PagingResult(data.isEmpty(), isRefresh, data.size() > 0));
                            if(mCachedPreferenceKey != null && isRefresh && !isFromCache) {
                                saveDataToPreference(networkResonseBean);
                            }
                            if(!isFromCache) {
                                pageNumber ++;
                            }
                        } else {
                            listenerItem.onLoadFinish(this, data);
                            if(mCachedPreferenceKey != null) {
                                saveDataToPreference(networkResonseBean);
                            }
                        }
                    }
                }
            }
        }
    }

    protected void loadFail(final String errorMessage) {
        synchronized (this) {
            for (WeakReference<IBaseModelListener> weakListener : mWeakListenerArrayList) {
                if (weakListener.get() instanceof IBaseModelListener) {
                    IBaseModelListener listenerItem = weakListener.get();
                    if (listenerItem != null) {
                        if (isPaging()) {
                            listenerItem.onLoadFail(this, errorMessage, new PagingResult(true, isRefresh, false));
                        } else {
                            listenerItem.onLoadFail(this, errorMessage);
                        }
                    }
                }
            }
        }
    }
}
