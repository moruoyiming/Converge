package com.jzyc.instock.network;

import com.cocos.network.base.NetworkApi;
import com.cocos.network.beans.TecentBaseResponse;
import com.cocos.network.errorhandler.ExceptionHandle;
import com.cocos.network.utils.TencentUtil;

import java.io.IOException;

import io.reactivex.functions.Function;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class MNetworkApi extends NetworkApi {
    private static volatile MNetworkApi sInstance;

    public static MNetworkApi getInstance() {
        if (sInstance == null) {
            synchronized (MNetworkApi.class) {
                if (sInstance == null) {
                    sInstance = new MNetworkApi();
                }
            }
        }
        return sInstance;
    }

    public static  <T> T getService(Class<T> service) {
        return getInstance().getRetrofit(service).create(service);
    }

    @Override
    protected Interceptor getInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                String timeStr = TencentUtil.getTimeStr();
                Request.Builder builder = chain.request().newBuilder();
//                builder.addHeader("Source", "source");
//                builder.addHeader("Authorization", TencentUtil.getAuthorization(timeStr));
                builder.addHeader("Date", timeStr);
                return chain.proceed(builder.build());
            }
        };
    }

    protected <T> Function<T, T> getAppErrorHandler() {
        return new Function<T, T>() {
            @Override
            public T apply(T response) throws Exception {
                //response中code码不会0 出现错误
                if (response instanceof TecentBaseResponse && ((TecentBaseResponse) response).showapiResCode != 0) {
                    ExceptionHandle.ServerException exception = new ExceptionHandle.ServerException();
                    exception.code = ((TecentBaseResponse) response).showapiResCode;
                    exception.message = ((TecentBaseResponse) response).showapiResError != null ?String.valueOf( ((TecentBaseResponse) response).showapiResError ): "";
                    throw exception;
                }
                return response;
            }
        };
    }

    @Override
    public String getFormal() {
        return "http://v.juhe.cn/toutiao/index/";
    }

    @Override
    public String getTest() {
        return "http://v.juhe.cn/toutiao/index/";
    }
}
