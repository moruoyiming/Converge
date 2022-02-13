package com.example.converge.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.cocos.base.autoservice.XiangxueServiceLoader;
import com.cocos.basewebview.WebViewActivity;
import com.cocos.base.autoservice.IWebViewService;
import com.example.converge.note.androidbasics.AActivity;
import com.example.converge.note.androidbasics.douyin.DouyinActivity;
import com.example.converge.note.javabasics.JavaBaseActivity;
import com.example.converge.note.javaheight.JavaHeightActivity;
import com.example.converge.note.androidheight.ndk.NdkActivity;
import com.example.converge.note.androidbasics.opengl.OpenGLActivity;
import com.example.dexdiff.DexdiffActivity;
import com.example.converge.R;
import com.example.converge.note.NetworkActivity;
import com.example.converge.activity.PermissionActivity;
import com.example.converge.databinding.FragmentCategoryBinding;
import com.example.converge.note.androidheight.framework.binder.MMAPActivity;
import com.example.converge.note.androidheight.framework.binder.demo.client.ClientActivity;
import com.example.converge.note.androidbasics.activitylifecycle.DemoActivity;
import com.example.converge.note.javabasics.inject.InjectActivity;
import com.example.converge.note.javabasics.inject.User;
import com.example.converge.note.javabasics.inject.retrofitdemo.RetrofitActivity;
import com.example.converge.note.androidbasics.ui.nestedscroll.ScrollActivity;
import com.example.converge.note.androidbasics.ui.drawtext.MainActivity;
import com.example.converge.note.androidbasics.ui.fish.FishActivity;
import com.example.converge.note.androidbasics.ui.recyclerview.RecyclerViewActivity;
import com.example.converge.note.androidbasics.ui.slideview.SlideCardActivity;
import com.example.converge.note.androidbasics.ui.viewpager.ViewPagerLazyActivity;
import com.example.converge.note.androidheight.frame.dagger2.FirstActivity;
import com.example.converge.note.androidheight.frame.dagger3.DaggerActivity;
import com.example.converge.note.androidheight.jetpack.databinding.DataBindingActivity;
import com.example.converge.note.androidheight.jetpack.lifecycle.LifeCycleActivity;
import com.example.converge.note.androidheight.jetpack.livedata.LiveDataActivity;
import com.example.converge.note.androidheight.jetpack.navigation.NavigationActivity;
import com.example.converge.note.androidheight.jetpack.paging.PagingActivity;
import com.example.converge.note.androidheight.jetpack.room.RoomActivity;
import com.example.converge.note.androidheight.jetpack.room.ViewModelActivity;
import com.example.converge.note.androidheight.jetpack.workmanager.WorkManagerActivity;
import com.example.converge.note.androidheight.plugin.PluginTestActivity;
import com.example.converge.utils.Utils;
import com.example.skin.SkinManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class CategoryFragment extends Fragment implements View.OnClickListener {
    FragmentCategoryBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_category, container, false);
//        mBinding.homeTxtTitle.setText(getString(R.string.menu_categories));
        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mBinding.javabase.setOnClickListener(this);
        mBinding.javaheight.setOnClickListener(this);
        mBinding.androidbase.setOnClickListener(this);
        mBinding.androidheight.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.javabase:
                Intent javabase = new Intent(getActivity(), JavaBaseActivity.class);
                startActivity(javabase);
                break;
            case R.id.javaheight:
                Intent javaheight = new Intent(getActivity(), JavaHeightActivity.class);
                startActivity(javaheight);
                break;
            case R.id.androidbase:
                Intent androidbase = new Intent(getActivity(), JavaBaseActivity.class);
                startActivity(androidbase);
                break;
            case R.id.androidheight:
                Intent androidheight = new Intent(getActivity(), JavaBaseActivity.class);
                startActivity(androidheight);
                break;
            case R.id.openWeb1:
