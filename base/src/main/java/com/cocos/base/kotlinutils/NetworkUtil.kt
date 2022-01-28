package com.cocos.base.kotlinutils

import android.content.Context
import android.net.ConnectivityManager
import android.telephony.TelephonyManager

/**
 * 网络处理操作类
 *  NetworkHelper -新浪提供的网络类.
 */
class NetworkUtil {
    companion object {
        private val instance = NetworkUtil()
        @JvmStatic
        fun getInstance(): NetworkUtil {
            return instance

        }
    }

    /**
     * wifi net work
     */
    val NETWORK_WIFI = "wifi"

    /**
     * "2G" networks
     */
    val NETWORK_CLASS_2_G = "2G"

    /**
     * "3G" networks
     */
    val NETWORK_CLASS_3_G = "3G"

    /**
     * "4G" networks
     */
    val NETWORK_CLASS_4_G = "4G"

    // 是否是WIFI网络
    fun isNetWorkWifi(context: Context): Boolean {
        val cm = context
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkINfo = cm.activeNetworkInfo
        return networkINfo != null && networkINfo.type == ConnectivityManager.TYPE_WIFI
    }

    // 是否是3g 4G网络
    fun isNetWorkMobile(context: Context): Boolean {
        val cm = context
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkINfo = cm.activeNetworkInfo
        return networkINfo != null && networkINfo.type == ConnectivityManager.TYPE_MOBILE
    }

    // 是否有网络连接，不区分网络类型
    fun isNetWorkConnect(mContext: Context): Boolean {
        val mConnectivity = mContext
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val info = mConnectivity.activeNetworkInfo
        return if (info == null || !mConnectivity.backgroundDataSetting) {
            false
        } else {
            info.isConnected
        }
    }
    /**
     * 获取网络类型
     *
     * @param context 上下文
     * @return 网络类型 wifi 2G 3G 4G
     */
    fun getNetWorkStatus(context: Context): String {
        var netWorkType = NETWORK_WIFI

        val connectivityManager = context
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo

        if (networkInfo != null && networkInfo.isConnected) {
            val type = networkInfo.type

            if (type == ConnectivityManager.TYPE_WIFI) {
                netWorkType = NETWORK_WIFI
            } else if (type == ConnectivityManager.TYPE_MOBILE) {
                netWorkType = getNetWorkClass(context)
            }
        }
        return netWorkType
    }


    fun getNetWorkClass(context: Context): String {
        val telephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

        when (telephonyManager.networkType) {
            TelephonyManager.NETWORK_TYPE_GPRS, TelephonyManager.NETWORK_TYPE_EDGE, TelephonyManager.NETWORK_TYPE_CDMA, TelephonyManager.NETWORK_TYPE_1xRTT, TelephonyManager.NETWORK_TYPE_IDEN -> return NETWORK_CLASS_2_G
            TelephonyManager.NETWORK_TYPE_UMTS, TelephonyManager.NETWORK_TYPE_EVDO_0, TelephonyManager.NETWORK_TYPE_EVDO_A, TelephonyManager.NETWORK_TYPE_HSDPA, TelephonyManager.NETWORK_TYPE_HSUPA, TelephonyManager.NETWORK_TYPE_HSPA, TelephonyManager.NETWORK_TYPE_EVDO_B, TelephonyManager.NETWORK_TYPE_EHRPD, TelephonyManager.NETWORK_TYPE_HSPAP -> return NETWORK_CLASS_3_G
            TelephonyManager.NETWORK_TYPE_LTE -> return NETWORK_CLASS_4_G
            else -> return NETWORK_WIFI
        }
    }

}