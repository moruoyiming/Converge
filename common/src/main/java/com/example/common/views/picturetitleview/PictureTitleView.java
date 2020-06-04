package com.example.common.views.picturetitleview;

import android.content.Context;
import android.view.View;

import com.cocos.base.customview.BaseCustomView;
import com.cocos.basewebview.WebviewActivity;
import com.example.common.R;
import com.example.common.databinding.PictureTitleViewBinding;


public class PictureTitleView extends BaseCustomView<PictureTitleViewBinding, PictureTitleViewViewModel> {
    public PictureTitleView(Context context) {
        super(context);
    }

    @Override
    public int setViewLayoutId() {
        return R.layout.picture_title_view;
    }

    @Override
    public void setDataToView(PictureTitleViewViewModel data) {
        getDataBinding().setViewModel(data);
    }

    @Override
    public void onRootClick(View view) {
        WebviewActivity.startCommonWeb(view.getContext(), "", getViewModel().jumpUri);
    }
}
