package com.cocos.base.utils.kotlin

import android.text.TextUtils
import android.util.Log
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.lang.*
import java.text.DateFormat

/**
 * 时间处理操作类
 */
class DateUtils {
    companion object {
        private val instance = DateUtils()

        val nyrFormatNYR = "yyyy-MM-dd HH:mm:ss"
        val nyrFormatYR = "MM-dd HH:mm:ss"
        val nyrFormatR = "HH:mm:ss"

        @JvmStatic
        fun getInstance(): DateUtils {
            return instance

        }
    }

    /**
     * G: 公元时代，例如AD公元
     * yy: 年的后2位
     * yyyy: 完整年
     * MM: 月，显示为1-12
     * MMM: 月，显示为英文月份简写,如 Jan
     * MMMM: 月，显示为英文月份全称，如 Janualy
     * dd: 日，2位数表示，如02
     * d: 日，1-2位显示，如 2
     * EEE: 简写星期几，如Sun
     * EEEE: 全写星期几，如Sunday
     * aa: 上下午，AM/PM
     * H: 时，24小时制，0-23
     * K：时，12小时制，0-11
     * m: 分，1-2位
     * mm: 分，2位
     * s: 秒，1-2位
     * ss: 秒，2位
     * S: 毫秒
     *
     * 链接：https://www.jianshu.com/p/fb2505a7f43b
     */

    /**
     *得到中文的格式的日期.
     * @param time
     * @param format : 传入的时间格式 如:"yyyy年MM月dd日 ; MM.dd ; yyyy-MM-dd HH:mm:ss
     * @return
     */
    fun getStringChineseTime(time: Long, format: String): String {
        var time = time
        if (time < 9999999999L) { // 说明传入的是秒.需要转换为毫秒
            time *= 1000
        }
        val d = Date(time)
        val sf = SimpleDateFormat(format)
        return sf.format(d)
    }

    /**
     * 得到英文的格式的日期.
     * @param format : 传入的时间格式 如:"yyyy年MM月dd日 ; MM.dd ; yyyy-MM-dd HH:mm:ss ;MMM d | yyyy
     * @param time
     */
    fun getStringEngLishTime(time: Long, format: String): String {
        var time = time
        if (time < 9999999999L) { // 说明传入的是秒.需要转换为毫秒
            time = time * 1000
        }
        val d = Date(time)
        val sf = SimpleDateFormat(format, Locale.ENGLISH)
        return sf.format(d)
    }

