package com.jzyc.instock.ui.stroll;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.jzyc.instock.bean.NewBean;
import com.jzyc.instock.databinding.FragmentStrollBinding;
import com.jzyc.instock.ui.adapter.StrollPageAdapter;
import com.jzyc.instock.ui.gallery.GalleryFragment;
import com.jzyc.instock.ui.home.HomeFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页TAB 闲逛
 */
public class StrollFragment extends Fragment {
    private StrollPageAdapter adapter;
    private StrollViewModel strollViewModel;
    private FragmentStrollBinding binding;
    private List<Fragment> fragments;
    private String[] titles = new String[]{"欧美电影","日韩电影"};

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        strollViewModel = new ViewModelProvider(this).get(StrollViewModel.class);
        binding = FragmentStrollBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new GalleryFragment());
        adapter = new StrollPageAdapter(requireActivity(), fragments);
        binding.tablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        binding.viewpager.setAdapter(adapter);
        new TabLayoutMediator(binding.tablayout, binding.viewpager, (tab, position) -> tab.setText(titles[position]));
        binding.viewpager.setOffscreenPageLimit(2);
        strollViewModel.getLiveData().observe(getViewLifecycleOwner(), new Observer<List<NewBean>>() {
            @Override
            public void onChanged(@Nullable List<NewBean> data) {

            }
        });
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        strollViewModel.getStrollData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}