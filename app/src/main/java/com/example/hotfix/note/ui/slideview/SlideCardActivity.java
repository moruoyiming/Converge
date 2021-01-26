package com.example.hotfix.note.ui.slideview;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hotfix.R;
import com.example.hotfix.note.ui.slideview.adapter.UniversalAdapter;
import com.example.hotfix.note.ui.slideview.adapter.ViewHolder;

import java.util.List;

public class SlideCardActivity extends AppCompatActivity {

    private List<SlideCardBean> mDatas;
    private RecyclerView recyclerView;
    private UniversalAdapter starAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        init();
    }

    public void init() {
        recyclerView = findViewById(R.id.rv_list);
        mDatas = SlideCardBean.initDatas();
        starAdapter = new UniversalAdapter<SlideCardBean>(this,mDatas, R.layout.item_swipe_card) {

            @Override
            public void convert(ViewHolder viewHolder, SlideCardBean slideCardBean) {
                viewHolder.setText(R.id.tvName, slideCardBean.getName());
                viewHolder.setText(R.id.tvPrecent, slideCardBean.getPostition() + "/" + mDatas.size());
                Glide.with(SlideCardActivity.this)
                        .load(slideCardBean.getUrl())
                        .into((ImageView) viewHolder.getView(R.id.iv));
            }
        };
        Log.i("Tokyo", "   " + mDatas.size() + "   " + mDatas.toString());
        recyclerView.setLayoutManager(new SlideCardLayoutManager());
        recyclerView.setAdapter(starAdapter);
        // 初始化数据
        CardConfig.initConfig(this);

        SlideCallback slideCallback = new SlideCallback(recyclerView, starAdapter, mDatas);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(slideCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }
}
