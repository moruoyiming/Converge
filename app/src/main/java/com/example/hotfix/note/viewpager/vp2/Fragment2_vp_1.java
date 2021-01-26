package com.example.hotfix.note.viewpager.vp2;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.hotfix.R;
import com.example.hotfix.note.viewpager.FragmentDelegater;
import com.example.hotfix.note.viewpager.base.LazyFragment5;

// 同学们：这是T2  嵌套了一层 ViewPager的Fragment2_vp_1
public class Fragment2_vp_1 extends LazyFragment5 {

    private static final String TAG = "Fragment2_vp_1";

    public static Fragment newIntance() {
        Fragment2_vp_1 fragment = new Fragment2_vp_1();
        fragment.setFragmentDelegater(new FragmentDelegater(fragment));
        return fragment;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_vp_1;
    }

    @Override
    protected void initView(View view) {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onFragmentLoadStop() {
        super.onFragmentLoadStop();
        Log.d(TAG, "onFragmentLoadStop" + " 停止一切更新");
    }

    @Override
    public void onFragmentLoad() {
        super.onFragmentLoad();
        Log.d(TAG, "onFragmentLoad" + " 真正更新数据");
    }
}
