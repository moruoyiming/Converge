package com.cocos.base.kotlinutils

import android.content.ContentUris
import android.content.ContentValues
import android.content.Context
import android.graphics.Color
import android.net.Uri
import android.provider.CalendarContract
import java.util.*


/**
 * 添加日历日程的工具类
 */
class CalendarEventUtils {

    private val CALENDER_URL = "content://com.android.calendar/calendars"
    private val CALENDER_EVENT_URL = "content://com.android.calendar/events"
    private val CALENDER_REMINDER_URL = "content://com.android.calendar/reminders"


    private val CALENDARS_NAME = "日历提醒"
    private val CALENDARS_ACCOUNT_NAME = "kmf"
    private val CALENDARS_ACCOUNT_TYPE = "com.android.exchange"
    private val CALENDARS_DISPLAY_NAME = "考满分账户"

    companion object {
        private val instance = CalendarEventUtils()
        @JvmStatic
        fun getInstance(): CalendarEventUtils {
            return instance
        }
    }

    /**
     * 查询日历账户
     */
    private fun checkCalendarAccount(context: Context): Int {
        val cursor = context.contentResolver.query(
            Uri.parse(CALENDER_URL),
            null, null, null, null
        )
        cursor.use { userCursor ->
            if (userCursor == null)//查询返回空值
                return -1
            val count = userCursor.count
            return if (count > 0) {//存在现有账户，取第一个账户的id返回
                userCursor.moveToFirst()
                userCursor.getInt(userCursor.getColumnIndex(CalendarContract.Calendars._ID))
            } else {
                -1
            }
        }
    }

    private fun addCalendarAccount(context: Context): Long {
        
        val timeZone = TimeZone.getDefault()
        val value = ContentValues()
        value.put(CalendarContract.Calendars.NAME, CALENDARS_NAME)
        value.put(CalendarContract.Calendars.ACCOUNT_NAME, CALENDARS_ACCOUNT_NAME)
        value.put(CalendarContract.Calendars.ACCOUNT_TYPE, CALENDARS_ACCOUNT_TYPE)
        value.put(CalendarContract.Calendars.CALENDAR_DISPLAY_NAME, CALENDARS_DISPLAY_NAME)
        value.put(CalendarContract.Calendars.VISIBLE, 1)
        value.put(CalendarContract.Calendars.CALENDAR_COLOR, Color.BLUE)
        value.put(
            CalendarContract.Calendars.CALENDAR_ACCESS_LEVEL,
            CalendarContract.Calendars.CAL_ACCESS_OWNER
        )
        value.put(CalendarContract.Calendars.SYNC_EVENTS, 1)
        value.put(CalendarContract.Calendars.CALENDAR_TIME_ZONE, timeZone.id)
        value.put(CalendarContract.Calendars.OWNER_ACCOUNT, CALENDARS_ACCOUNT_NAME)
        value.put(CalendarContract.Calendars.CAN_ORGANIZER_RESPOND, 0)
        var calendarUri = Uri.parse(CALENDER_URL)
        calendarUri = calendarUri.buildUpon()
            .appendQueryParameter(CalendarContract.CALLER_IS_SYNCADAPTER, "true")
            .appendQueryParameter(CalendarContract.Calendars.ACCOUNT_NAME, CALENDARS_ACCOUNT_NAME)
            .appendQueryParameter(CalendarContract.Calendars.ACCOUNT_TYPE, CALENDARS_ACCOUNT_TYPE)
            .build()
        val result = context.contentResolver.insert(calendarUri, value)
        return if (result == null) {
            -1
        } else {
            ContentUris.parseId(result)
        }
    }


    /**
     * 检查并且创建日历账号
     */
    private fun checkAndAddCalendarAccount(context: Context): Int {
        val account = checkCalendarAccount(context)
        return if (account > -1) {
            account
        } else {
            val calendarAccount = addCalendarAccount(context)
            if (calendarAccount > -1) {
                calendarAccount.toInt()
            } else {
                -1
            }
        }
    }

    fun addCalendarEvent(context: Context, title: String, describe: String, startTime: Long, endTime: Long) : Boolean {
        val accountId = checkAndAddCalendarAccount(context)
        if (accountId < 0) {
            return false
        }
        val event = ContentValues()
        event.put("title", title)
        event.put("description", describe)
        // 插入账户的id
        event.put("calendar_id", accountId)
//        val mCalendar = Calendar.getInstance()
//        mCalendar.timeInMillis = startTime//设置开始时间
//        val start = mCalendar.time.time
//        mCalendar.timeInMillis = start + 300 * 1000//设置终止时间
//        val end = mCalendar.time.time

        event.put(CalendarContract.Events.DTSTART, startTime)
        event.put(CalendarContract.Events.DTEND, endTime)
        event.put(CalendarContract.Events.HAS_ALARM, 1)//设置有闹钟提醒
        event.put(CalendarContract.Events.EVENT_TIMEZONE, "Asia/Shanghai") //这个是时区，必须有，
        //添加事件
        val newEvent =
            context.contentResolver.insert(
                Uri.parse(CALENDER_EVENT_URL),
                event
            )
                ?: // 添加日历事件失败直接返回
                return false
        //事件提醒的设定
        val values = ContentValues()
        values.put(CalendarContract.Reminders.EVENT_ID, ContentUris.parseId(newEvent))
        // 提前10分钟有提醒
        values.put(CalendarContract.Reminders.MINUTES, 10)
        values.put(CalendarContract.Reminders.METHOD, CalendarContract.Reminders.METHOD_ALERT)
        context.contentResolver.insert(
            Uri.parse(CALENDER_REMINDER_URL),
            values
        )
            ?: // 添加闹钟提醒失败直接返回
            return false
        return true
    }
}