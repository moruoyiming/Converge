package com.example.news.newslist;


import com.cocos.base.customview.BaseCustomViewModel;
import com.cocos.base.viewmodel.MvvmBaseViewModel;

public class NewsListViewModel extends MvvmBaseViewModel<NewsListModel, BaseCustomViewModel> {
    public NewsListViewModel init(String classId, String lboClassId) {
        model = new NewsListModel(classId, lboClassId);
        model.register(this);
        model.getCachedDataAndLoad();
        return this;
    }

    public void tryToLoadNextPage() {
        model.loadNexPage();
    }
}
