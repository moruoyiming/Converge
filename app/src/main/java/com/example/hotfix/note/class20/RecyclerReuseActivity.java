package com.example.hotfix.note.class20;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotfix.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerReuseActivity extends AppCompatActivity {

    private List<Star> starList;
    private RecyclerView recyclerView;
    private RecyclerReuse starAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        init();
    }

    public void init() {
        starList = new ArrayList<>();
        Star star = null;
        for (int j = 0; j < 1000; j++) {
            star = new Star();
            star.setGroudName("东京热");
            star.setName("" + j);
            starList.add(star);
        }
        recyclerView = findViewById(R.id.rv_list);
        starAdapter = new RecyclerReuse(starList, RecyclerReuseActivity.this);
        Log.i("Tokyo", "   " + starList.size() + "   " + starList.toString());
        recyclerView.setLayoutManager(new GridLayoutManager(RecyclerReuseActivity.this,1));
        recyclerView.setAdapter(starAdapter);
    }
}
