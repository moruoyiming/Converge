package com.cocos.base.kotlinutils

import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.annotation.DrawableRes
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.cocos.base.R
import jp.wasabeef.glide.transformations.CropTransformation

/**
 * Date: 2020/8/3
 * Time: 3:38 PM
 * author:wanyu
 *
 */


fun ImageView.loadImage(uri: Uri?) {
    Glide.with(this).load(uri).into(this)
}

fun ImageView.loadImage(uri: String?, @DrawableRes holder: Int) {
    Glide.with(this).load(uri).apply(RequestOptions.placeholderOf(holder)).into(this)
}

fun ImageView.loadImage(uri: String?, requestListener: RequestListener<Drawable?>) {
    Glide.with(this).load(uri).listener(requestListener).into(this)
}

fun ImageView.loadImage(uri: String?, width: Int, height: Int) {
    val multi = MultiTransformation(CropTransformation(width, height))
    Glide.with(this).load(uri).apply(RequestOptions.bitmapTransform(multi).dontAnimate()).into(this)
}

fun ImageView.loadImageCenterCrop(uri: String?, @DrawableRes holder: Int? = null) {
    Glide.with(this).load(uri).apply(RequestOptions().dontAnimate().dontTransform().centerCrop().apply {
        if (holder != null) {
            this.placeholder(holder)
        }
    }).into(this)
}

fun ImageView.loadImageRequestOptions(uri: String?, requestOptions: RequestOptions) {
    Glide.with(this).load(uri).apply(requestOptions).into(this)
}

fun ImageView.loadImageRoundingRadius(imageUrl: String, defaultImage: Int = R.mipmap.icon_face_sad, roundingRadius: Int = 1, isCenterCrop: Boolean = false) {
    val newImageUrl = if (imageUrl.startsWith("http")) {
        imageUrl
    } else {
        "https://$imageUrl"
    }
    val requestOptions = RequestOptions()
    var transform = if (isCenterCrop) {
        requestOptions.centerCrop().transform(RoundedCorners(roundingRadius))
                .error(defaultImage)
    } else {
        requestOptions.transform(RoundedCorners(roundingRadius))
                .error(defaultImage)
    }
    Glide.with(this).load(newImageUrl.trim()).apply(transform).into(this)

}
//fun ImageView.loadGif(uri: String?, requestListener: RequestListener<GifDrawable?>? = null, centerCrop: Boolean? = null, @DrawableRes holder: Int? = null) {
//    var requestOptions = RequestOptions().dontTransform()
//    if (centerCrop != null) {
//        requestOptions = requestOptions.centerCrop()
//    }
//    if (holder != null) {
//        requestOptions = requestOptions.placeholder(holder)
//    }
//    if (requestListener != null) {
//        Glide.with(this).asGif().load(uri).apply(requestOptions).listener(requestListener).into(this)
//    } else {
//        Glide.with(this).asGif().load(uri).apply(requestOptions).into(this)
//    }
//}

