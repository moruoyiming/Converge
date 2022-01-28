package com.cocos.base.kotlinutils

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import android.text.TextUtils
import android.util.TypedValue
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.cocos.base.R
import org.json.JSONArray
import org.json.JSONObject
import java.io.Closeable

////////////////////////////////////主要存放kotlin中的扩展方法/////////////////////////////////////////
/**
 * dp转px
 */
fun Float.dp2Px(): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        Resources.getSystem().displayMetrics
    )
}

/**
 * sp转px
 */
fun Float.sp2Px(): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP,
        this,
        Resources.getSystem().displayMetrics
    )
}

/**
 * 关闭流
 */
fun Closeable?.closeQuietly() {
    try {
        this?.close()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

/***
 * 设置延迟时间的View扩展
 * @param delay Long 延迟时间，默认600毫秒
 * @return T
 */
fun <T : View> T.withTrigger(delay: Long = 600): T {
    triggerDelay = delay
    return this
}

/***
 * 点击事件的View扩展
 * @param block: (T) -> Unit 函数
 * @return Unit
 */
fun <T : View> T.click(block: (T) -> Unit) = setOnClickListener {

    if (clickEnable()) {
        block(this)
    }
}

/***
 * 带延迟过滤的点击事件View扩展
 * @param delay Long 延迟时间，默认500毫秒
 * @param block: (T) -> Unit 函数
 * @return Unit
 */
fun <T : View> T.clickWithTrigger(delay: Long = 500, block: (T) -> Unit) {
    triggerDelay = delay
    setOnClickListener {
        if (clickEnable()) {
            block(this)
        }
    }
}


/**
 * 给view添加一个上次触发时间的属性，用来屏蔽双击操作
 */
private var <T : View> T.triggerLastTime: Long
    get() = if (getTag(R.id.triggerLastTimeKeyAssembly) != null) getTag(R.id.triggerLastTimeKeyAssembly) as Long else 0
    set(value) {
        setTag(R.id.triggerLastTimeKeyAssembly, value)
    }

/**
 * 给view添加一个延迟的属性，用来屏蔽双击操作
 */
private var <T : View> T.triggerDelay: Long
    get() = if (getTag(R.id.triggerDelayKeyAssembly) != null) getTag(R.id.triggerDelayKeyAssembly) as Long else -1
    set(value) {
        setTag(R.id.triggerDelayKeyAssembly, value)
    }

private fun <T : View> T.clickEnable(): Boolean {
    var flag = false
    val currentClickTime = System.currentTimeMillis()
    if (currentClickTime - triggerLastTime >= triggerDelay) {
        flag = true
    }
    triggerLastTime = currentClickTime
    return flag
}


fun getDrawable(context: Context, id: Int): Drawable {
    return ContextCompat.getDrawable(context, id)!!
}

//////////////////////////////json解析相关方法//////////////////////////////////
/**
 * JSON 解析数据 String
 */
fun JSONObject.getStringJson(key: String): String {
    var str = ""
    try {
        str = this.getString(key)
        if (null == str) {//有些业务存在 "null"这个状态
            str = ""
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return str
}

/**
 * JSON 解析数据 int
 */
fun JSONObject.getIntJson(key: String): Int {
    var i = 0
    try {
        i = this.getInt(key)
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return i
}

/**
 * JSON 解析数据 long
 */
fun JSONObject.getLongJson(key: String): Long {
    var i = 0L
    try {
        i = this.getLong(key)
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return i
}

/**
 * JSON 解析数据 double
 */
fun JSONObject.getDoubleJson(key: String): Double {
    var i = 0.0
    try {
        i = this.getDouble(key)
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return i
}

/**
 * JSON 解析数据 JSONObject
 */
fun JSONObject.getJSONObjectJson(key: String): JSONObject? {
    return this.optJSONObject(key)
}

/**
 * JSON 解析数据 JSONArray
 */
fun JSONObject.getJSONArrayJson(string: String): JSONArray? {
    return this.optJSONArray(string)
}


fun ImageView.loadImage(uri: String?) {
    if(!TextUtils.isEmpty(uri)&&uri!!.startsWith("https://")){
        Glide.with(this.context).load(uri).into(this)
    }else{
        Glide.with(this.context).load("https://"+uri).into(this)
    }

}

fun ImageView.loadGif(uri: Int, requestListener: RequestListener<GifDrawable?>? = null, centerCrop: Boolean? = null, @DrawableRes holder: Int? = null) {
    var requestOptions = RequestOptions().dontTransform()
    if (centerCrop != null) {
        requestOptions = requestOptions.centerCrop()
    }
    if (holder != null) {
        requestOptions = requestOptions.placeholder(holder)
    }
    if (requestListener != null) {
        Glide.with(this.context).asGif().load(uri).apply(requestOptions).listener(requestListener).into(this)
    } else {
        Glide.with(this.context).asGif().load(uri).apply(requestOptions).into(this)
    }
}

fun ImageView.loadGif(uri: String?, requestListener: RequestListener<GifDrawable?>? = null, centerCrop: Boolean? = null, @DrawableRes holder: Int? = null) {
    var requestOptions = RequestOptions().dontTransform()
    if (centerCrop != null) {
        requestOptions = requestOptions.centerCrop()
    }
    if (holder != null) {
        requestOptions = requestOptions.placeholder(holder)
    }
    if (requestListener != null) {
        Glide.with(this.context).asGif().load(uri).apply(requestOptions).listener(requestListener).into(this)
    } else {
        Glide.with(this.context).asGif().load(uri).apply(requestOptions).into(this)
    }
}

