package com.example.converge.utils.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CenterInside;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

public class GlideImageLoader extends ImageLoader {

    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {

        RequestOptions options = new RequestOptions();
        options.transform(buildRoundCornerTransformation(imageView, 20));
        Glide.with(imageView.getContext()).load((String)path).apply(options).into(imageView);
    }

    /**
     * Glide 圆角问题
     * @param imageView
     * @param cornerRadius
     * @return
     */
    private static MultiTransformation buildRoundCornerTransformation(ImageView imageView, int cornerRadius) {
        switch (imageView.getScaleType()) {
            case CENTER_INSIDE:
                return new MultiTransformation<Bitmap>(new CenterInside(), new RoundedCorners(cornerRadius));
            case FIT_CENTER:
            case FIT_START:
            case FIT_END:
            case FIT_XY:
                return new MultiTransformation<Bitmap>(new FitCenter(), new RoundedCorners(cornerRadius));
            case CENTER_CROP:
            default:
                return new MultiTransformation<Bitmap>(new CenterCrop(), new RoundedCorners(cornerRadius));
        }
    }
}