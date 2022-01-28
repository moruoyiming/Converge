package com.cocos.base.kotlinutils

import android.content.Context
import android.view.Gravity
import android.widget.Toast


/**
 *
 * author huanghuajuan
 * created at 2019/8/5 15:01
 * Toast 工具类
 */
class ToastUtil {

    companion object {
        private val instant = ToastUtil()

        @JvmStatic
        fun getInstance(): ToastUtil {
            return instant
        }
    }

    var toast: Toast? = null//实现不管我们触发多少次Toast调用，都只会持续一次Toast显示的时长

    /**
     * 默认 短时间显示Toast【居下】
     * @param msg 显示的内容-字符串
     */
    fun showTextToast(context: Context, msg: String) {
        if (context.applicationContext != null) {
            try {
                toast = Toast.makeText(context.applicationContext, msg, Toast.LENGTH_SHORT)
                toast?.setText(msg)
                //1、setGravity方法必须放到这里，否则会出现toast始终按照第一次显示的位置进行显示
                //  （比如第一次是在底部显示，那么即使设置setGravity在中间，也不管用）
                //2、虽然默认是在底部显示，但是，因为这个工具类实现了中间显示，所以需要还原，还原方式如下：
                toast?.setGravity(Gravity.BOTTOM, 0, dip2px(context.applicationContext, 64f))
                toast?.show()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    /**
     * 短时间显示Toast【居中】
     * @param msg 显示的内容-字符串
     */
    fun showShortToastCenter(context: Context, msg: String) {
        if (context.applicationContext != null) {
            try {
                toast = Toast.makeText(context.applicationContext, msg, Toast.LENGTH_SHORT)
                toast?.setText(msg)
                toast?.setGravity(Gravity.CENTER, 0, 0)
                toast?.show()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    /**
     * 短时间显示Toast【居上】
     * @param msg 显示的内容-字符串
     */
    fun showShortToastTop(context: Context, msg: String) {
        if (context.applicationContext != null) {
            try {
                toast = Toast.makeText(context.applicationContext, msg, Toast.LENGTH_SHORT)
                toast?.setText(msg)
                toast?.setGravity(Gravity.TOP, 0, 0)
                toast?.show()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    /**
     * 长时间显示Toast【居下】
     * @param msg 显示的内容-字符串
     */
    fun showLongToast(context: Context, msg: String) {
        if (context.applicationContext != null) {
            try {
                toast = Toast.makeText(context.applicationContext, msg, Toast.LENGTH_LONG)
                toast?.setText(msg)
                toast?.setGravity(Gravity.BOTTOM, 0, dip2px(context.applicationContext, 64f))
                toast?.show()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    /**
     * 长时间显示Toast【居中】
     * @param msg 显示的内容-字符串
     */
    fun showLongToastCenter(context: Context, msg: String) {
        if (context.applicationContext != null) {
            try {
                toast =  Toast.makeText(context.applicationContext, msg, Toast.LENGTH_LONG)
                toast?.setText(msg)
                toast?.setGravity(Gravity.CENTER, 0, 0)
                toast?.show()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    /**
     * 长时间显示Toast【居上】
     * @param msg 显示的内容-字符串
     */
    fun showLongToastTop(context: Context, msg: String) {
        if (context.applicationContext != null) {
            try {
                toast = toast ?: Toast.makeText(context.applicationContext, msg, Toast.LENGTH_LONG)
                toast?.setText(msg)
                toast?.setGravity(Gravity.TOP, 0, 0)
                toast?.show()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun dip2px(context: Context, dpValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }
}