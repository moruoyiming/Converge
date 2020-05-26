package com.example.hotfix;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.cocos.basewebview.WebviewCallBack;
import com.cocos.basewebview.command.Command;

import java.util.Map;

public class BaseLevelCommands implements Command {

    @Override
    public String name() {
        return "newPage";
    }

    @Override
    public void exec(Context context, Map params, WebviewCallBack callBack) {
        String newUrl = params.get("url").toString();
        String title = (String) params.get("title");
        Intent intent = new Intent(context, AActivity.class);
        intent.putExtra("title",title);
        context.startActivity(intent);
    }
}
