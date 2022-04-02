package com.cocos.base.utils.kotlin

import android.animation.Animator
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.res.Resources
import android.util.Log
import android.view.View
import java.util.logging.Logger

/**
 * Created by wfm on 2019/8/5
 * 通用动画工具类
 */
class AnimationUtil {

    companion object {
        private val instance = AnimationUtil()
        @JvmStatic
        fun getInstance(): AnimationUtil {
            return instance
        }
    }

    /**
     * 动画：右边切入 - 从 右 切入 屏幕
     *
     * @param view     要执行动画的view
     * @param duration 时间（毫秒） Resources.getSystem()
     */
    fun animationRightIn(view: View, duration: Int) {
        val widthPixels = Resources.getSystem().displayMetrics.widthPixels
        val objectAnimator = ObjectAnimator.ofFloat(view, "translationX", widthPixels.toFloat(), 0f)
        objectAnimator.duration = duration.toLong()
        objectAnimator.start()
    }
    /**
     * 动画：右边切入 - 从 右 切入 屏幕
     *
     * @param view     要执行动画的view
     * @param duration 时间（毫秒） Resources.getSystem()
     */
    fun animationRightIn(view: View, duration: Int,value:Float) {
        val widthPixels = Resources.getSystem().displayMetrics.widthPixels
        val objectAnimator = ObjectAnimator.ofFloat(view, "translationX", value, 0f)
        objectAnimator.duration = duration.toLong()
        objectAnimator.start()
    }
    /**
     * 动画：右边切出 - 从 右 滑出 屏幕
     *
     * @param view     要执行动画的view
     * @param duration 时间（毫秒）
     */
    fun animationRightOut(view: View, duration: Int) {
        val widthPixels = Resources.getSystem().displayMetrics.widthPixels
        val objectAnimator = ObjectAnimator.ofFloat(view, "translationX", 0f, widthPixels.toFloat())
        objectAnimator.duration = duration.toLong()
        objectAnimator.start()
    }
    /**
     * 动画：右边切出 - 从 右 滑出 屏幕
     *
     * @param view     要执行动画的view
     * @param duration 时间（毫秒）
     */
    fun animationRightOut(view: View, duration: Int,value:Float) {
        val widthPixels = Resources.getSystem().displayMetrics.widthPixels
        val objectAnimator = ObjectAnimator.ofFloat(view, "translationX", 0f, value)
        objectAnimator.duration = duration.toLong()
        objectAnimator.start()
    }

    /**
     * 动画：左边切入 - 从 左 切入 屏幕
     *
     * @param view     要执行动画的view
     * @param duration 时间（毫秒）
     */
    fun animationLeftIn(view: View, duration: Int) {
        val widthPixels = Resources.getSystem().displayMetrics.widthPixels
        val objectAnimator = ObjectAnimator.ofFloat(view, "translationX", -widthPixels.toFloat(), 0f)
        objectAnimator.duration = duration.toLong()
        objectAnimator.start()
    }
    /**
     * 动画：左边切入 - 从 左 切入 屏幕
     *
     * @param view     要执行动画的view
     * @param duration 时间（毫秒）
     */
    fun animationLeftIn(view: View, duration: Int,value:Float) {
        val widthPixels = Resources.getSystem().displayMetrics.widthPixels
        val objectAnimator = ObjectAnimator.ofFloat(view, "translationX", -widthPixels.toFloat(), value)
        objectAnimator.duration = duration.toLong()
        objectAnimator.start()
    }

    /**
     * 动画：左边退出 - 从 左 滑出 屏幕
     *
     * @param view     要执行动画的view
     * @param duration 时间（毫秒）
     */
    fun animationLeftOut(view: View, duration: Int) {
        val widthPixels = Resources.getSystem().displayMetrics.widthPixels
        val objectAnimator = ObjectAnimator.ofFloat(view, "translationX", 0f, -widthPixels.toFloat())
        objectAnimator.duration = duration.toLong()
        objectAnimator.start()
    }

    /**
     * 动画：底部进入 - 从 下 至 上
     *
     * @param view     要执行动画的view
     * @param duration 时间（毫秒）
     */
    fun bottomIn(view: View, duration: Int) {
        val heightPixels = Resources.getSystem().displayMetrics.heightPixels
        val objectAnimator = ObjectAnimator.ofFloat(view, "translationY", heightPixels.toFloat(), 0f)
        objectAnimator.duration = duration.toLong()
        objectAnimator.start()
    }


    /**
     * 动画：底部消失 - 从 上 至 下
     *
     * @param view     要执行动画的view
     * @param duration 时间（毫秒）
     */
    fun bottomOut(view: View, duration: Int) {
        val heightPixels = Resources.getSystem().displayMetrics.heightPixels
        val objectAnimator = ObjectAnimator.ofFloat(view, "translationY", 0f, heightPixels.toFloat())
        objectAnimator.addListener(object : Animator.AnimatorListener {

            override fun onAnimationRepeat(animation: Animator?) {
            }

            override fun onAnimationEnd(animation: Animator?) {
                view.visibility = View.GONE
            }

            override fun onAnimationCancel(animation: Animator?) {
                view.visibility = View.GONE
            }

            override fun onAnimationStart(animation: Animator?) {
            }
        })
        objectAnimator.duration = duration.toLong()
        objectAnimator.start()
    }

    /**
     * 缩放动画
     *
     * @param view 要执行动画的view
     */
    fun animationScale(view: View) {
        val scaleX = PropertyValuesHolder.ofFloat("scaleX", 0.8f, 1.3f, 1.0f)
        val scaleY = PropertyValuesHolder.ofFloat("scaleY", 0.8f, 1.3f, 1.0f)
        val objectAnimator = ObjectAnimator.ofPropertyValuesHolder(view, scaleX, scaleY)
        objectAnimator.duration = 1000
        objectAnimator.start()
    }

    /**
     * 缩放动画
     *
     * @param view 要执行动画的view
     * @param duration 执行时间（毫秒）
     */
    fun animationScale(view: View, duration: Int) {
        val scaleX = PropertyValuesHolder.ofFloat("scaleX", 0.8f, 1.3f, 1.0f)
        val scaleY = PropertyValuesHolder.ofFloat("scaleY", 0.8f, 1.3f, 1.0f)
        val objectAnimator = ObjectAnimator.ofPropertyValuesHolder(view, scaleX, scaleY)
        objectAnimator.duration = duration.toLong()
        objectAnimator.start()
    }
}