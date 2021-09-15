package com.example.converge.fragments;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.shehuan.niv.NiceImageView;

public class GlideImageLoader extends ImageLoader {

    @Override
    public void displayImage(Context context, Object path, ImageView niceImageView) {

       Glide.with(context).load((String)path).apply(new RequestOptions().transform(new RoundedCorners(30))).into(niceImageView);

    }
}