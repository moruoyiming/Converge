package com.example.converge.note.androidbasics.ui.viewpager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.converge.R;
import com.example.converge.note.androidbasics.ui.viewpager.base.LazyFragment5;

public class Fragment5 extends LazyFragment5 {
    private static final String TAG = /*"Fragment5"*/ MyFragment.class.getSimpleName();

    Button btn;

    public static Fragment5 instance() {
        Fragment5 fragment5 = new Fragment5();
        fragment5.setFragmentDelegater(new FragmentDelegater(fragment5));
        return  fragment5;
    }
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
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    protected void initView(View rootView) {
        btn = rootView.findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Main2Activity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment5;
    }

    @Override
    public void onFragmentLoadStop() {
        super.onFragmentLoadStop();
        Log.d(TAG, "onFragmentLoadStop: fragment 5" + " 停止一切更新");
    }

    @Override
    public void onFragmentLoad() {
        super.onFragmentLoad();
        Log.d(TAG, "onFragmentLoad: fragment 5" + " 真正更新数据");
    }
}
