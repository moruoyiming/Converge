package com.example.converge.note.androidheight.jetpack.lifecycle.model;




import com.example.converge.R;
import com.example.converge.note.androidheight.jetpack.lifecycle.bean.Goods;
import com.example.converge.note.androidheight.jetpack.lifecycle.databus.LiveDataBus;

import java.util.ArrayList;
import java.util.List;

public class GoodsModel implements IGoodsModel {
    @Override
    public void loadGoodsData(OnLoadListener onLoadListener) {
        onLoadListener.onComplete(getData());
    }

    private List<Goods> getData() {
        ArrayList data = new ArrayList<>();
        //这里的数据来源于网络或数据库或其它地方
        data.add(new Goods(R.drawable.s1, "一星", "****"));
        data.add(new Goods(R.drawable.s2, "一星", "****"));
        data.add(new Goods(R.drawable.s3, "一星", "****"));
        data.add(new Goods(R.drawable.s4, "一星", "****"));
        data.add(new Goods(R.drawable.s5, "一星", "****"));
        data.add(new Goods(R.drawable.s6, "一星", "****"));
        data.add(new Goods(R.drawable.s7, "一星", "****"));
        data.add(new Goods(R.drawable.s8, "一星", "****"));
        data.add(new Goods(R.drawable.s9, "一星", "****"));

        //发送消息
        LiveDataBus.getInstance().with("list", ArrayList.class).postValue(data);

        return data;
    }

}
