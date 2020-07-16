package com.example.hotfix.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.cocos.basewebview.WebviewActivity;
import com.example.hotfix.R;
import com.example.hotfix.activity.NetworkActivity;
import com.example.hotfix.activity.PermissionActivity;
import com.example.hotfix.databinding.FragmentCategoryBinding;
import com.example.hotfix.note.class01.DemoActivity;
import com.example.hotfix.note.class02.InjectActivity;
import com.example.hotfix.note.class02.User;
import com.example.hotfix.note.class02.retrofitdemo.RetrofitActivity;
import com.example.hotfix.note.class15.ScrollActivity;
import com.example.hotfix.note.class17.MainActivity;
import com.example.hotfix.note.class18.FishActivity;
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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.openWeb1:
                WebviewActivity.startCommonWeb(getActivity(), "腾讯网", "https://xw.qq.com/?f=qqcom");
                break;
            case R.id.openWeb2:
                WebviewActivity.startCommonWeb(getActivity(), "AIDL测试", "file:///android_asset/" + "aidl.html");
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
        }
    }

    boolean change = false;
}
