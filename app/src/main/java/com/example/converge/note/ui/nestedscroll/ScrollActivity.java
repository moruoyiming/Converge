package com.example.converge.note.ui.nestedscroll;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.converge.R;
import com.example.converge.note.ui.nestedscroll.nestedscroll.a_scrollview_recyclerview.NestedViewPagerActivityTest1;
import com.example.converge.note.ui.nestedscroll.nestedscroll.b_nestedscrollview_recyclerview.NestedViewPagerActivityTest2;
import com.example.converge.note.ui.nestedscroll.nestedscroll.c_fixedheight_viewpager_nestedscrollview_recyclerview.NestedViewPagerActivityTest3;
import com.example.converge.note.ui.nestedscroll.nestedscroll.e_perfect_nestedscroll.NestedViewPagerActivity;
import com.example.converge.note.androidbasics.dispatch.inner.InnerInterceptActivity;
import com.example.converge.note.androidbasics.dispatch.outer.OuterInterceptActivity;

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
        }else if(v.getId() == R.id.inner_intercept){
            Intent intent = new Intent(this, InnerInterceptActivity.class);
            startActivity(intent);
        }else if(v.getId() == R.id.outer_intercept){
            Intent intent = new Intent(this, OuterInterceptActivity.class);
            startActivity(intent);
        }
    }
}
