package com.cocos.basewebview;

import android.app.Service;
import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.cocos.basewebview.utils.WebConstants;
import com.example.common.autoservice.IWebViewService;
import com.google.auto.service.AutoService;

import java.util.HashMap;

@AutoService(IWebViewService.class)
public class WebViewServiceImpl implements IWebViewService {

    @Override
    public void startWebViewActivity(Context context, String url, String title, boolean showBar) {
        if (context != null) {
            Intent intent = new Intent(context, WebViewActivity.class);
            intent.putExtra(WebConstants.INTENT_TAG_TITLE, title);
            intent.putExtra(WebConstants.INTENT_TAG_URL, url);
            intent.putExtra(WebConstants.INTENT_TAG_IS_SHOW_ACTION_BAR, showBar);
            if (context instanceof Service) {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }
            context.startActivity(intent);
        }
    }

    @Override
    public Fragment getWebViewFragment(String url, HashMap<String, String> headers, boolean isSyncToCookie, boolean canNativeRefresh) {
        return WebViewFragment.newInstance(url, headers, isSyncToCookie);
    }


}
