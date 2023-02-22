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

import com.jzyc.instock.bean.NewBean;
import com.jzyc.instock.databinding.FragmentStrollBinding;

import java.util.List;

/**
 * 首页TAB 闲逛
 */
public class StrollFragment extends Fragment {

    private StrollViewModel strollViewModel;
    private FragmentStrollBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        strollViewModel = new ViewModelProvider(this).get(StrollViewModel.class);
        binding = FragmentStrollBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final RecyclerView recyclerView = binding.strollRecycler;
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