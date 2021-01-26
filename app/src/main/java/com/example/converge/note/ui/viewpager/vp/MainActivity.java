package com.example.converge.note.ui.viewpager.vp;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.converge.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

// ViewPager + Fragment 【使用篇】 == 工作过一年 都知道
// 同学们：关于ViewPager + Fragment 的基本使用，这里就简单过了下了，希望同学们能理解哈
public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager; // v4
    private BottomNavigationView navigationView; // Tab 5个

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager_lazy);

        viewPager = findViewById(R.id.viewPager);
        navigationView = findViewById(R.id.bottomNavigationView);
        navigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);

        // ViewPage Adapter 绑定
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), getShowData());
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(8); // 0没有效果,内部会给你赋值1
        viewPager.setOnPageChangeListener(viewpagerChangeListener);
    }

    // 展示五个Fragment
    private final List<Fragment> getShowData() {
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(MyFragment.newInstance(1));
        fragmentList.add(MyFragment.newInstance(2));
        fragmentList.add(MyFragment.newInstance(3));
        fragmentList.add(MyFragment.newInstance(4));
        fragmentList.add(MyFragment.newInstance(5));
        return fragmentList;
    }

    // tab 名称设置，例如： T1, T2, T3, T4, T5
    private ViewPager.OnPageChangeListener viewpagerChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageScrolled(int i, float v, int i1) {}

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
            navigationView.setSelectedItemId(itemId);
        }

        @Override
        public void onPageScrollStateChanged(int i) {}
    };

    // 当点击 tab1 的时候 就会 设置CurrentItem=0，来设置 ViewPager下标操作
    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.fragment_1:
                    viewPager.setCurrentItem(0, true);
                    return true;
                case R.id.fragment_2:
                    viewPager.setCurrentItem(1, true);
                    return true;
                case R.id.fragment_3:
                    viewPager.setCurrentItem(2, true);
                    return true;
                case R.id.fragment_4:
                    viewPager.setCurrentItem(3, true);
                    return true;
                case R.id.fragment_5:
                    viewPager.setCurrentItem(4, true);
                    return true;
            }
            return false;
        }

    };
}
