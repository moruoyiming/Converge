package com.example.converge.note.jetpack.livedata.databus;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 去掉粘性事件
 */
public class LiveDataBusX {
    //存放订阅者
    private Map<String, BusMutableLiveData<Object>> bus;
    private static LiveDataBusX liveDataBus = new LiveDataBusX();

    private LiveDataBusX() {
        bus = new HashMap<>();
    }

    public static LiveDataBusX getInstance() {
        return liveDataBus;
    }

    //注册订阅者
    public synchronized <T> BusMutableLiveData<T> with(String key, Class<T> type) {
        if (!bus.containsKey(key)) {
            bus.put(key, new BusMutableLiveData<Object>());
        }
        return (BusMutableLiveData) bus.get(key);
    }

    public static class BusMutableLiveData<T> extends MutableLiveData {
        @Override
        public void observe(@NonNull LifecycleOwner owner, @NonNull Observer observer) {
            super.observe(owner, observer);
            hook(observer);
        }

        private void hook(Observer<? super T> observer) {
            try {
                //1.得到mLastVersion
                //获取到LivData的类中的mObservers对象
                Class<LiveData> liveDataClass = LiveData.class;
                Field mObserversField = liveDataClass.getDeclaredField("mObservers");
                mObserversField.setAccessible(true);
                //获取到这个成员变量的对象
                Object mObserversObject = mObserversField.get(this);
                //得到map对象的class对象
                Class<?> mObserversClass = mObserversObject.getClass();
                //获取到mObservers对象的get方法
                Method get = mObserversClass.getDeclaredMethod("get", Object.class);
                get.setAccessible(true);
                //执行get方法
                Object invokeEntry = get.invoke(mObserversObject, observer);
                //取到entry中的value
                Object observerWraper = null;
                if (invokeEntry != null && invokeEntry instanceof Map.Entry) {
                    observerWraper = ((Map.Entry) invokeEntry).getValue();
                }
                if (observerWraper == null) {
                    throw new NullPointerException("observerWraper is null");
                }
                //得到observerWraperr的类对象
                Class<?> supperClass = observerWraper.getClass().getSuperclass();
                Field mLastVersion = supperClass.getDeclaredField("mLastVersion");
                mLastVersion.setAccessible(true);
                //2.得到mVersion
                Field mVersion = liveDataClass.getDeclaredField("mVersion");
                mVersion.setAccessible(true);
                //3.mLastVersion=mVersion
                Object mVersionValue = mVersion.get(this);
                mLastVersion.set(observerWraper, mVersionValue);

            } catch (Exception e) {
                e.printStackTrace();

            }
        }
    }
}













