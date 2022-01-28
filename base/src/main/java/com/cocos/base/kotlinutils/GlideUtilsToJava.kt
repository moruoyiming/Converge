package com.cocos.base.kotlinutils

import android.content.Context
import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.annotation.DrawableRes
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.cocos.base.BaseApplication
import jp.wasabeef.glide.transformations.CropTransformation

/**
 * Date: 2020/8/3
 * Time: 3:38 PM
 * author:wanyu
 *
 */
class GlideUtilsToJava {

   var context: Context = BaseApplication.getAppContext()
    companion object {
        private val instant = GlideUtilsToJava()
        @JvmStatic
        fun getInstance(): GlideUtilsToJava {
            return instant
        }
    }

    fun loadImage(imageView: ImageView, uri: String?) {
        Glide.with(context).load(uri).into(imageView)
    }

    fun loadImage(imageView: ImageView,uri: Uri?) {
        Glide.with(context).load(uri).into(imageView)
    }

    fun loadImage(imageView: ImageView,uri: String?, requestOptions : RequestOptions) {
        Glide.with(context).load(uri).apply(requestOptions).into(imageView)
    }

    fun loadImage(imageView: ImageView,uri: String?, @DrawableRes holder: Int) {
        Glide.with(context).load(uri).apply(RequestOptions.placeholderOf(holder)).into(imageView)
    }

    fun loadImage(imageView: ImageView,uri: String?, requestListener: RequestListener<Drawable?>) {
        Glide.with(context).load(uri).listener(requestListener).into(imageView)
    }

    fun loadImage(imageView: ImageView,uri: String?, width: Int, height: Int) {
        val multi = MultiTransformation(CropTransformation(width, height))
        Glide.with(context).load(uri).apply(RequestOptions.bitmapTransform(multi).dontAnimate()).into(imageView)
    }

    fun loadImageCenterCrop(imageView: ImageView,uri: String?, @DrawableRes holder: Int? = null) {
        Glide.with(context).load(uri).apply(RequestOptions().dontAnimate().dontTransform().centerCrop().apply {
            if (holder != null) {
                this.placeholder(holder)
            }
        }).into(imageView)
    }

    fun loadGif(imageView: ImageView,uri: String?, requestListener: RequestListener<GifDrawable?>? = null, centerCrop: Boolean? = null, @DrawableRes holder: Int? = null) {
        var requestOptions = RequestOptions().dontTransform()
        if (centerCrop != null) {
            requestOptions = requestOptions.centerCrop()
        }
        if (holder != null) {
            requestOptions = requestOptions.placeholder(holder)
        }
        if (requestListener != null) {
            Glide.with(context).asGif().load(uri).apply(requestOptions).listener(requestListener).into(imageView)
        } else {
            Glide.with(context).asGif().load(uri).apply(requestOptions).into(imageView)
        }
    }


    fun loadCircleImage(imageView: ImageView,uri: String?, @DrawableRes holder: Int? = null) {
        if (uri.isNullOrBlank()) {
            if (holder != null) {
                imageView.setImageResource(holder)
            }
        } else if (holder == null) {
            Glide.with(context).load(uri).apply(RequestOptions().circleCrop()).into(imageView)
        } else {
            Glide.with(context).load(uri).apply(RequestOptions().placeholder(holder).circleCrop()).into(imageView)
        }
    }
}