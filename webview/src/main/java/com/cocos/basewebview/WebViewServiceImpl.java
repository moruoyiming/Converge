package com.cocos.basewebview;

import android.app.Service;
import android.content.Context;
import android.content.Intent;

import com.cocos.basewebview.utils.WebConstants;
import com.example.common.autoservice.IWebViewService;
import com.google.auto.service.AutoService;

@AutoService({IWebViewService.class})
public class WebViewServiceImpl implements IWebViewService {
    @Override
    public void startWebViewActivity(Context context, String url, String title) {
        if (context != null) {
            Intent intent = new Intent(context, WebViewActivity.class);
            intent.putExtra(WebConstants.INTENT_TAG_TITLE, title);
            intent.putExtra(WebConstants.INTENT_TAG_URL, url);
            if (context instanceof Service) {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }
            context.startActivity(intent);
        }
    }
}
