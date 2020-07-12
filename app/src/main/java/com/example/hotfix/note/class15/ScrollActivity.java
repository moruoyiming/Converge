package com.example.hotfix.note.class15;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.hotfix.R;
import com.example.hotfix.note.class15.nestedscroll.a_scrollview_recyclerview.NestedViewPagerActivityTest1;
import com.example.hotfix.note.class15.nestedscroll.b_nestedscrollview_recyclerview.NestedViewPagerActivityTest2;
import com.example.hotfix.note.class15.nestedscroll.c_fixedheight_viewpager_nestedscrollview_recyclerview.NestedViewPagerActivityTest3;
import com.example.hotfix.note.class15.nestedscroll.e_perfect_nestedscroll.NestedViewPagerActivity;

public class ScrollActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);
    }

    public void onButtonClick(View v) {
        if (v.getId() == R.id.nested_scroll_test1) {
            Intent intent = new Intent(this, NestedViewPagerActivityTest1.class);
            startActivity(intent);
        }
        if (v.getId() == R.id.nested_scroll_test2) {
            Intent intent = new Intent(this, NestedViewPagerActivityTest2.class);
            startActivity(intent);
        }
        if (v.getId() == R.id.nested_scroll_test3) {
            Intent intent = new Intent(this, NestedViewPagerActivityTest3.class);
            startActivity(intent);
        } else if (v.getId() == R.id.nested_scroll) {
            Intent intent = new Intent(this, NestedViewPagerActivity.class);
            startActivity(intent);
        }
    }
}
