package com.example.hotfix.note.class02;//package com.cocos.network;

import com.cocos.network.beans.NewsListBean;
import com.cocos.network.utils.TencentUtil;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetWorkApi {

    private static final String TAG = "NetWorkApi";
    private static final String TENCENT_BASE_URL = "http://service-o5ikp40z-1255468759.ap-shanghai.apigateway.myqcloud.com/";
    private static OkHttpClient mOkHttpClient;

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
        build.client(getOkHttpClient());
        Retrofit retrofit = build.build();
        //动态代理 获取实例对象
        NewApiInterface newApiInterface = retrofit.create(NewApiInterface.class);
        String timeStr = TencentUtil.getTimeStr();
        Call<NewsListBean> call = newApiInterface.getNewsList("", "");
//        同步请求接口
//      call.execute();
//        异步请求接口
        call.enqueue(new Callback<NewsListBean>() {
            @Override
            public void onResponse(Call<NewsListBean> call, Response<NewsListBean> response) {

            }

            @Override
            public void onFailure(Call<NewsListBean> call, Throwable t) {

            }
        });



    }

    private static OkHttpClient getOkHttpClient() {
        if (mOkHttpClient == null) {
            OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
            mOkHttpClient = okHttpClientBuilder.build();

        }
        return mOkHttpClient;
    }

}
