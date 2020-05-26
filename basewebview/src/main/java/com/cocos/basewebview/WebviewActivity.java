package com.cocos.basewebview;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.cocos.basewebview.command.Command;
import com.cocos.basewebview.mainprocess.CommandsManager;
import com.cocos.basewebview.utils.WebConstants;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 *     author: jian
 *     Date  : 2020/5/25 2:13 PM
 *     Description:
 * </pre>
 */
public class WebviewActivity extends AppCompatActivity {
    private String title;
    private String url;

    BaseFragment webviewFragment;

    public static void startCommonWeb(Context context, String title, String url) {
        Intent intent = new Intent(context, WebviewActivity.class);
        intent.putExtra(WebConstants.INTENT_TAG_TITLE, title);
        intent.putExtra(WebConstants.INTENT_TAG_URL, url);
        if (context instanceof Service) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_web);
        title = getIntent().getStringExtra(WebConstants.INTENT_TAG_TITLE);
        url = getIntent().getStringExtra(WebConstants.INTENT_TAG_URL);
        setTitle(title);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        CommandsManager.getInstance().registerCommand(titleUpdateCommand);
        webviewFragment = null;
        webviewFragment = WebviewFragment.newInstance(url, (HashMap<String, String>) getIntent().getExtras().getSerializable(WebConstants.INTENT_TAG_HEADERS), true);
        transaction.replace(R.id.web_view_fragment, webviewFragment).commit();
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (webviewFragment != null && webviewFragment instanceof BaseFragment) {
            boolean flag = webviewFragment.onKeyDown(keyCode, event);
            if (flag) {
                return flag;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 页面路由
     */
    private final Command titleUpdateCommand = new Command() {
        @Override
        public String name() {
            return Command.COMMAND_UPDATE_TITLE;
        }

        @Override
        public void exec(Context context, Map params, WebviewCallBack resultBack) {
            if (params.containsKey(Command.COMMAND_UPDATE_TITLE_PARAMS_TITLE)) {
                setTitle((String) params.get(Command.COMMAND_UPDATE_TITLE_PARAMS_TITLE));
            }
        }
    };

}