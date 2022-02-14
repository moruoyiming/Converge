package com.example.converge.note.javaadvanced;

import com.cocos.base.activity.MvvmActivity;
import com.cocos.base.viewmodel.MvvmBaseViewModel;
import com.example.converge.R;
import com.example.converge.databinding.ActivityJavabaseBinding;

public class JavaAdvancedActivity extends MvvmActivity<ActivityJavabaseBinding, MvvmBaseViewModel> {

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
        return R.layout.activity_javaheight;
    }

}
