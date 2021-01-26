package com.example.hotfix.note.inject;

import com.cocos.network.beans.NewsListBean;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NewApiInterface {
    @GET
    Call<NewsListBean> getNewsList(String name, String pwd);
}
