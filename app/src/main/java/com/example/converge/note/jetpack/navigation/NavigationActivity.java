package com.example.converge.note.jetpack.navigation;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.converge.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class NavigationActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        findViewById(R.id.action_page1);
        bottomNavigationView=findViewById(R.id.nav_view);
//      NavController controller=Navigation.findNavController(this,R.id.my_nav_host_fragment);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.my_nav_host_fragment);
        NavController controller = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(bottomNavigationView, controller);
        //官网代码
//        val finalHost = NavHostFragment.create(R.navigation.nav_graph_main)
//        supportFragmentManager.beginTransaction()
//                .replace(R.id.ll_fragment_navigation, finalHost)
//                .setPrimaryNavigationFragment(finalHost)
//                .commit();
    }


    //    @Override
//    public boolean onSupportNavigateUp() {
//        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.my_nav_host_fragment);
//        return NavHostFragment.findNavController(fragment).navigateUp();
//    }
}
