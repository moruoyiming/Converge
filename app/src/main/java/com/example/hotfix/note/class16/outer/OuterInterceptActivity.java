package com.example.hotfix.note.class16.outer;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import com.example.hotfix.R;
import com.example.hotfix.note.class16.DataModel;
import com.example.hotfix.note.class16.SubVpAdapter;


public class OuterInterceptActivity extends AppCompatActivity {
    private OuterInterceptSwipeRefreshLayout swipeRefreshLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outer_intercept);
        initView();
        initData();
    }

    private void initView() {
        swipeRefreshLayout = (OuterInterceptSwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
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
