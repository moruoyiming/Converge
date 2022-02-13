package com.example.converge.utils.glide;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.youth.banner.loader.ImageLoaderInterface;

public abstract class ImageLoader implements ImageLoaderInterface<ImageView> {
    public ImageLoader() {
    }

    public ImageView createImageView(Context context) {
        ImageView imageView = new ImageView(context);
        return imageView;
    }
}
