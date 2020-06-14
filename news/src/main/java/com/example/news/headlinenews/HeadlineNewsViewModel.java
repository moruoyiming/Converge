package com.example.news.headlinenews;


import com.cocos.base.viewmodel.MvvmBaseViewModel;


public class HeadlineNewsViewModel extends MvvmBaseViewModel<ChannelsModel, ChannelsModel.Channel> {
    public HeadlineNewsViewModel(){
        model = new ChannelsModel();
        model.register(this);
        model.getCachedDataAndLoad();
    }
}
