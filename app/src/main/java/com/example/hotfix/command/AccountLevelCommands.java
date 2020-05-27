package com.example.hotfix.command;

import android.content.Context;
import android.text.TextUtils;

import com.cocos.basewebview.WebviewCallBack;
import com.cocos.basewebview.command.Command;
import com.cocos.basewebview.utils.AidlError;
import com.cocos.basewebview.utils.WebConstants;

import java.util.HashMap;
import java.util.Map;


public class AccountLevelCommands implements Command {

    @Override
    public String name() {
        return "appDataProvider";
    }

    @Override
    public void exec(Context context, Map params, WebviewCallBack callBack) {
        try {
            String callbackName = "";
            if (params.get("type") == null) {
                AidlError aidlError = new AidlError(WebConstants.ERRORCODE.ERROR_PARAM, WebConstants.ERRORMESSAGE.ERROR_PARAM);
                callBack.onResult(WebConstants.FAILED, this.name(), aidlError);
                return;
            }
            if (params.get(WebConstants.WEB2NATIVE_CALLBACk) != null) {
                callbackName = params.get(WebConstants.WEB2NATIVE_CALLBACk).toString();
            }
            String type = params.get("type").toString();
            HashMap map = new HashMap();
            switch (type) {
                case "account":
                    map.put("accountId", "test123456");
                    map.put("accountName", "xud");
                    break;
            }
            if (!TextUtils.isEmpty(callbackName)) {
                map.put(WebConstants.NATIVE2WEB_CALLBACK, callbackName);
            }
            callBack.onResult(WebConstants.SUCCESS, this.name(), map);
        } catch (Exception e) {
            e.printStackTrace();
            AidlError aidlError = new AidlError(WebConstants.ERRORCODE.ERROR_PARAM, WebConstants.ERRORMESSAGE.ERROR_PARAM);
            callBack.onResult(WebConstants.FAILED, this.name(), aidlError);
        }
    }
}
