package com.example.converge.note.androidbasics;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.cocos.base.activity.MvvmActivity;
import com.cocos.base.autoservice.IWebViewService;
import com.cocos.base.autoservice.XiangxueServiceLoader;
import com.cocos.base.viewmodel.MvvmBaseViewModel;
import com.cocos.basewebview.WebViewActivity;
import com.cocos.socket.client.sdk.client.action.ISocketActionListener;
import com.example.converge.R;
import com.example.converge.activity.PermissionActivity;
import com.example.converge.databinding.ActivityAndroidbaseBinding;
import com.example.converge.note.androidadvanced.AndroidAdvancedActivity;
import com.example.converge.note.androidbasics.activitylifecycle.AActivity;
import com.example.converge.note.androidbasics.activitylifecycle.DemoActivity;
import com.example.converge.note.androidbasics.douyin.DouyinActivity;
import com.example.converge.note.androidbasics.socket.SocketDemoActivity;
import com.example.converge.note.androidbasics.ui.drawtext.MainActivity;
import com.example.converge.note.androidbasics.inject.InjectActivity;
import com.example.converge.note.androidbasics.inject.User;
import com.example.converge.note.androidbasics.inject.retrofitdemo.RetrofitActivity;
import com.example.converge.note.androidbasics.ui.nestedscroll.ScrollActivity;
import com.example.converge.note.androidbasics.ui.recyclerview.RecyclerViewActivity;
import com.example.converge.note.androidbasics.ui.slideview.SlideCardActivity;
import com.example.converge.note.androidbasics.ui.viewpager.ViewPagerLazyActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AndroidbasicsActivity extends MvvmActivity<ActivityAndroidbaseBinding,MvvmBaseViewModel> implements View.OnClickListener {
    @Override
    protected void onRetryBtnClick() {

    }

    @Override
    protected MvvmBaseViewModel getViewModel() {
        return null;
    }

    @Override
    public int getBindingVariable() {
        viewDataBinding.activitylifecycle.setOnClickListener(this);
        viewDataBinding.testService.setOnClickListener(this);
        viewDataBinding.testHandler.setOnClickListener(this);
        viewDataBinding.openWeb1.setOnClickListener(this);
        viewDataBinding.openWeb2.setOnClickListener(this);
        viewDataBinding.network.setOnClickListener(this);
        viewDataBinding.recyclerview.setOnClickListener(this);
        viewDataBinding.recyclerview2.setOnClickListener(this);
        viewDataBinding.viewpager.setOnClickListener(this);
        viewDataBinding.scroll.setOnClickListener(this);
        viewDataBinding.lf.setOnClickListener(this);
        viewDataBinding.annotions.setOnClickListener(this);
        viewDataBinding.views.setOnClickListener(this);
        viewDataBinding.drawtext.setOnClickListener(this);
        viewDataBinding.socket.setOnClickListener(this);
        viewDataBinding.douyin.setOnClickListener(this);
        return 0;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_androidbase;
    }

    boolean change = false;
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activitylifecycle:
                Intent aIntent = new Intent(AndroidbasicsActivity.this, AActivity.class);
                startActivity(aIntent);
                break;
            case R.id.testService:
                Intent sIntent = new Intent(AndroidbasicsActivity.this, TestServiceActivity.class);
                startActivity(sIntent);
                break;
            case R.id.testHandler:
                Intent hIntent = new Intent(AndroidbasicsActivity.this, TestHandlerActivity.class);
                startActivity(hIntent);
                break;
            case R.id.openWeb1:
//                WebviewActivity.startCommonWeb(getActivity(), "腾讯网", "https://xw.qq.com/?f=qqcom");
                IWebViewService webviewService = XiangxueServiceLoader.load(IWebViewService.class);
                if (webviewService != null) {
                    webviewService.startWebViewActivity(AndroidbasicsActivity.this, "https://www.baidu.com", "百度", false);
                }
                break;
            case R.id.openWeb2:
                WebViewActivity.startCommonWeb(AndroidbasicsActivity.this, "AIDL测试", "file:///android_asset/" + "aidl.html");
                break;
            case R.id.network:
                Intent intent = new Intent(AndroidbasicsActivity.this, NetworkActivity.class);
                startActivity(intent);
                break;
            case R.id.lf:
                Intent intent2 = new Intent(AndroidbasicsActivity.this, RetrofitActivity.class);
                startActivity(intent2);
                break;

            case R.id.annotions:
                List<User> users = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    User user = new User();
                    user.setName("张三" + i);
                    user.setSex("男");
                    users.add(user);
                }
                Intent intent1 = new Intent(AndroidbasicsActivity.this, InjectActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("name", "jianruilin");
                bundle.putString("pwd", "123456");
                intent1.putExtra("user", (Serializable) users);
                intent1.putExtra("users", (Serializable) new User[]{ new User("刘老师","女")});
                intent1.putExtras(bundle);
                startActivity(intent1);
                break;
            case R.id.scroll:
                Intent scroll = new Intent(AndroidbasicsActivity.this, ScrollActivity.class);
                startActivity(scroll);
                break;
            case R.id.recyclerview:
                Intent recyclerview = new Intent(AndroidbasicsActivity.this, RecyclerViewActivity.class);
                startActivity(recyclerview);
                break;

            case R.id.recyclerview2:
                Intent recyclerview2 = new Intent(AndroidbasicsActivity.this, SlideCardActivity.class);
                startActivity(recyclerview2);
                break;
            case R.id.views:
                Intent view = new Intent(AndroidbasicsActivity.this, DemoActivity.class);
                startActivity(view);
                break;
            case R.id.viewpager:
                Intent viewpager = new Intent(AndroidbasicsActivity.this, ViewPagerLazyActivity.class);
                startActivity(viewpager);
                break;
            case R.id.socket:
                Intent socket = new Intent(AndroidbasicsActivity.this, SocketDemoActivity.class);
                startActivity(socket);
                break;
            case R.id.drawtext:
                Intent dispatch = new Intent(AndroidbasicsActivity.this, MainActivity.class);
                startActivity(dispatch);
                break;
            case R.id.douyin:
                Intent douyin = new Intent(AndroidbasicsActivity.this, DouyinActivity.class);
                startActivity(douyin);
                break;

        }
    }
}
