package com.cocos.base.kotlinutils

import android.graphics.Color
import android.graphics.drawable.GradientDrawable

/**
 * Created by sgl on 2021/8/20.
 * shape 工具类
 */
class ShapeSettingUtils {

    companion object {
        private val instance = ShapeSettingUtils()

        @JvmStatic
        fun getInstance(): ShapeSettingUtils {
            return instance
        }
    }

    /**
     * 设置shape
     *
     * @param radius 半径长度
     * @param fillColor 填充颜色
     * @param storeWidth 线条宽度
     * @param strokeColor 线条颜色
     * @return
     */
    fun getDrawableFill(radius: Float, fillColor: String ="#00FFFFFF",  storeWidth: Int = 0, strokeColor: String="#00FFFFFF"): GradientDrawable? {
        val gradientDrawable = GradientDrawable()
        gradientDrawable.setCornerRadius(radius.toFloat())
        gradientDrawable.setColor(Color.parseColor(fillColor))
        gradientDrawable.setStroke(storeWidth, Color.parseColor(strokeColor))
        return gradientDrawable
    }

    /**
     * 设置shape 渐变色
     *
     * @param radius 半径长度
     * @param fillColorStart 填充颜色
     * @param fillColorEnd 填充颜色
     * @param storeWidth 线条宽度
     * @param strokeColor 线条颜色
     * @return
     */
    fun getDrawableGradient(radius: Float, fillColorStart: String="#00FFFFFF", fillColorEnd: String="#00FFFFFF", storeWidth: Int = 0, strokeColor: String="#00FFFFFF"): GradientDrawable? {
        val gradientDrawable = GradientDrawable()
        gradientDrawable.apply {
            this.cornerRadius = radius // 指定圆角
            this.setStroke(storeWidth, Color.parseColor(strokeColor)) // 指定边框
            this.gradientType = GradientDrawable.LINEAR_GRADIENT // 指定渲染角度
            this.colors = intArrayOf(Color.parseColor(fillColorStart), Color.parseColor(fillColorEnd)) // 指定渐变色
        }
        return gradientDrawable
    }
}
