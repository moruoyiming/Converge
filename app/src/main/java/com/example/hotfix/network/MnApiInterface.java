package com.example.hotfix.network;

import com.example.hotfix.beans.VoiceRequest;
import com.example.hotfix.beans.What;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface MnApiInterface {

    @POST("api/tts/v1")
    Observable<What> getNewsList(@Body() VoiceRequest voiceRequest);

}
