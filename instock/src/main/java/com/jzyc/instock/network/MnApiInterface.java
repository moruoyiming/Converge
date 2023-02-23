package com.jzyc.instock.network;

import com.jzyc.instock.bean.BaseResponses;
import com.jzyc.instock.bean.NewBeanResponses;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface MnApiInterface {

    @GET("index")
//type=&page=&page_size=&is_filter=&key=b8dff355******49555f54
    Observable<BaseResponses<NewBeanResponses>> getNewsList(@Query("type") String type,
                                                            @Query("page") int page,
                                                            @Query("page_size") int page_size,
                                                            @Query("is_filter") String is_filter,
                                                            @Query("key") String key);
}
