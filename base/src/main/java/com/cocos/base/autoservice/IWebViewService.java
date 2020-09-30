package com.cocos.base.autoservice;

import android.content.Context;

import androidx.fragment.app.Fragment;

import java.util.HashMap;


public interface IWebViewService {

    void startWebViewActivity(Context context, String url, String title, boolean showBar);

    Fragment getWebViewFragment(String url, HashMap<String, String> headers, boolean isSyncToCookie, boolean canNativeRefresh);

}
