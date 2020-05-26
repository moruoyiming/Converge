package com.cocos.basewebview.command;

import android.content.Context;

import com.cocos.basewebview.WebviewCallBack;

import java.util.Map;

/**
 * <pre>
 *     author: jian
 *     Date  : 2020/5/25 2:17 PM
 *     Description:
 * </pre>
 */
public interface Command {
    String COMMAND_UPDATE_TITLE = "xiangxue_webview_update_title";
    String COMMAND_UPDATE_TITLE_PARAMS_TITLE = "xiangxue_webview_update_title_params_title";

    String name();

    void exec(Context context, Map params, WebviewCallBack callBack);
}
