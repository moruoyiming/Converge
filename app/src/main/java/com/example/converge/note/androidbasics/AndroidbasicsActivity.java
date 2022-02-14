package com.example.converge.note.androidbasics;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.cocos.base.activity.MvvmActivity;
import com.cocos.base.autoservice.IWebViewService;
import com.cocos.base.autoservice.XiangxueServiceLoader;
import com.cocos.base.viewmodel.MvvmBaseViewModel;
import com.cocos.basewebview.WebViewActivity;
import com.example.converge.R;
import com.example.converge.activity.PermissionActivity;
import com.example.converge.note.NetworkActivity;
import com.example.converge.note.androidbasics.activitylifecycle.DemoActivity;
import com.example.converge.note.androidbasics.douyin.DouyinActivity;
import com.example.converge.note.androidbasics.opengl.OpenGLActivity;
import com.example.converge.note.androidbasics.ui.drawtext.MainActivity;
import com.example.converge.note.androidbasics.ui.fish.FishActivity;
import com.example.converge.note.androidbasics.ui.nestedscroll.ScrollActivity;
import com.example.converge.note.androidbasics.ui.recyclerview.RecyclerViewActivity;
import com.example.converge.note.androidbasics.ui.slideview.SlideCardActivity;
import com.example.converge.note.androidbasics.ui.viewpager.ViewPagerLazyActivity;
import com.example.converge.note.androidheight.frame.dagger2.FirstActivity;
import com.example.converge.note.androidheight.frame.dagger3.DaggerActivity;
import com.example.converge.note.androidheight.framework.binder.MMAPActivity;
import com.example.converge.note.androidheight.framework.binder.demo.client.ClientActivity;
import com.example.converge.note.androidheight.jetpack.databinding.DataBindingActivity;
import com.example.converge.note.androidheight.jetpack.lifecycle.LifeCycleActivity;
import com.example.converge.note.androidheight.jetpack.livedata.LiveDataActivity;
import com.example.converge.note.androidheight.jetpack.navigation.NavigationActivity;
import com.example.converge.note.androidheight.jetpack.paging.PagingActivity;
import com.example.converge.note.androidheight.jetpack.room.RoomActivity;
import com.example.converge.note.androidheight.jetpack.room.ViewModelActivity;
import com.example.converge.note.androidheight.jetpack.workmanager.WorkManagerActivity;
import com.example.converge.note.androidheight.ndk.NdkActivity;
import com.example.converge.note.androidheight.plugin.PluginTestActivity;
import com.example.converge.note.javabasics.JavaBaseActivity;
import com.example.converge.note.javabasics.inject.InjectActivity;
import com.example.converge.note.javabasics.inject.User;
import com.example.converge.note.javabasics.inject.retrofitdemo.RetrofitActivity;
import com.example.converge.note.javaheight.JavaHeightActivity;
import com.example.converge.utils.Utils;
import com.example.dexdiff.DexdiffActivity;
import com.example.skin.SkinManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AndroidbasicsActivity extends MvvmActivity implements View.OnClickListener {
    @Override
    protected void onRetryBtnClick() {

    }

    @Override
    protected MvvmBaseViewModel getViewModel() {
        return null;
    }

    @Override
    public int getBindingVariable() {
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
            case R.id.permission:
                Intent per = new Intent(AndroidbasicsActivity.this, PermissionActivity.class);
                startActivity(per);
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
                intent1.putExtras(bundle);
                startActivity(intent1);
                break;
            case R.id.views:
                Intent view = new Intent(AndroidbasicsActivity.this, DemoActivity.class);
                startActivity(view);
                break;
            case R.id.skin:
                if (!change) {
                    change = true;
                    SkinManager.getInstance().loadSkin("/data/data/com.example.hotfix/skin/skins-debug.apk");
                } else {
                    change = false;
                    SkinManager.getInstance().loadSkin(null);
                }
                break;
            case R.id.scroll:
                Intent scroll = new Intent(AndroidbasicsActivity.this, ScrollActivity.class);
                startActivity(scroll);
                break;
            case R.id.drawtext:
                Intent dispatch = new Intent(AndroidbasicsActivity.this, MainActivity.class);
                startActivity(dispatch);
                break;
            case R.id.fish:
                Intent fish = new Intent(AndroidbasicsActivity.this, FishActivity.class);
                startActivity(fish);
                break;

            case R.id.recyclerview:
                Intent recyclerview = new Intent(AndroidbasicsActivity.this, RecyclerViewActivity.class);
                startActivity(recyclerview);
                break;

            case R.id.recyclerview2:
                Intent recyclerview2 = new Intent(AndroidbasicsActivity.this, SlideCardActivity.class);
                startActivity(recyclerview2);
                break;
            case R.id.viewpager:
                Intent viewpager = new Intent(AndroidbasicsActivity.this, ViewPagerLazyActivity.class);
                startActivity(viewpager);
                break;
            case R.id.mmap:
                Intent mmap = new Intent(AndroidbasicsActivity.this, MMAPActivity.class);
                startActivity(mmap);
                break;
            case R.id.aidl:
                Intent aidl = new Intent(AndroidbasicsActivity.this, ClientActivity.class);
                startActivity(aidl);
                break;
            case R.id.dexdiff:
                Intent dexdiff = new Intent(AndroidbasicsActivity.this, DexdiffActivity.class);
                startActivity(dexdiff);
                break;
            case R.id.socket:
                Intent socket = new Intent(AndroidbasicsActivity.this, com.example.converge.note.androidbasics.socket.DemoActivity.class);
                startActivity(socket);
                break;
            case R.id.plugin:
                Intent plugin = new Intent(AndroidbasicsActivity.this, PluginTestActivity.class);
                startActivity(plugin);
                break;
            case R.id.hotfix:
                Utils.what();
                break;
            case R.id.dagger2:
                Intent dagger2 = new Intent(AndroidbasicsActivity.this, FirstActivity.class);
                startActivity(dagger2);
                break;
            case R.id.dagger3:
                Intent dagger3 = new Intent(AndroidbasicsActivity.this, DaggerActivity.class);
                startActivity(dagger3);
                break;
            case R.id.lifecycle:
                Intent lifecycle = new Intent(AndroidbasicsActivity.this, LifeCycleActivity.class);
                startActivity(lifecycle);
                break;
            case R.id.livedata:
                Intent livedata = new Intent(AndroidbasicsActivity.this, LiveDataActivity.class);
                startActivity(livedata);
                break;
            case R.id.databinding:
                Intent databinding = new Intent(AndroidbasicsActivity.this, DataBindingActivity.class);
                startActivity(databinding);
                break;
            case R.id.room:
                Intent room = new Intent(AndroidbasicsActivity.this, RoomActivity.class);
                startActivity(room);
                break;
            case R.id.viewmodel:
                Intent viewmodel = new Intent(AndroidbasicsActivity.this, ViewModelActivity.class);
                startActivity(viewmodel);
                break;
            case R.id.navigation:
                Intent navigation = new Intent(AndroidbasicsActivity.this, NavigationActivity.class);
                startActivity(navigation);
                break;
            case R.id.paging:
                Intent paging = new Intent(AndroidbasicsActivity.this, PagingActivity.class);
                startActivity(paging);
                break;
            case R.id.workmanager:
                Intent workmanager = new Intent(AndroidbasicsActivity.this, WorkManagerActivity.class);
                startActivity(workmanager);
                break;
            case R.id.opengl:
                Intent opengl = new Intent(AndroidbasicsActivity.this, OpenGLActivity.class);
                startActivity(opengl);
                break;
            case R.id.ndk:
                Intent ndk = new Intent(AndroidbasicsActivity.this, NdkActivity.class);
                startActivity(ndk);
                break;
            case R.id.douyin:
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent douyin = new Intent(AndroidbasicsActivity.this, DouyinActivity.class);
                startActivity(douyin);
                break;
            case R.id.shenmin:
                Intent aIntent = new Intent(AndroidbasicsActivity.this, AActivity.class);
                startActivity(aIntent);
                break;
        }
    }
}
