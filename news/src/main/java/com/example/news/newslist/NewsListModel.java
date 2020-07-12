package com.example.news.newslist;


import com.cocos.base.customview.BaseCustomViewModel;
import com.cocos.base.model.MvvmBaseModel;
import com.cocos.network.TecentNetworkApi;
import com.cocos.network.observer.BaseObserver;
import com.example.common.views.picturetitleview.PictureTitleViewViewModel;
import com.example.common.views.titleview.TitleViewViewModel;
import com.example.news.api.NewsApiInterface;
import com.example.news.api.NewsListBean;

import java.util.ArrayList;

public class NewsListModel extends MvvmBaseModel<NewsListBean, ArrayList<BaseCustomViewModel>> {
    private String mChannelId = "";
    private String mChannelName = "";

    public NewsListModel(String channelId, String channelName) {
        super(NewsListBean.class, true, "pref_key_news_" + channelId, null, 1);
        mChannelId = channelId;
        mChannelName = channelName;
    }

    @Override
    public void refresh() {
        isRefresh = true;
        load();
    }

    public void loadNexPage() {
        isRefresh = false;
        load();
    }

    @Override
    protected void load() {
        TecentNetworkApi.getService(NewsApiInterface.class)
                .getNewsList(mChannelId, mChannelName, String.valueOf(isRefresh ? 1 : pageNumber))
                .compose(TecentNetworkApi.
                        getInstance().
                        applySchedulers(new BaseObserver<NewsListBean>(this, this)));
    }

    @Override
    public void onSuccess(NewsListBean newsListBean, boolean isFromCache) {
        ArrayList<BaseCustomViewModel> baseViewModels = new ArrayList<>();
        for (NewsListBean.Contentlist source : newsListBean.showapiResBody.pagebean.contentlist) {
            if (source.imageurls != null && source.imageurls.size() > 1) {
                PictureTitleViewViewModel viewModel = new PictureTitleViewViewModel();
                viewModel.avatarUrl = source.imageurls.get(0).url;
                viewModel.jumpUri = source.link;
                viewModel.title = source.title;
                baseViewModels.add(viewModel);
            } else {
                TitleViewViewModel viewModel = new TitleViewViewModel("xxxx");
                viewModel.jumpUri = source.link;
                viewModel.title = source.title;
                baseViewModels.add(viewModel);
            }
        }
        loadSuccess(newsListBean, baseViewModels, isFromCache);
    }

    @Override
    public void onFailure(Throwable e) {
        e.printStackTrace();
        loadFail(e.getMessage());
    }
}
