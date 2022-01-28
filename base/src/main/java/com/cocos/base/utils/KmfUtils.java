package com.cocos.base.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Base64;


import org.json.JSONArray;
import org.json.JSONException;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
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

public class KmfUtils {
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
     * @return
     */
    public static String getUUID(String timestamp) {
        long ranNum = 100;
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

//    /**
//     * 获取签名 签名规则 时间戳 + uuid + path + method + query + body + appKey
//     * 文档参考:http://con.kaomanfen.com/pages/viewpage.action?pageId=4918428
//     * 签名的方法
//     */
//    public static String getSignToString(String timestamp, String uuid, String url, String method, String body) {
//        String signToString = null;
//        if ("".equals(body) || null == body) { //get
//            signToString = timestamp + uuid
//                    + getURLPath(url) + method + getURLQuery(url) +
//                    "" + HttpConfig.K_KEY_VALUE;
//        } else { //post
//            signToString = timestamp + uuid
//                    + getURLPath(url) + method + getURLQuery(url) +
//                    Base64.encodeToString((MD5encrypt(body).toUpperCase()).getBytes(), Base64.NO_WRAP)
//                    + HttpConfig.K_KEY_VALUE;
//        }
//        //L.d("ssss", "signToString:" + signToString);
//        String k_sign_value = stringToSign(signToString);
//        // L.d("ssss", "k_sign_value:" + k_sign_value);
//        return k_sign_value;
//    }

//    /**
//     * Hmac加密 即签名的机制
//     *
//     * @param data
//     * @return
//     */
//    public static String stringToSign(String data) {
//        Mac mac = null;
//        try {
//            mac = Mac.getInstance(HttpConfig.ALGORITHM_MAC);
//            SecretKeySpec secret = new SecretKeySpec(
//                    HttpConfig.MAC_KEY.getBytes("UTF-8"), mac.getAlgorithm());
//            mac.init(secret);
//            return Base64.encodeToString(mac.doFinal(data.getBytes()), Base64.NO_WRAP);
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        } catch (InvalidKeyException e) {
//            e.printStackTrace();
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        return "";
//    }

    /**
     * MD5加密 32位
     *
     * @param plaintext 明文
     * @return ciphertext 密文
     */
    public final static String MD5encrypt(String plaintext) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
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
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
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
     * @return
     */
    public static String getURLPath(String URL) {
        java.net.URL url = null;
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
     * @return
     */
    public static String getURLQuery(String url) {
        String query = "";
        Map<String, String> mapRequest = URLRequest(url);
        //url参数键值对
        for (String strRequestKey : mapRequest.keySet()) {
            String strRequestValue = mapRequest.get(strRequestKey);
            query += strRequestKey + strRequestValue;
        }
        return query;
    }

    /**
     * 解析出url参数中的键值对
     * 如 "index.jsp?Action=del&id=123"，解析出Action:del,id:123存入map中
     *
     * @param URL url地址
     * @return url请求参数部分
     */
    public static Map<String, String> URLRequest(String URL) {
        Map<String, String> mapRequest = new TreeMap<String, String>(
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
     * 方法用途: 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序），并且生成url参数串<br>
     * 实现步骤: <br>
     *
     * @param paraMap    要排序的Map对象
     * @param urlEncode  是否需要URLENCODE
     * @param keyToLower 是否需要将Key转换为全小写
     *                   true:key转化成小写，false:不转化
     * @return 如: paraMap.put("Type","200");paraMap.put("phoneNumber", "wxd678efh567hg6787");paraMap.put("timeStamp", "194985983493");paraMap.put("UUID","20150806125346");
     * 结果:Type=200&UUID=20150806125346&phoneNumber=wxd678efh567hg6787&timeStamp=194985983493
     */
    public static String formatUrlMap(Map<String, String> paraMap, boolean urlEncode, boolean keyToLower) {
        String buff = "";
        try {
            List<Map.Entry<String, String>> infoIds = new ArrayList<Map.Entry<String, String>>(paraMap.entrySet());
            // 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序）
            Collections.sort(infoIds, new Comparator<Map.Entry<String, String>>() {

                @Override
                public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                    return (o1.getKey()).compareTo(o2.getKey());
                }
            });
            // 构造URL 键值对的格式
            StringBuilder buf = new StringBuilder();
            for (Map.Entry<String, String> item : infoIds) {
                if (!TextUtils.isEmpty(item.getKey())) {
                    String key = item.getKey();
                    String val = item.getValue();
                    if (urlEncode) {
                        val = URLEncoder.encode(val, "utf-8");
                    }
                    if (keyToLower) {
                        buf.append(key.toLowerCase());
                        buf.append("=");
                        buf.append(val);
                    } else {
                        buf.append(key);
                        buf.append("=");
                        buf.append(val);
                    }
                    buf.append("&");
                }

            }
            buff = buf.toString();
            if (!buff.isEmpty()) {
                buff = buff.substring(0, buff.length() - 1);
            }
        } catch (Exception e) {
            return null;
        }
        return buff;
    }


    /**
     * 校验微信号是否合法
     * 1）微信帐号支持6-20个字母、数字、下划线和减号,必须以字母开头
     * 2）支持 11 位数的手机号
     * 3）初期微信注册，以 wxid 开头的原始 ID 不支持；
     *
     * @param content 微信id
     * @return 是否合法 true 合法 false 不合法
     */
    public static boolean checkWeiXinNumber(String content) {
        boolean isLegal;
        if (TextUtils.isEmpty(content) || content.length() < 6 || content.length() > 20) {
            return false;
        }
        isLegal = isLetter(content.subSequence(0, 1).toString());//首个输入的是否为字母
        if (isLegal) {
            //字母开头
            if (content.startsWith("wxid_")) {
                return false;
            } else {
                char[] chars = content.toCharArray();
                for (char c : chars) {
                    String str = String.valueOf(c);
                    if (isLetter(str) || isNumber(str) || TextUtils.equals("-", str) || TextUtils.equals("_", str)) {
                        //字母数字下划线减号
                        isLegal = true;
                    } else {
                        return false;
                    }
                }
            }
        } else {
            //手机号
            isLegal = isMobileNumber(content);
        }
        return isLegal;
    }

    /**
     * 验证 是否为 字母
     *
     * @param content 要验证的字符
     * @return 是否是字母 true 是 false 不是
     */
    public static boolean isLetter(String content) {
        Pattern compile = Pattern.compile("[a-zA-Z]");
        Matcher matcher = compile.matcher(content);
        return !TextUtils.isEmpty(content) && matcher.matches();
    }

    /**
     * 验证 是否为 数字
     *
     * @param content 要验证的字符
     * @return 是否是数字 true 是 false 不是
     */
    public static boolean isNumber(String content) {
        Pattern compile = Pattern.compile("[0-9]");
        Matcher matcher = compile.matcher(content);
        return !TextUtils.isEmpty(content) && matcher.matches();
    }

    /**
     * 验证手机格式
     *
     * @param mobiles 要验证的手机号
     * @return 是否是手机号 true 是 false 不是
     */
    public static boolean isMobileNumber(String mobiles) {
        String telRegex = "[1]\\d{10}";
        return !TextUtils.isEmpty(mobiles) && mobiles.matches(telRegex);
    }

    /**
     * str 转化成long
     *
     * @param str
     * @return
     */
    public static Long getLongFromString(String str) {
        Long l = 0l;
        try {
            l = Long.parseLong(str);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return l;
    }

    /**
     * str 转化成int
     *
     * @param str
     * @return
     */
    public static Integer getIntegerFromString(String str) {
        int l = 0;
        try {
            l = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return l;
    }

    /**
     * 判断格式是否合法.
     *
     * @param type
     * @return
     */
    public static Boolean verifyTypeIsOK(JSONArray jsonArray, String type) {
        if (null != jsonArray) {
            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    // Log.i("ssss", type + ":" + jsonArray.getString(i));
                    if (type.equals(jsonArray.getString(i))) {
                        return true;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }
        return false;
    }

    /**
     * 构建Android/data根目录
     *
     * @return 文件夹的Android/data根目录
     */
    public static String getPackageDataPath(Context context, String typePath) {
        String path = "";
        File externalFilesDir = context.getExternalFilesDir(null);
        if (externalFilesDir != null) {
            if (TextUtils.isEmpty(typePath)) {
                path = externalFilesDir.getParentFile().getAbsolutePath() + File.separator;
            } else {
                path = externalFilesDir.getParentFile().getAbsolutePath() + File.separator +
                        typePath + File.separator;
            }
        } else {
            if (TextUtils.isEmpty(typePath)) {
                path = Environment.getExternalStorageDirectory().getAbsolutePath() +
                        File.separator + "Android" + File.separator + "data" + File.separator +
                        context.getPackageName() + File.separator;
            } else {
                path = Environment.getExternalStorageDirectory().getAbsolutePath() +
                        File.separator + "Android" + File.separator + "data" + File.separator +
                        context.getPackageName() + File.separator + typePath +
                        File.separator;
            }
        }
        return path;
    }
}
