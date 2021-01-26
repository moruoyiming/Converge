package com.example.hotfix.note.ui.recyclerview;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotfix.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {

    private List<Star> starList;
    private RecyclerView recyclerView;
    private StarAdapter starAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        init();
    }

    public void init() {
        starList = new ArrayList<>();
        Star star = null;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 20; j++) {
                star = new Star();
                if (i == 0) {
                    star.setGroudName("东京热");
                } else if (i == 1) {
                    star.setGroudName("一本道");
                } else if (i == 2) {
                    star.setGroudName("fc2");
                } else if (i == 3) {
                    star.setGroudName("东京热");
                }
                star.setName("波多野结衣"+j);
                starList.add(star);
            }
        }
        recyclerView = findViewById(R.id.rv_list);
        starAdapter = new StarAdapter(starList, RecyclerViewActivity.this);
        Log.i("Tokyo","   "+starList.size()+ "   "+starList.toString());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(starAdapter);
        recyclerView.addItemDecoration(new StarItemDecoration(RecyclerViewActivity.this));
    }
}
