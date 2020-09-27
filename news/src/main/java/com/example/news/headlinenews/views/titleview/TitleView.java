package com.example.news.headlinenews.views.titleview;

import android.content.Context;
import android.view.View;

import com.cocos.base.customview.BaseCustomView;
import com.cocos.basewebview.WebViewActivity;
import com.example.news.R;
import com.example.news.databinding.TitleViewBinding;


public class TitleView extends BaseCustomView<TitleViewBinding, TitleViewViewModel> {
    public TitleView(Context context) {
        super(context);
    }

    @Override
    public int setViewLayoutId() {
        return R.layout.title_view;
    }

    @Override
    public void setDataToView(TitleViewViewModel data) {
        getDataBinding().setViewModel(data);
    }

    @Override
    public void onRootClick(View view) {
        WebViewActivity.startCommonWeb(view.getContext(), "", getViewModel().jumpUri);
    }
}
