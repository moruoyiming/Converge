package com.example.converge.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.converge.note.androidbasics.AndroidbasicsActivity;
import com.example.converge.note.javabasics.JavaBaseActivity;
import com.example.converge.note.javaadvanced.JavaAdvancedActivity;
import com.example.converge.R;
import com.example.converge.databinding.FragmentCategoryBinding;


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
                Intent javaheight = new Intent(getActivity(), JavaAdvancedActivity.class);
                startActivity(javaheight);
                break;
            case R.id.androidbase:
                Intent androidbase = new Intent(getActivity(), AndroidbasicsActivity.class);
                startActivity(androidbase);
                break;
            case R.id.androidheight:
                Intent androidheight = new Intent(getActivity(), JavaBaseActivity.class);
                startActivity(androidheight);
                break;
        }
    }

    boolean change = false;
}
