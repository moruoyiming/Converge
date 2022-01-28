package com.cocos.base.kotlinutils

import java.util.*


/**
 * Created by hanbin on 2019/8/20.
 * String转换的工具类
 */
class StringUtils {

    companion object {
        private val instance = StringUtils()
        @JvmStatic
        fun getInstance(): StringUtils {
            return instance
        }
    }

    /**
     * 字符串转换为Int
     *
     * @param src 字符串
     * @return int值
     */
    fun parseString2Int(src: String): Int {
        var result = 0
        try {
            result = src.toInt()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return result
    }

    /**
     * 字符串转换为Double
     *
     * @param src 字符串
     * @return double值
     */
    fun parseString2Double(src: String): Double {
        var result = 0.0
        try {
            result = src.toDouble()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return result
    }

    /**
     * 字符串转换为Float
     *
     * @param src 字符串
     * @return float值
     */
    fun parseString2Float(src: String): Float {
        var result = 0.0f
        try {
            result = src.toFloat()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return result
    }

    /**
     * 字符串转Long
     *
     * @param src
     * @return
     */
    fun parseString2Long(src: String): Long {
        var result = 0L
        try {
            result = src.toLong()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return result
    }

    /**
     * str转换成list，已“|”分割
     */
    fun getListFromString(str: String, flag: String): List<String> {
        val list = ArrayList<String>()
        val st = StringTokenizer(str, flag)//"|"或者"/"
        while (st.hasMoreTokens()) {
            list.add(st.nextToken())
        }
        return list
    }

}
