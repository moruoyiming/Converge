package com.jzyc.instock.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Base64;

import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by hanbin on 2017/11/1.
 */

public class QZCommonUtils {


    /**
     * 创建u+时间+两位数的字符串
     *
     * @return 时间+五位数的字符串
     */
    public static String createExamUnique() {
        return System.currentTimeMillis() + String.format(Locale.getDefault(), "%05d", (int) (Math.random() * 100000));
    }

    /**
     * 判断网络是否可用
     */
    public static boolean isNetworkConnected(Context context) {
        try {
            if (context != null) {
                ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                        .getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
                if (mNetworkInfo != null) {
                    return mNetworkInfo.isAvailable();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * wifi net work
     */
    public static final String NETWORK_WIFI = "wifi";

    /**
     * "2G" networks
     */
    public static final String NETWORK_CLASS_2_G = "2G";

    /**
     * "3G" networks
     */
    public static final String NETWORK_CLASS_3_G = "3G";

    /**
     * "4G" networks
     */
    public static final String NETWORK_CLASS_4_G = "4G";

    /**
     * 获取网络类型
     *
     * @param context 上下文
     * @return 网络类型 wifi 2G 3G 4G
     */
    public static String getNetWorkStatus(Context context) {
        String netWorkType = NETWORK_WIFI;

        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            int type = networkInfo.getType();

            if (type == ConnectivityManager.TYPE_WIFI) {
                netWorkType = NETWORK_WIFI;
            } else if (type == ConnectivityManager.TYPE_MOBILE) {
                netWorkType = getNetWorkClass(context);
            }
        }
        return netWorkType;
    }


    public static String getNetWorkClass(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

        switch (telephonyManager.getNetworkType()) {
            case TelephonyManager.NETWORK_TYPE_GPRS:
            case TelephonyManager.NETWORK_TYPE_EDGE:
            case TelephonyManager.NETWORK_TYPE_CDMA:
            case TelephonyManager.NETWORK_TYPE_1xRTT:
            case TelephonyManager.NETWORK_TYPE_IDEN:
                return NETWORK_CLASS_2_G;
            case TelephonyManager.NETWORK_TYPE_UMTS:
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
            case TelephonyManager.NETWORK_TYPE_EVDO_A:
            case TelephonyManager.NETWORK_TYPE_HSDPA:
            case TelephonyManager.NETWORK_TYPE_HSUPA:
            case TelephonyManager.NETWORK_TYPE_HSPA:
            case TelephonyManager.NETWORK_TYPE_EVDO_B:
            case TelephonyManager.NETWORK_TYPE_EHRPD:
            case TelephonyManager.NETWORK_TYPE_HSPAP:
                return NETWORK_CLASS_3_G;
            case TelephonyManager.NETWORK_TYPE_LTE:
                return NETWORK_CLASS_4_G;
            default:
                return NETWORK_WIFI;
        }
    }


    /**
     * 生成唯一时间的UUID 即完整的唯一标识
     * 算法 根据当前毫秒加上3-5位随机数之后md5 并转成大写
     *
     * @return 唯一时间的UUID 即完整的唯一标识
     */
    public static String getUUID(String timestamp) {
        long ranNum;
        int current = (int) (Math.random() * 9 + 1);
        if (current % 3 == 0) { //取5位随机数
            ranNum = (long) ((Math.random() * 9 + 1) * 10000);
        } else if (current % 3 == 1) {//取4位随机数
            ranNum = (long) ((Math.random() * 9 + 1) * 1000);
        } else { //取3位随机数
            ranNum = (long) ((Math.random() * 9 + 1) * 100);
        }
        return MD5encrypt((timestamp + ranNum)).toUpperCase();
    }

    /**
     * MD5加密 32位
     *
     * @param plaintext 明文
     * @return ciphertext 密文
     */
    public static String MD5encrypt(String plaintext) {
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            byte[] btInput = plaintext.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char[] str = new char[j * 2];
            int k = 0;
            for (byte byte0 : md) {
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 签名的时候取出URL的path部分
     * 为uri部分 eg. http://kmf.com/users/1?p=123   则path为 /users/1
     *
     * @return urlPath
     */
    public static String getURLPath(String URL) {
        java.net.URL url;
        try {
            url = new java.net.URL(URL);
            return url.getPath();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 签名的时候取出query部分
     * 为 url  ?部分的内容  eg. http://kmf.com/users/1?keywords=kmf&p=123&aa=444
     * 则query为```keywords=kmf&p=10&aa=444```转化为数组 并按照key 升序排序，后连接起来。 ```（aa444keywordskmfp10）```
     *
     * @return url的query部分
     */
    public static String getURLQuery(String url) {
        StringBuilder query = new StringBuilder();
        Map<String, String> mapRequest = URLRequest(url);
        //url参数键值对
        for (String strRequestKey : mapRequest.keySet()) {
            String strRequestValue = mapRequest.get(strRequestKey);
            query.append(strRequestKey).append(strRequestValue);
        }
        return query.toString();
    }

    /**
     * 解析出url参数中的键值对
     * 如 "index.jsp?Action=del&id=123"，解析出Action:del,id:123存入map中
     *
     * @param URL url地址
     * @return url请求参数部分
     */
    public static Map<String, String> URLRequest(String URL) {
        Map<String, String> mapRequest = new TreeMap<>(
                new Comparator<String>() {
                    public int compare(String obj1, String obj2) {
                        // 升序排序
                        return obj1.compareTo(obj2);
                    }
                });

        try {
            java.net.URL url = new java.net.URL(URL);
            String strUrlParam = url.getQuery();
            if (TextUtils.isEmpty(strUrlParam)) {
                return mapRequest;
            } else {
                //每个键值为一组
                String[] arrSplit = strUrlParam.split("[&]");
                for (String strSplit : arrSplit) {
                    if (!TextUtils.isEmpty(strSplit) && strSplit.contains("=")) {
                        int idx = strSplit.indexOf("=");
                        String substring = strSplit.substring(idx + 1);
                        if (!TextUtils.isEmpty(substring)) {
                            mapRequest.put(strSplit.substring(0, idx), substring);
                        } else {
                            mapRequest.put(strSplit.substring(0, idx), "");
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapRequest;
    }

    /**
     * 校验字符是否包含中文
     *
     * @param checkString 要检测的字符
     * @return 否包含中文
     */
    public static boolean isContainChinese(String checkString) {
        String regEx = "[\u4e00-\u9fff]";
        Pattern pat = Pattern.compile(regEx);
        Matcher matcher = pat.matcher(checkString);
        return matcher.find();
    }


}
