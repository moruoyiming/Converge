package com.cocos.base.kotlinutils

import org.json.JSONArray
import org.json.JSONObject
import java.util.ArrayList

/**
 *
 * author huanghuajuan
 * created at 2019-08-23 17:41
 * json 解析类
 */
class JsonUtils {

    companion object {
        private val instant = JsonUtils()
        @JvmStatic
        fun getInstance(): JsonUtils {
            return instant
        }
    }

    /**
     * JSON 解析数据 String
     *
     * @param string
     */
    fun getStringJson(string: String, jsonObj: JSONObject): String {
        var str: String
        try {
            str = jsonObj.getString(string)
            if ("null" == str || null == str) {
                str = ""
            }
        } catch (e: Exception) {
            str = jsonObj.optString(string, "")
            e.printStackTrace()
        }
        return str
    }


    /**
     * JSON 解析数据 int
     *
     * @param string
     */
    fun getIntJson(string: String, jsonObj: JSONObject): Int {
        var i: Int
        try {
            i = jsonObj.getInt(string)
        } catch (e: Exception) {
            e.printStackTrace()
            i = jsonObj.optInt(string, 0)
        }
        return i
    }

    /**
     * JSON 解析数据 int
     *
     * @param string
     */
    fun getLongJson(string: String, jsonObj: JSONObject): Long {
        var i: Long
        try {
            i = jsonObj.getLong(string)
        } catch (e: Exception) {
            e.printStackTrace()
            i = jsonObj.optLong(string, 0)
        }
        return i
    }

    /**
     * JSON 解析数据 double
     *
     * @param string
     */
    fun getDoubleJson(string: String, jsonObj: JSONObject): Double {
        var i: Double
        try {
            i = jsonObj.getDouble(string)
        } catch (e: Exception) {
            e.printStackTrace()
            i = jsonObj.optDouble(string, 0.0)
        }
        return i
    }

    /**
     * JSON 解析数据 JSONObject
     *
     * @param string
     */
    @Throws(Exception::class)
    fun getJSONObjectJson(string: String, jsonObj: JSONObject): JSONObject? {
        return jsonObj.optJSONObject(string)
    }

    /**
     * JSON 解析数据 JSONArray
     *
     * @param string
     */
    @Throws(Exception::class)
    fun getJSONArrayJson(string: String, jsonObj: JSONObject): JSONArray? {
        return jsonObj.optJSONArray(string)
    }

    /**
     * jsonString转ArrayList<T>
     * @param jsonString
     * @return
    </T> */
//    fun <T : Any> jsonString2ArrayList(jsonString: String, t: T): ArrayList<T>? {
//        return JSON.parseArray(jsonString, t.javaClass) as ArrayList<T>
//    }


    /**
     * ArrayList<T>转jsonString
     * @param arrayList
     * @return
    </T> */
//    fun <T : Any> ArrayList2jsonString(arrayList: ArrayList<T>): String {
//        return JSON.toJSONString(arrayList)
//    }
}