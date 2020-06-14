package com.example.hotfix.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hotfix.network.MnApiInterface;
import com.example.hotfix.beans.What;
import com.example.hotfix.utils.MD5Utils;
import com.example.hotfix.beans.VoiceRequest;
import com.example.hotfix.network.MNetworkApi;
import com.example.hotfix.R;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class NetworkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);
        setTitle("网络请求");
        findViewById(R.id.get_data).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testNetWorkApi();
            }
        });

    }

    public void testNetWorkApi() {
        long curenttime = System.currentTimeMillis();
        Map<String, Object> map = new HashMap<>();
        map.put("pid", "cbjt");
        map.put("timestamp", curenttime);
        map.put("nonce", curenttime);
        map.put("appKey", "0076debe40f74eae84c9dff4dd9babee");
        String sign = null;
        try {
            sign = crateSign(map, false);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        VoiceRequest voiceRequest = new VoiceRequest();
        voiceRequest.setPid("cbjt");
        voiceRequest.setSign(sign);
        voiceRequest.setNonce(String.valueOf(curenttime));
        voiceRequest.setInputStr("ᠪᠠᠲᠤᠡᠮᠨᠡᠭᠦᠯᠬᠦᠲᠠᠰᠤᠭ ᠲᠤ᠋ᠣᠴᠢᠵᠤᠨᠢᠭᠡᠡᠮᠴᠢᠯᠡᠭᠦᠯᠬᠦᠪᠣᠯᠪᠠᠤ᠃");
        voiceRequest.setFormat(1);
        MNetworkApi.getService(MnApiInterface.class)
                .getNewsList(voiceRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<What>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(What what) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public String crateSign(Map<String, Object> params, boolean encode) throws UnsupportedEncodingException {
        Set<String> keysSet = params.keySet();
        Object[] keys = keysSet.toArray();
        Arrays.sort(keys);
        StringBuffer temp = new StringBuffer();
        boolean first = true;
        for (Object key : keys) {
            if (first) {
                first = false;
            } else {
                temp.append("&");
            }
            temp.append(key).append("=");
            Object value = params.get(key);
            String valueString = "";
            if (null != value) {
                valueString = String.valueOf(value);
            }
            if (encode) {
                temp.append(URLEncoder.encode(valueString, "UTF-8"));
            } else {
                temp.append(valueString);
            }
        }
        String m = MD5Utils.getStringMD5(temp.toString()).toLowerCase();
        Log.i("xxxxxxxxx", "签名数据 " + temp.toString() + " MD5 加密数据 " + MD5Utils.getStringMD5(temp.toString()) + "   " + m);
        Log.i("xxxxxxxxx", "   " + m + "     " + m.toUpperCase());
        return m;
    }

}
