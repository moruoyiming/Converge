package com.example.converge.note.androidheight.jetpack.lifecycle.databus;

import androidx.lifecycle.MutableLiveData;

import java.util.HashMap;
import java.util.Map;

/**
 * 这条总线用于把任何类中的数据直接传递到activity或是fragment上
 */
public class LiveDataBus {
    //存放订阅者
    private Map<String, MutableLiveData<Object>> bus;

    private static LiveDataBus liveDataBus = new LiveDataBus();

    private LiveDataBus() {
        bus = new HashMap<>();
    }

    public static LiveDataBus getInstance() {
        return liveDataBus;
    }

    //注册订阅者，（存入map）
    public synchronized <T> MutableLiveData<T> with(String key, Class<T> type){
        if(!bus.containsKey(key)){
            bus.put(key,new MutableLiveData<Object>());
        }
        return (MutableLiveData<T>) bus.get(key);
    }




}