    /**
     * 解析字符串成日期
     *
     * @param dateString 字符串格式的日期 yyyy-MM-dd
     * @param format : 传入的时间格式 如:"yyyy年MM月dd日 HH时mm分ss秒 ; MM.dd ; yyyy-MM-dd HH:mm:ss ;MMM d | yyyy
     * @return 日期
     */
    fun parseDate(dateString: String, format: String): Date {
        var date: Date? = null
        try {
            val format = SimpleDateFormat(format)
            date = format.parse(dateString)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return date ?: Date()
    }

    /**
     * 解析字符串成日期
     *
     * @param dateString 字符串格式的日期 yyyy-MM-dd
     * @param format : 传入的时间格式 如:"yyyy年MM月dd日 HH时mm分ss秒 ; MM.dd ; yyyy-MM-dd HH:mm:ss ;MMM d | yyyy
     * @return 日期
     */
    fun parseDateTime(dateString: Long, format: String): String {
        var date: Date? = null
        try {
            val format = SimpleDateFormat(format)
            date = Date(dateString)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        date = date ?: Date()
        return date!!.stringFormat(format)
    }

    /**
     * 解析字符串成日期
     *
     * @param dateString 字符串格式的日期 yyyy-MM-dd
     * @param format : 传入的时间格式 如:"yyyy年MM月dd日 HH时mm分ss秒 ; MM.dd ; yyyy-MM-dd HH:mm:ss ;MMM d | yyyy
     * @return 日期
     */
    fun parseDateFormat(dateString: String, format: String): String {
        var date: Date? = null
        try {
            val format = SimpleDateFormat(format)
            date = format.parse(dateString)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        date = date ?: Date()
        return date!!.stringFormat(format)
    }

    /**
     * 得到当前时间
     *
     * @return 当前日期格式 yyyy-MM-dd HH:mm:ss
     */
    fun getNowDateString(format: String): String? {

        var mDate: String? = null
        val dfs = SimpleDateFormat(format)
        val end = Date()
        try {
            mDate = dfs.format(end)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return mDate
    }

    /**
     * 获取当前年份
     *
     * @return
     */
    fun getCurrentYear(): String {
        val cal = Calendar.getInstance()
        val year = cal.get(Calendar.YEAR)
        return year.toString()
    }

    /**
     * 获取当前年月份
     *
     * @return
     */
    fun getCurrentMonth(): String {
        val cal = Calendar.getInstance()
        val month = cal.get(Calendar.MONTH) + 1
        return month.toString()
    }

    fun changeBeforeTimeWeek(time: String): String {
        var week = ""
        val weekCalendar = Calendar.getInstance()
        val timeDateFormat = SimpleDateFormat("yyyy年MM月dd日")
        try {
            weekCalendar.time = timeDateFormat.parse(time)
        }catch (e :ParseException){
            e.printStackTrace()
        }
        val dayOfWeek = weekCalendar.get(Calendar.DAY_OF_WEEK)
        when (dayOfWeek) {
            1 -> week = "周日"
            2 -> week = "周一"
            3 -> week = "周二"
            4 -> week = "周三"
            5 -> week = "周四"
            6 -> week = "周五"
            7 -> week = "周六"
            else -> { }
        }
        return week
    }

    /**
     * 转换时间差值为 分钟：秒格式
     *
     * @param betweenTime 时间差值
     * @return 格式：00：12
     */
    fun getDateTime(betweenTime: Long): String {
        val min = betweenTime / (60 * 1000)
        val s = (betweenTime - min * 60 * 1000) / 1000
        return java.lang.String.format("%02d", min) + ":" + java.lang.String.format("%02d", s)
    }

    fun getCurrentDate(): String? {
       var str = getNowDateString("MM月dd日") + "～"+ get7DayAfter()
        return str
    }

    /**
     * 判断时间戳和当前时间的差值，以字符串格式返回
     *
     * @param time 时间戳格式 秒
     * @return 字符串格式当前时间的差值
     */

    fun changeTimeToString(time: Long): String {
        val stringBuilder = StringBuilder()
        val spaceTime = System.currentTimeMillis() / 1000 - time
        val day = spaceTime / (24 * 60 * 60)
        val hour = spaceTime / (60 * 60) - 24 * day
        val min = spaceTime / 60 - day * 24 * 60 - hour * 60
        val s = spaceTime - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60
        if (day > 0) {
            stringBuilder.append(day)
            stringBuilder.append("天")
        }
        if (hour > 0) {
            stringBuilder.append(hour)
            stringBuilder.append("小时")
        }
        if (min > 0) {
            stringBuilder.append(min)
            stringBuilder.append("分钟")
        }
        if (s >= 0) {
            stringBuilder.append(s)
            stringBuilder.append("秒")
        }
        return stringBuilder.toString()
    }


    /**
     * 判断时间戳和某个时间戳差值，以字符串格式返回
     *
     * @param time 时间戳格式 秒  小值
     * @param time2 时间戳格式 秒 大值
     * @return 字符串格式当前时间的差值
     */
    fun changeTimeToString(time: Long, time2: Long, count: Int): String {
        val stringBuilder = StringBuilder()
        val spaceTime = time2 - time
        val day = spaceTime / (24 * 60 * 60)
        val hour = spaceTime / (60 * 60) - 24 * day
        val min = spaceTime / 60 - day * 24 * 60 - hour * 60
        val s = spaceTime - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60
        if (day > 0 && count > 0) {
            stringBuilder.append(day)
            stringBuilder.append("天")
        }
        if (hour > 0 && count > 1) {
            stringBuilder.append(hour)
            stringBuilder.append("小时")
        }
        if (min > 0 && count > 2) {
            stringBuilder.append(min)
            stringBuilder.append("分钟")
        }
        if (s > 0 && count > 3) {
            stringBuilder.append(s)
            stringBuilder.append("秒")
        }
        return stringBuilder.toString()
    }

    /**
     * 将时间转换成多久之前
     *
     * @param time 秒值
     * @return 多久之前
     */
    fun changeBeforeTimeString(time: String): String {
        if (TextUtils.isEmpty(time)) {
            return ""
        }
        val now = Calendar.getInstance().timeInMillis
        var date: Date? = null
        try {
            date = Date(java.lang.Long.parseLong(time + "000"))
        } catch (e: Exception) {
            e.printStackTrace()
        }

        if (date == null) {
            return ""
        }
        var shortString = ""
        val delTime = (now - date.time) / 1000
        if (delTime >= 31536000) {//365天
            shortString = (delTime / 31536000).toInt().toString() + "年前"
        } else if (delTime >= 86400) {
            shortString = (delTime / 86400).toInt().toString() + "天前"
        } else if (delTime >= 3600) {
            shortString = (delTime / 3600).toInt().toString() + "小时前"
        } else if (delTime >= 60) {
            shortString = (delTime / 60).toInt().toString() + "分前"
        } else if (delTime > 1) {
            shortString = delTime.toString() + "秒前"
        } else {
            shortString = "刚刚"
        }
        return shortString

    }

    /**
     * 转换播放器的播放时间为显示格式 00:00:00
     * @param playTime 播放时间毫秒值
     * @return 要显示的字符串格式 00:00:00
     */
    fun changePlayTime2String2(playTime: Int): String {
        val stringBuilder = StringBuilder()
        val hour = playTime / 3600000//小时
        stringBuilder.append(java.lang.String.format("%02d", hour))
        stringBuilder.append(":")
        val minute = (playTime - hour * 3600000) / 60000//分钟
        val second = (playTime - hour * 3600000 - minute * 60000) / 1000//秒
        stringBuilder.append(java.lang.String.format("%02d", minute))
        stringBuilder.append(":")
        stringBuilder.append(java.lang.String.format("%02d", second))
        return stringBuilder.toString()
    }

    /*
     * 判断两个日期的相差天数
     */
    fun daysBetweenTwo(smdate: String, bdate: String): Int {
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        val cal = Calendar.getInstance()
        try {
            cal.time = sdf.parse(smdate)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        val time1 = cal.timeInMillis
        try {
            cal.time = sdf.parse(bdate)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        val time2 = cal.timeInMillis
        val between_days = (time2 - time1) / (1000 * 3600 * 24)
        return between_days.toInt()
    }


    /**
     * date+1
     * 往后的几天
     */
    operator fun Date.plus(nextVal: Int): Date {
        val calendar = GregorianCalendar()
        calendar.time = this
        calendar.add(Calendar.DATE, nextVal)
        return calendar.time
    }

    /**
     * date-1
     */
    operator fun Date.minus(nextVal: Int): Date {
        val calendar = GregorianCalendar()
        calendar.time = this
        calendar.add(Calendar.DATE, nextVal * -1)
        return calendar.time
    }


//    /**
//     * 得到月末
//     */
//    operator fun Date.inc():Date {
//        val calendar = GregorianCalendar()
//        calendar.time = this
//        calendar.add(Calendar.MONTH, 1);
//        calendar.set(Calendar.DAY_OF_MONTH, 0);
//        return calendar.time
//    }
//
//    /**
//     * 得到月初
//     */
//    operator fun Date.dec():Date {
//        val calendar = GregorianCalendar()
//        calendar.time = this
//        calendar.set(Calendar.DAY_OF_MONTH, 1)
//        return calendar.time
//    }

    /**
     * 取 年月日时分秒 0 - 5
     * 例如 2015-12-21 22:15:56
     * date[0]:2015  date[1]:12 date[2]:21
     */
    operator fun Date.get(position: Int): Int {
        val calendar = GregorianCalendar()
        calendar.time = this
        var value = 0
        when (position) {
            0 -> value = calendar.get(Calendar.YEAR)
            1 -> value = calendar.get(Calendar.MONTH) + 1
            2 -> value = calendar.get(Calendar.DAY_OF_MONTH)
            3 -> value = calendar.get(Calendar.HOUR)
            4 -> value = calendar.get(Calendar.MINUTE)
            5 -> value = calendar.get(Calendar.SECOND)
        }
        return value
    }

    /**
     * 比较2个日期
     * if(date1 > date2) {
     * }
     */

    operator fun Date.compareTo(compareDate: Date): Int {
        return (time - compareDate.time).toInt()
    }

    /**
     * 日期转化为字符串
     */
    fun Date.stringFormat(formatType: String): String {
        return SimpleDateFormat(formatType).format(this)
    }


    fun getNextDay(): String {
        val format = SimpleDateFormat("yyyy-MM-dd")
        val yesterdayCalendar = Calendar.getInstance()
        yesterdayCalendar.add(Calendar.DAY_OF_MONTH, -1)//日期
        val yesterdayCalendarTime = yesterdayCalendar.time
        return format.format(yesterdayCalendarTime)
    }


    fun get7DayAfter(): String {
        val format = SimpleDateFormat("MM月dd日")
        val yesterdayCalendar = Calendar.getInstance()
        yesterdayCalendar.add(Calendar.DAY_OF_MONTH, +7)//日期
        val yesterdayCalendarTime = yesterdayCalendar.time
        return format.format(yesterdayCalendarTime)
    }


    /**
     * 时间戳转换成字符窜 传入long类型的时间戳 返回HH:mm 时分
     */
    fun getDateToStringMDSF(time: Long): String {
        var time = time
        if (time < 9999999999L) { // 说明传入的是秒.需要转换为毫秒
            time = time * 1000
        }
        val d = Date(time)
        val sf = SimpleDateFormat(nyrFormatYR)
        return sf.format(d)
    }
}





