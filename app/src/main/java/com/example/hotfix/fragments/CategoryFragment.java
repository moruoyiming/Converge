package com.example.hotfix.fragments;

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
import com.example.dexdiff.DexdiffActivity;
import com.example.hotfix.R;
import com.example.hotfix.activity.NetworkActivity;
import com.example.hotfix.activity.PermissionActivity;
import com.example.hotfix.databinding.FragmentCategoryBinding;
import com.example.hotfix.note.binder.MMAPActivity;
import com.example.hotfix.note.binder.demo.client.ClientActivity;
import com.example.hotfix.note.class01.DemoActivity;
import com.example.hotfix.note.class02.InjectActivity;
import com.example.hotfix.note.class02.User;
import com.example.hotfix.note.class02.retrofitdemo.RetrofitActivity;
import com.example.hotfix.note.class15.ScrollActivity;
import com.example.hotfix.note.class17.MainActivity;
import com.example.hotfix.note.class18.FishActivity;
import com.example.hotfix.note.class19.RecyclerViewActivity;
import com.example.hotfix.note.class20.SlideCardActivity;
import com.example.hotfix.note.class21.ViewPagerLazyActivity;
import com.example.hotfix.note.dagger2.FirstActivity;
import com.example.hotfix.note.dagger3.DaggerActivity;
import com.example.hotfix.note.jetpack.databinding.DataBindingActivity;
import com.example.hotfix.note.jetpack.lifecycle.LifeCycleActivity;
import com.example.hotfix.note.jetpack.livedata.LiveDataActivity;
import com.example.hotfix.note.jetpack.navigation.NavigationActivity;
import com.example.hotfix.note.jetpack.paging.PagingActivity;
import com.example.hotfix.note.jetpack.room.RoomActivity;
import com.example.hotfix.note.jetpack.room.ViewModelActivity;
import com.example.hotfix.note.jetpack.workmanager.WorkManagerActivity;
import com.example.hotfix.note.plugin.PluginTestActivity;
import com.example.hotfix.utils.Hotfix;
import com.example.hotfix.utils.Utils;
import com.example.skin.SkinManager;

import java.io.File;
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
        mBinding.openWeb1.setOnClickListener(this);
        mBinding.openWeb2.setOnClickListener(this);
        mBinding.network.setOnClickListener(this);
        mBinding.lf.setOnClickListener(this);
        mBinding.permission.setOnClickListener(this);
        mBinding.annotions.setOnClickListener(this);
        mBinding.views.setOnClickListener(this);
        mBinding.skin.setOnClickListener(this);
        mBinding.scroll.setOnClickListener(this);
        mBinding.drawtext.setOnClickListener(this);
        mBinding.fish.setOnClickListener(this);
        mBinding.recyclerview.setOnClickListener(this);
        mBinding.recyclerview2.setOnClickListener(this);
        mBinding.viewpager.setOnClickListener(this);
        mBinding.mmap.setOnClickListener(this);
        mBinding.aidl.setOnClickListener(this);
        mBinding.dexdiff.setOnClickListener(this);
        mBinding.socket.setOnClickListener(this);
        mBinding.plugin.setOnClickListener(this);
        mBinding.hotfix.setOnClickListener(this);
        mBinding.dagger2.setOnClickListener(this);
        mBinding.dagger3.setOnClickListener(this);
        mBinding.lifecycle.setOnClickListener(this);
        mBinding.livedata.setOnClickListener(this);
        mBinding.databinding.setOnClickListener(this);
        mBinding.room.setOnClickListener(this);
        mBinding.viewmodel.setOnClickListener(this);
        mBinding.navigation.setOnClickListener(this);
        mBinding.paging.setOnClickListener(this);
        mBinding.workmanager.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
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
                Intent socket = new Intent(getActivity(), com.example.hotfix.activity.socket.DemoActivity.class);
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
        }
    }

    boolean change = false;
}
