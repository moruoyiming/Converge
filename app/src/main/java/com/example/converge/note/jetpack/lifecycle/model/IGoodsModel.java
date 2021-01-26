package com.example.converge.note.jetpack.lifecycle.model;

import com.example.converge.note.jetpack.lifecycle.bean.Goods;

import java.util.List;

public interface IGoodsModel {
    void loadGoodsData(OnLoadListener onLoadListener);
    interface OnLoadListener{
        void onComplete(List<Goods> goods);
        void onError(String msg);
    }
}









