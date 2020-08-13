package com.cocos.basewebview.command;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebView;

import com.cocos.basewebview.CommandCallBack;
import com.cocos.basewebview.widget.BaseWebView;
import com.cocos.basewebview.mainprocess.CommandsManager;
import com.cocos.basewebview.mainprocess.MainProcessConnector;
import com.cocos.basewebview.utils.MainLooper;
import com.cocos.basewebview.utils.WebConstants;
import com.google.gson.Gson;
import com.weblib.webview.ICallbackFromMainToWeb;
import com.weblib.webview.IWebToMain;

import java.util.Map;

public class CommandDispatcher {
    private static CommandDispatcher instance;
    private Gson gson = new Gson();

    // 实现跨进程通信的接口
    protected IWebToMain webAidlInterface;

    public static CommandDispatcher getInstance() {
        if (instance == null) {
            synchronized (CommandDispatcher.class) {
                if (instance == null) {
                    instance = new CommandDispatcher();
                }
            }
        }
        return instance;
    }

    public void initAidlConnect(final Context context) {
        if (webAidlInterface != null) {
            return;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.i("AIDL", "Begin to connect with main process");
                webAidlInterface = IWebToMain.Stub.asInterface(MainProcessConnector.getInstance(context).getIWebAidlInterface());
                Log.i("AIDL", "Connect success with main process");
            }
        }).start();
    }

    public void exec(Context context, String cmd, String params, final WebView webView) {
        Log.i("CommandDispatcher", "command: " + cmd + " params: " + params);
        try {
            if (CommandsManager.getInstance().isWebviewProcessCommand(cmd)) {
                Map mapParams = gson.fromJson(params, Map.class);
                CommandsManager.getInstance().execWebviewProcessCommand(context, cmd, mapParams, new CommandCallBack() {
                    @Override
                    public void onResult(int status, String action, Object result) {
                        handleCallback(status, action, gson.toJson(result), webView);
                    }
                });
            } else {
                if (webAidlInterface != null) {
                    webAidlInterface.handleWebAction(cmd, params, new ICallbackFromMainToWeb.Stub() {
                        @Override
                        public void onResult(int responseCode, String actionName, String response) {
                            handleCallback(responseCode, actionName, response, webView);
                        }
                    });
                }
            }
        } catch (Exception e) {
            Log.e("CommandDispatcher", "Command exec error!!!!", e);
        }
    }

    private void handleCallback(final int responseCode, final String actionName, final String response,
                                final WebView webView) {
        Log.d("CommandDispatcher", String.format("Callback result: action= %s, result= %s", actionName, response));
        MainLooper.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Map params = new Gson().fromJson(response, Map.class);
                if (params.get(WebConstants.NATIVE2WEB_CALLBACK) != null && !TextUtils.isEmpty(params.get(WebConstants.NATIVE2WEB_CALLBACK).toString())) {
                    if (webView instanceof BaseWebView) {
                        ((BaseWebView) webView).handleCallback(response);
                    }
                }
            }
        });
    }
}
