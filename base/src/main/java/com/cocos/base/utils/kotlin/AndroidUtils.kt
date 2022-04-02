package com.cocos.base.utils.kotlin

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.res.Resources
import android.graphics.Point
import android.view.Display
import android.view.WindowManager

/**
 * Created by hanbin on 2019/8/20.
 * Android系统工具类
 */
class AndroidUtils {

    companion object {
        private val instance = AndroidUtils()

        @JvmStatic
        fun getInstance(): AndroidUtils {
            return instance
        }
    }

    /**
     * 获取手机屏幕宽度
     *
     * @return 屏幕的宽度
     */
    fun getScreenWidth(): Int {
        return Resources.getSystem().displayMetrics.widthPixels
    }

    /**
     * 获取手机屏幕高度
     *
     * @return 屏幕的高度
     */
    fun getScreenHeight(): Int {
        return Resources.getSystem().displayMetrics.heightPixels
    }

    /**
     * 获取手机屏幕高度
     *
     * @return 屏幕的高度
     */
    fun getScreenHeight(context: Context): Int {
        val windowManager: WindowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display: Display = windowManager.defaultDisplay
        val point = Point()
        display.getRealSize(point)
        return if (point.x < point.y) {
            point.y
        } else {
            point.x
        }
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    fun dp2px(dpValue: Float): Int {

        val scale = Resources.getSystem().displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    fun px2dp(pxValue: Float): Int {
        val scale = Resources.getSystem().displayMetrics.density
        return (pxValue / scale + 0.5f).toInt()
    }

    /**
     * 获取状态栏的高度
     *
     * @return 状态栏的高度
     */
    fun getStatusBarHeight(): Int {
        var result = 0
        val resourceId = Resources.getSystem().getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = Resources.getSystem().getDimensionPixelSize(resourceId)
        }
        return result
    }

    fun putTextIntoClip(mContext :Context , textView: String){
        val clipboardManager = mContext.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText(null, textView)//创建ClipData对象
        clipboardManager.primaryClip = clipData //添加ClipData对象到剪切板中
        if (clipboardManager.hasPrimaryClip()) {
            clipboardManager.primaryClip.getItemAt(0).text
        }
    }

    /**
     * 获取应用程序名称
     *
     * @param context 上下文
     * @return 应用的版本号
     */
    fun getAppName(context: Context): String? {
        try {
            val packageManager = context.packageManager
            val packageInfo = packageManager.getPackageInfo(
                    context.packageName, 0)
            val labelRes = packageInfo.applicationInfo.labelRes
            return context.resources.getString(labelRes)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return "考满分"
    }
}