//                WebviewActivity.startCommonWeb(getActivity(), "腾讯网", "https://xw.qq.com/?f=qqcom");
                IWebViewService webviewService = XiangxueServiceLoader.load(IWebViewService.class);
                if (webviewService != null) {
                    webviewService.startWebViewActivity(getActivity(), "https://www.baidu.com", "百度", false);
                }
                break;
            case R.id.openWeb2:
                WebViewActivity.startCommonWeb(getActivity(), "AIDL测试", "file:///android_asset/" + "aidl.html");
                break;
            case R.id.network:
                Intent intent = new Intent(getActivity(), NetworkActivity.class);
                startActivity(intent);
                break;
            case R.id.lf:
                Intent intent2 = new Intent(getActivity(), RetrofitActivity.class);
                startActivity(intent2);
                break;
            case R.id.permission:
                Intent per = new Intent(getActivity(), PermissionActivity.class);
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
                Intent intent1 = new Intent(getActivity(), InjectActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("name", "jianruilin");
                bundle.putString("pwd", "123456");
                intent1.putExtra("user", (Serializable) users);
                intent1.putExtras(bundle);
                startActivity(intent1);
                break;
            case R.id.views:
                Intent view = new Intent(getActivity(), DemoActivity.class);
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
                Intent scroll = new Intent(getActivity(), ScrollActivity.class);
                startActivity(scroll);
                break;
            case R.id.drawtext:
                Intent dispatch = new Intent(getActivity(), MainActivity.class);
                startActivity(dispatch);
                break;
            case R.id.fish:
                Intent fish = new Intent(getActivity(), FishActivity.class);
                startActivity(fish);
                break;

            case R.id.recyclerview:
                Intent recyclerview = new Intent(getActivity(), RecyclerViewActivity.class);
                startActivity(recyclerview);
                break;

            case R.id.recyclerview2:
                Intent recyclerview2 = new Intent(getActivity(), SlideCardActivity.class);
                startActivity(recyclerview2);
                break;
            case R.id.viewpager:
                Intent viewpager = new Intent(getActivity(), ViewPagerLazyActivity.class);
                startActivity(viewpager);
                break;
            case R.id.mmap:
                Intent mmap = new Intent(getActivity(), MMAPActivity.class);
                startActivity(mmap);
                break;
            case R.id.aidl:
                Intent aidl = new Intent(getActivity(), ClientActivity.class);
                startActivity(aidl);
                break;
            case R.id.dexdiff:
                Intent dexdiff = new Intent(getActivity(), DexdiffActivity.class);
                startActivity(dexdiff);
                break;
            case R.id.socket:
                Intent socket = new Intent(getActivity(), com.example.converge.note.androidbasics.socket.DemoActivity.class);
                startActivity(socket);
                break;
            case R.id.plugin:
                Intent plugin = new Intent(getActivity(), PluginTestActivity.class);
                startActivity(plugin);
                break;
            case R.id.hotfix:
                Utils.what();
                break;
            case R.id.dagger2:
                Intent dagger2 = new Intent(getActivity(), FirstActivity.class);
                startActivity(dagger2);
                break;
            case R.id.dagger3:
                Intent dagger3 = new Intent(getActivity(), DaggerActivity.class);
                startActivity(dagger3);
                break;
            case R.id.lifecycle:
                Intent lifecycle = new Intent(getActivity(), LifeCycleActivity.class);
                startActivity(lifecycle);
                break;
            case R.id.livedata:
                Intent livedata = new Intent(getActivity(), LiveDataActivity.class);
                startActivity(livedata);
                break;
            case R.id.databinding:
                Intent databinding = new Intent(getActivity(), DataBindingActivity.class);
                startActivity(databinding);
                break;
            case R.id.room:
                Intent room = new Intent(getActivity(), RoomActivity.class);
                startActivity(room);
                break;
            case R.id.viewmodel:
                Intent viewmodel = new Intent(getActivity(), ViewModelActivity.class);
                startActivity(viewmodel);
                break;
            case R.id.navigation:
                Intent navigation = new Intent(getActivity(), NavigationActivity.class);
                startActivity(navigation);
                break;
            case R.id.paging:
                Intent paging = new Intent(getActivity(), PagingActivity.class);
                startActivity(paging);
                break;
            case R.id.workmanager:
                Intent workmanager = new Intent(getActivity(), WorkManagerActivity.class);
                startActivity(workmanager);
                break;
            case R.id.opengl:
                Intent opengl = new Intent(getActivity(), OpenGLActivity.class);
                startActivity(opengl);
                break;
            case R.id.ndk:
                Intent ndk = new Intent(getActivity(), NdkActivity.class);
                startActivity(ndk);
                break;
            case R.id.douyin:
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent douyin = new Intent(getActivity(), DouyinActivity.class);
                startActivity(douyin);
                break;
            case R.id.shenmin:
                Intent aIntent = new Intent(getActivity(), AActivity.class);
                startActivity(aIntent);
                break;
        }
    }

    boolean change = false;
}
