package com.cocos.base.utils.kotlin

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.cocos.base.R
import jp.wasabeef.glide.transformations.BlurTransformation
import java.io.File

/**
 *
 * @Date: 2022/4/1
 * @Time: 18:09
 * @Author: Jian
 *
 */
class ImageViewExtend {

    fun ImageView.loadImage(imageUrl: String, defaultImage: Int = R.mipmap.icon_platform_article_default_icon, roundingRadius: Int = 1, isCenterCrop: Boolean = false) {
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

    fun ImageView.loadImageCircle(imageUrl: String, defaultImage: Int = R.mipmap.icon_platform_article_default_icon, isCircle: Boolean = true) {
        val newImageUrl = if (imageUrl.startsWith("http")) {
            imageUrl
        } else {
            "https://$imageUrl"
        }
        val requestOptions = RequestOptions()
        val transform = if (isCircle) {
            requestOptions.circleCrop().placeholder(defaultImage)
                .error(defaultImage)
        } else {
            requestOptions.placeholder(defaultImage).error(defaultImage)
        }
        Glide.with(this).load(newImageUrl.trim()).apply(transform).into(this)

    }


    fun ImageView.loadImageCircle(filePath: File, defaultImage: Int = R.mipmap.icon_platform_article_default_icon, isCircle: Boolean = true) {
        val requestOptions = RequestOptions()
        val transform = if (isCircle) {
            requestOptions.circleCrop().placeholder(defaultImage)
                .error(defaultImage)
        } else {
            requestOptions.placeholder(defaultImage).error(defaultImage)
        }
        Glide.with(this).load(filePath).apply(transform).into(this)

    }

    /**
     * 设置不同圆角的图片
     */
    fun ImageView.loadGranularRoundedCornersImage(imageUrl: String, topLeft: Float = 0f, topRight: Float = 0f, bottomRight: Float = 0f, bottomLeft: Float = 0f) {
        val newImageUrl = if (imageUrl.startsWith("http")) {
            imageUrl
        } else {
            "https://$imageUrl"
        }
        val requestOptions = RequestOptions()
        val transform = requestOptions.transform(GranularRoundedCorners(topLeft, topLeft, bottomRight, bottomLeft))
            .error(R.mipmap.icon_platform_article_default_icon)
        Glide.with(this).load(newImageUrl).apply(transform).into(this)
    }

    /**
     * 设置不同圆角的图片 -- 高斯模糊
     */
    fun ImageView.loadGranularRoundedCornersImageVague(imageUrl: String, topLeft: Float = 0f, topRight: Float = 0f, bottomRight: Float = 0f, bottomLeft: Float = 0f) {
        val newImageUrl = if (imageUrl.startsWith("http")) {
            imageUrl
        } else {
            "https://$imageUrl"
        }
        // 圆角和高斯模糊,设置模糊度(在0.0到25.0之间)，默认”25";"4":图片缩放比例,默认“1”
        var multi = MultiTransformation(
            BlurTransformation(25, 1),
            GranularRoundedCorners(topLeft, topLeft, bottomRight, bottomLeft)
        )//设置图片圆角角度

        val requestOptions = RequestOptions.bitmapTransform(multi)
        Glide.with(this).load(newImageUrl).apply(requestOptions).into(this)
    }
    /**
     * 设置相同同圆角的图片 -- 高斯模糊
     */
    fun ImageView.loadImageRoundedCornersImageVague(imageUrl: String, roundingRadius: Int = 1) {
        val newImageUrl = if (imageUrl.startsWith("http")) {
            imageUrl
        } else {
            "https://$imageUrl"
        }
        // 圆角和高斯模糊,设置模糊度(在0.0到25.0之间)，默认”25";"4":图片缩放比例,默认“1”
        var multi = MultiTransformation(
            BlurTransformation(25, 1),
            RoundedCorners(roundingRadius)
        )//设置图片圆角角度

        val requestOptions = RequestOptions.bitmapTransform(multi)
        Glide.with(this).load(newImageUrl).apply(requestOptions).into(this)
    }
}