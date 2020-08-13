package com.cocos.basewebview.command;

import android.content.Context;

import com.cocos.basewebview.CommandCallBack;

import java.util.Map;

public interface Command {

    String name();

    void exec(Context context, Map params, CommandCallBack callBack);
}
