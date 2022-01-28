package com.cocos.base.kotlinutils

import android.app.ActivityManager
import android.content.Context

/**
 * Date: 2019-11-25
 * Time: 18:32
 * author:wanyu
 *
 */
class SystemMemoryUtils {

    companion object {
        private val instance = SystemMemoryUtils()
        @JvmStatic
        fun getInstance(): SystemMemoryUtils {
            return instance
        }
    }

    //定义GB的计算常量
    private val GB = 1024 * 1024 * 1024
    //定义MB的计算常量
    private val MB = 1024 * 1024
    //定义KB的计算常量
    private val KB = 1024

    /**
     *   * 获取android当前可用运行内存大小
     *   * @param context
     *   *
     */
//    fun getAvailMemory(context: Context): String {
//        val am = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
//        val mi = ActivityManager.MemoryInfo()
//        am.getMemoryInfo(mi)
//        // mi.availMem; 当前系统的可用内存
//        return Formatter.formatFileSize(context, mi.availMem)// 将获取的内存大小规格化
//    }

    /**
     *   * 获取android当前可用运行内存大小
     *   * @param context
     *   *
     */
    fun getAvailMemoryLong(context: Context): Long {
        val am = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val mi = ActivityManager.MemoryInfo()
        am.getMemoryInfo(mi)
        return mi.availMem / MB// 将获取的内存大小规格化
    }

}