//    fun ImageView.loadGifMark(uri: String?, holder: String?, mark: Int) {
//        Glide.with(this).asGif().load(uri).apply(RequestOptions().dontTransform().signature(StringSignature("$uri$mark")).apply {
//            if (holder != null) {
//                this.placeholder(holder.toDrawable())
//            }
//        }).into(this)
//    }
//
//    fun ImageView.loadGifMark(uri: String?, mark: Int) {
//        Glide.with(this).asGif().load(uri).apply(RequestOptions().dontTransform().signature(StringSignature("$uri$mark")))
//                .listener(object : RequestListener<GifDrawable> {
//                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<GifDrawable>?, isFirstResource: Boolean): Boolean {
//                        return true
//                    }
//
//                    override fun onResourceReady(resource: GifDrawable?, model: Any?, target: Target<GifDrawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
//                        setImageDrawable(resource)
//                        return true
//                    }
//                })
//                .submit(layoutParams.width, layoutParams.height)
//    }
//
//    fun ImageView.loadImageMark(uri: String?, holder: String?, mark: Int) {
//        Glide.with(this).load(uri).apply(RequestOptions().dontAnimate()
//                .signature(StringSignature("$uri$mark")).apply {
//                    if (holder != null) {
//                        this.placeholder(holder.toDrawable())
//                    }
//                }).into(this)
//    }
//
//    fun ImageView.loadImageMark(uri: String?, mark: Int) {
//        Glide.with(this).load(uri).apply(RequestOptions().dontAnimate()
//                .signature(StringSignature("$uri$mark")))
//                .listener(object : RequestListener<Drawable> {
//                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
//                        return true
//                    }
//
//                    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
//                        setImageDrawable(resource)
//                        return true
//                    }
//                })
//                .submit(layoutParams.width, layoutParams.height)
//    }
//
//    fun ImageView.loadLongImageMark(uri: String?, holder: String?, mark: Int) {
//        Glide.with(this).load(uri).apply(RequestOptions.bitmapTransform(CropTransformation(0, layoutParams.height, CropTransformation.CropType.TOP))
//                .dontAnimate()
//                .signature(StringSignature("$uri$mark")).apply {
//                    if (holder != null) {
//                        this.placeholder(holder.toDrawable())
//                    }
//                }).into(this)
//    }
//
//    fun ImageView.loadLongImageMark(uri: String?, mark: Int) {
//        Glide.with(this).load(uri).apply(RequestOptions.bitmapTransform(CropTransformation(0, layoutParams.height, CropTransformation.CropType.TOP))
//                .dontAnimate()
//                .signature(StringSignature("$uri$mark"))).listener(object : RequestListener<Drawable> {
//            override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
//                return true
//            }
//
//            override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
//                setImageDrawable(resource)
//                return true
//            }
//        }).submit(layoutParams.width, layoutParams.height)
//    }
//
//    fun ImageView.loadVideoMark(uri: String?, holder: String?, mark: Int) {
//        Glide.with(this).load(uri).apply(RequestOptions().frame(0)
//                .signature(StringSignature("$uri$mark"))
//                .centerCrop().dontAnimate().apply {
//                    if (holder != null) {
//                        this.placeholder(holder.toDrawable())
//                    }
//                }
//        ).into(this)
//    }
//
//    fun ImageView.loadVideoMark(uri: String?, mark: Int) {
//        Glide.with(this).load(uri).apply(RequestOptions().frame(0)
//                .signature(StringSignature("$uri$mark"))
//                .centerCrop().dontAnimate()).listener(object : RequestListener<Drawable> {
//            override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
//                return true
//            }
//
//            override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
//                setImageDrawable(resource)
//                return true
//            }
//        }).submit(layoutParams.width, layoutParams.height)
//    }

fun ImageView.loadVideo(uri: String, @DrawableRes holder: Int) {
    Glide.with(this).load(uri).apply(RequestOptions().frame(0)
            .centerCrop().placeholder(holder).dontAnimate()).into(this)
}

fun ImageView.loadSticker(uri: String?, type: String?) {
    uri?.let {
        when (type) {
            "GIF" -> {
                loadGif(uri)
            }
            else -> loadImage(uri)
        }
    }
}

//    fun ImageView.loadBase64(uri: ByteArray?, width: Int, height: Int, mark: Int) {
//        val multi = MultiTransformation(CropTransformation(width, height))
//        Glide.with(this).load(uri)
//                .apply(RequestOptions().centerCrop()
//                        .transform(multi).signature(StringSignature("$uri$mark"))
//                        .dontAnimate()).into(this)
//    }

fun ImageView.loadCircleImage(uri: String?, @DrawableRes holder: Int? = null) {
    if (uri.isNullOrBlank()) {
        if (holder != null) {
            setImageResource(holder)
        }
    } else if (holder == null) {
        Glide.with(this).load(uri).apply(RequestOptions().circleCrop()).into(this)
    } else {
        Glide.with(this).load(uri).apply(RequestOptions().placeholder(holder).circleCrop()).into(this)
    }
}