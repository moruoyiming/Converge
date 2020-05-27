package com.cocos.network;

import com.cocos.network.utils.TencentUtil;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetWorkApi {

    private static final String TAG = "NetWorkApi";
    private static final String TENCENT_BASE_URL = "http://service-o5ikp40z-1255468759.ap-shanghai.apigateway.myqcloud.com/";

    //构建Retrofit 建造者模式
    public static Retrofit getRetrofit() {
        Retrofit.Builder build = new Retrofit.Builder();
        build.baseUrl(TENCENT_BASE_URL);
        build.addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = build.build();
        return retrofit;
    }

    public static void getTencentNewsChannel() {
        Retrofit.Builder build = new Retrofit.Builder();
        build.baseUrl(TENCENT_BASE_URL);
        build.addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = build.build();
        //动态代理 获取实例对象
        NewApiInterface newApiInterface = retrofit.create(NewApiInterface.class);
        String timeStr = TencentUtil.getTimeStr();
    }

}
