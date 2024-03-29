package com.example.converge.note.androidbasics.ui.viewpager.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.converge.note.androidbasics.ui.viewpager.FragmentDelegater;

/**
 * BaseFragment
 *  TODO 第四版
 */
public abstract class LazyFragment4 extends Fragment {

    FragmentDelegater mFragmentDelegater;
    private View rootView = null;
    private boolean isViewCreated = false; // View加载了
    private boolean isVisibleStateUP = false; // 记录上一次可见的状态

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        E("onCreateView: ");
        if (rootView == null) {
            rootView = inflater.inflate(getLayoutRes(),container, false);
        }
        isViewCreated  = true; // 解决奔溃1.1
        initView(rootView); // 初始化控件 findvxxx

        // 解决第一次一直初始化loading一直显示的问题 【第二版2.1】
        if (getUserVisibleHint()) {
            // 手动来分发下
            setUserVisibleHint(true);
        }

        return rootView;
    }

    // 判断 Fragment 是否可见 【第一版 1.1】
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        E("setUserVisibleHint");

        if (isViewCreated) {

            // 记录上一次可见的状态: && isVisibleStateUP

            if (isVisibleToUser && !isVisibleStateUP) {
                dispatchUserVisibleHint(true);
            } else if (!isVisibleToUser && isVisibleStateUP){
                dispatchUserVisibleHint(false);
            }

        }
    }

    // 分发 可见 不可见 的动作 【第一版 1.2】
    private void dispatchUserVisibleHint(boolean visibleState) {

        // 记录上一次可见的状态 实时更新状态
        this.isVisibleStateUP = visibleState;

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

        // TODO 不可见 到 可见 变化过程  说明可见
        if (getUserVisibleHint() && !isVisibleStateUP) {
            dispatchUserVisibleHint(true);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        E("onPause");

        // TODO 可见 到 不可见  变化过程  说明 不可见
        if (getUserVisibleHint() && isVisibleStateUP) {
            dispatchUserVisibleHint(false);
        }
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
