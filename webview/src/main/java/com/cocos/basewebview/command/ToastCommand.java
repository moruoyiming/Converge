package com.cocos.basewebview.command;

import android.content.Context;
import android.widget.Toast;


import com.cocos.basewebview.CommandCallBack;
import com.google.auto.service.AutoService;

import java.util.Map;

@AutoService({Command.class})
public class ToastCommand implements Command {
    @Override
    public String name() {
        return "showToast";
    }

    @Override
    public void exec(Context context, Map params, CommandCallBack resultBack) {
        Toast.makeText(context, String.valueOf(params.get("message")), Toast.LENGTH_SHORT).show();
    }
}
