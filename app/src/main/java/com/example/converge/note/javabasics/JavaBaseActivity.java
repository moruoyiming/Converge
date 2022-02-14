package com.example.converge.note.javabasics;


import android.view.View;

import com.cocos.base.activity.MvvmActivity;
import com.cocos.base.utils.ToastUtil;
import com.cocos.base.viewmodel.MvvmBaseViewModel;
import com.example.converge.R;
import com.example.converge.databinding.ActivityJavabaseBinding;

public class JavaBaseActivity extends MvvmActivity<ActivityJavabaseBinding,MvvmBaseViewModel> implements View.OnClickListener {
    @Override
    protected void onRetryBtnClick() {

    }

    @Override
    protected MvvmBaseViewModel getViewModel() {
        return null;
    }

    @Override
    public int getBindingVariable() {
        viewDataBinding.javageneric.setOnClickListener(this);
        return 0;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_javabase;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.javageneric:
                ToastUtil.show("toast show");
                break;
        }
    }
}
