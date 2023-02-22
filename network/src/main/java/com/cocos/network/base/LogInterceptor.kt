package com.cocos.network.base

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import org.json.JSONArray
import org.json.JSONObject
import java.nio.charset.Charset

/**
 * 辅助日志输出的okhttp拦截器
 */
public class LogInterceptor : Interceptor {

    private val tag = "LogInterceptor"

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestInformation = request.toString()
        val response = chain.proceed(request)
        //获取response数据只能这么处理，要不然会影响后续使用
        val source = response.body()?.source()
        source?.request(java.lang.Long.MAX_VALUE)
        val buffer = source?.buffer
        var body = buffer?.clone()?.readString(Charset.forName("UTF-8"))
        if (!body.isNullOrEmpty()) {
            Log.i(tag, " ───────────────────────requestInformation──────────────────────────")
            Log.i(tag, requestInformation)
            Log.i(tag, " ─────────────────────────────Header─────────────────────────────")
            val headers = request.headers()
            Log.i(tag, "$headers")
            Log.i(tag, " ─────────────────────────────Response─────────────────────────────")
            body = body.trim()
            when {
                body.startsWith("{") -> {
                    val jsonObject = JSONObject(body)
                    val toString = jsonObject.toString(4)
                    Log.i(tag, toString)
                }
                body.startsWith("[") -> {
                    val jsonArray = JSONArray(body)
                    val toString = jsonArray.toString(4)
                    Log.i(tag, toString)
                }
                else -> Log.i(tag, body)
            }
        }
        return response
    }
}