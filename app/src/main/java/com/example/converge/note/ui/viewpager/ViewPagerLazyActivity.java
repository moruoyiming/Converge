package com.example.converge.note.ui.viewpager;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.converge.R;
import com.example.converge.note.ui.viewpager.vp2.Fragment2;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

// ViewPager + Fragment 【使用篇】 == 工作过一年 都知道
// 同学们：关于ViewPager + Fragment 的基本使用，这里就简单过了下了，希望同学们能理解哈
public class ViewPagerLazyActivity extends AppCompatActivity {

    ViewPager mViewPager;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager_lazy);
        mViewPager = findViewById(R.id.viewPager);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(MyFragment.newInstance(1));

        // fragmentList.add(MyFragment.newInstance(2));
        fragmentList.add(Fragment2.newIntance()); // 第五版的改动点

        fragmentList.add(MyFragment.newInstance(3));
        fragmentList.add(MyFragment.newInstance(4));
        fragmentList.add(Fragment5.instance());
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),fragmentList);
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setOnPageChangeListener(viewpagerChangeListener);
    }

    // tab 名称设置，例如： T1, T2, T3, T4, T5
    ViewPager.OnPageChangeListener viewpagerChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) { }

        @Override
        public void onPageSelected(int i) {
            int itemId = R.id.fragment_1;
            switch (i) {
                case 0:
                    itemId = R.id.fragment_1;
                    break;
                case 1:
                    itemId = R.id.fragment_2;
                    break;
                case 2:
                    itemId = R.id.fragment_3;
                    break;
                case 3:
                    itemId = R.id.fragment_4;
                    break;
                case 4:
                    itemId = R.id.fragment_5;
                    break;
            }
            bottomNavigationView.setSelectedItemId(itemId);
        }

        @Override
        public void onPageScrollStateChanged(int i) { }
    };

    // 当点击 tab1 的时候 就会 设置CurrentItem=0，来设置 ViewPager下标操作
    BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.fragment_1:
                    mViewPager.setCurrentItem(0, true);
                    return true;
                case R.id.fragment_2:
                    mViewPager.setCurrentItem(1, true);
                    return true;
                case R.id.fragment_3:
                    mViewPager.setCurrentItem(2, true);
                    return true;
                case R.id.fragment_4:
                    mViewPager.setCurrentItem(3, true);
                    return true;
                case R.id.fragment_5:
                    mViewPager.setCurrentItem(4, true);
                    return true;
            }
            return false;
        }

    };
}
