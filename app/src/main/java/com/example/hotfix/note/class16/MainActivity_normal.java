package com.example.hotfix.note.class16;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import com.example.hotfix.R;


public class MainActivity_normal extends AppCompatActivity {
    private SwipeRefreshLayout swipeRefreshLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_srl_vp);
        initView();
        initData();
    }

    private void initView() {
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
    }

    private void initData() {
        viewPager.setAdapter(new SubVpAdapter(getSupportFragmentManager(), DataModel.getFragmentList1()));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}
