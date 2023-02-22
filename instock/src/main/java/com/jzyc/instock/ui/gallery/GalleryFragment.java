package com.jzyc.instock.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.jzyc.instock.databinding.FragmentGalleryBinding;
import com.jzyc.instock.utils.MeasUtils;
import com.jzyc.instock.widget.greedolayout.GreedoLayoutManager;
import com.jzyc.instock.widget.greedolayout.GreedoSpacingItemDecoration;
import com.jzyc.instock.widget.greedolayout.PhotosAdapter;

/**
 * 首页TAB 图库
 */
public class GalleryFragment extends Fragment {

    private GalleryViewModel dashboardViewModel;
    private FragmentGalleryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel = new ViewModelProvider(this).get(GalleryViewModel.class);
        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        PhotosAdapter photosAdapter = new PhotosAdapter(getContext());
        final GreedoLayoutManager layoutManager = new GreedoLayoutManager(photosAdapter);
        layoutManager.setMaxRowHeight(MeasUtils.dpToPx(150, getContext()));
        RecyclerView recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(photosAdapter);
        int spacing = MeasUtils.dpToPx(4, getContext());
        recyclerView.addItemDecoration(new GreedoSpacingItemDecoration(spacing));
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}