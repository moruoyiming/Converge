package com.example.converge.note.ui.viewpager.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.converge.note.ui.viewpager.FragmentDelegater;


/**
 * BaseFragment
 *  TODO 第一版
 */
public abstract class LazyFragment1 extends Fragment {

    FragmentDelegater mFragmentDelegater;
    private View rootView = null;
    private boolean isViewCreated = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        E("onCreateView: ");
        if (rootView == null) {
            rootView = inflater.inflate(getLayoutRes(),container, false);
        }
        isViewCreated  = true; // TODO 解决奔溃1.1
        initView(rootView); // 初始化控件 findvxxx
        return rootView;
    }

    // TODO 判断 Fragment 是否可见 【第一版 1.1】
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        E("setUserVisibleHint");

        if (isViewCreated) {

            if (isVisibleToUser) {
                dispatchUserVisibleHint(true);
            } else {
                dispatchUserVisibleHint(false);
            }

        }
    }

    // TODO 分发 可见 不可见 的动作 【第一版 1.2】
    private void dispatchUserVisibleHint(boolean visibleState) {
        if (visibleState) {
            // 加载数据
            onFragmentLoad();
        } else {
            // 停止一切操作
            onFragmentLoadStop();
        }
    }












    // 让子类完成，初始化布局，初始化控件
    protected abstract void initView(View rootView);
    protected abstract int getLayoutRes();

    // -->>>停止网络数据请求
    public void onFragmentLoadStop() {
        E("onFragmentLoadStop");
    }

    // -->>>加载网络数据请求
    public void onFragmentLoad() {
        E("onFragmentLoad");
    }

    @Override
    public void onResume() {
        super.onResume();
        E("onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        E("onPause");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        E("onDestroyView");
    }

    // 工具相关而已
    public void setFragmentDelegater(FragmentDelegater fragmentDelegater) {
        mFragmentDelegater = fragmentDelegater;
    }
    private void E(String  string) {
        if (mFragmentDelegater != null) {
            mFragmentDelegater.dumpLifeCycle(string);
        }
    }
}
