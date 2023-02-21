package com.jzyc.instock.network;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface MnApiInterface {

    @GET("release/news")//type=&page=&page_size=&is_filter=&key=b8dff355******49555f54
    Observable<String> getNewsList(@Query("type") String type,
                                          @Query("page") int page,
                                          @Query("page_size") int page_size,
                                          @Query("is_filter") String is_filter,
                                          @Query("key") String key);
}
