package com.cocos.base.kotlinutils;

import static com.cocos.base.utils.KmfUtils.getNetWorkClass;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.provider.Settings;
import androidx.core.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

import com.cocos.base.utils.AssemblyPublicALLSharedPreUtil;
import com.cocos.base.utils.AssemblyUtils;
import com.cocos.base.utils.KmfUtils;

import java.util.Locale;
import java.util.Random;


/**
 * 工具类获得收集的配置信息
 * Created by kokuritsu on 17/10/17.
 */

public class SystemUtil {

    private static String SystemModel = "";
    private static String DeviceBrand = "";
    private static String IMEI = "";
    private static String SystemVersion = "";

    private static final String APP_VERSION = "K-A";
    private static final String SYSTEM_VERSION = "K-B";
    private static final String DEVICE_NANE = "K-C";
    private static final String DEVICE_ID_NAME = "K-D";
    public static final String IDFA_IMEI = "K-E";
    private static final String DEVICE_MODEL = "K-F";

    // 系统版本号
    private static String release;//
    // 手机模型
    private static String model;
    // 手机名称
    private static String manufacturer;
    // 手机厂商
    private static String brand;
    // 手机IMEI
    private static String DEVICE_ID;

    /**
     * 获取当前手机系统语言。
     *
     * @return 返回当前系统语言。例如：当前设置的是“中文-中国”，则返回“zh-CN”
     */
    public static String getSystemLanguage() {
        return Locale.getDefault().getLanguage();
    }

    /**
     * 获取当前系统上的语言列表(Locale列表)
     *
     * @return 语言列表
     */
    public static Locale[] getSystemLanguageList() {
        return Locale.getAvailableLocales();
    }

    /**
     * 获取当前手机系统版本号
     *
     * @return 系统版本号
     */

    public static String getSystemVersion(Context context) {
        if (TextUtils.isEmpty(SystemVersion)) {
            release = AssemblyPublicALLSharedPreUtil.getInstance(context).getString(APP_VERSION, "");
            if (release == null || TextUtils.isEmpty(release)) {
                release = Build.VERSION.RELEASE;
                AssemblyPublicALLSharedPreUtil.getInstance(context).putStringValue(APP_VERSION, release);
            }
        }
        SystemVersion = ("Android" + release).replace(" ", "");
        return SystemVersion;
    }

    public static String getVersion(Context context) {
        release = AssemblyPublicALLSharedPreUtil.getInstance(context).getString(APP_VERSION, "");
        if (release == null || TextUtils.isEmpty(release)) {
            release = Build.VERSION.RELEASE;
            AssemblyPublicALLSharedPreUtil.getInstance(context).putStringValue(APP_VERSION, release);
        }

        return release;
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
        Log.i("ssss","netWorkType:"+netWorkType);
        return netWorkType;
    }


    /**
     * 获取手机型号
     *
     * @return 手机型号
     */

    public static String getSystemModel(Context context) {
        if (TextUtils.isEmpty(SystemModel)) {
            model = AssemblyPublicALLSharedPreUtil.getInstance(context).getString(DEVICE_MODEL, "");
            if (model == null || TextUtils.isEmpty(model)) {
                model = Build.MODEL;
                AssemblyPublicALLSharedPreUtil.getInstance(context).putStringValue(DEVICE_MODEL, model);
            }
            SystemModel = changeIllegalCharacters(model);
        }
        return SystemModel;
    }

    public static String getModel(Context context) {
        model = AssemblyPublicALLSharedPreUtil.getInstance(context).getString(DEVICE_MODEL, "");
        if (model == null || TextUtils.isEmpty(model)) {
            model = Build.MODEL;
            AssemblyPublicALLSharedPreUtil.getInstance(context).putStringValue(DEVICE_MODEL, model);
        }
        return model;
    }

    /**
     * 获取手机名称
     *
     * @return 手机名称
     */

    public static String getSystemManufacturer(Context context) {
        manufacturer = AssemblyPublicALLSharedPreUtil.getInstance(context).getString("K-M", "");
        if (manufacturer == null || TextUtils.isEmpty(manufacturer)) {
            manufacturer = Build.MANUFACTURER;
            AssemblyPublicALLSharedPreUtil.getInstance(context).putStringValue("K-M", manufacturer);
            if (TextUtils.isEmpty(manufacturer)) {
                manufacturer = "Android";
            }
        }
        return manufacturer.replace(" ", "");
    }

    /**
     * 获取手机厂商
     *
     * @return 手机厂商
     */

    public static String getDeviceBrand(Context context) {
        brand = AssemblyPublicALLSharedPreUtil.getInstance(context).getString(DEVICE_NANE, "");
        if (TextUtils.isEmpty(brand)) {
            if (brand == null || TextUtils.isEmpty(brand)) {
                brand = Build.BRAND;
                AssemblyPublicALLSharedPreUtil.getInstance(context).putStringValue(DEVICE_NANE, brand);
            }
            DeviceBrand = changeIllegalCharacters(brand);
        }
        return DeviceBrand;
    }

    /**
     * 获取手机IMEI(需要“android.permission.READ_PHONE_STATE”权限)
     *
     * @return 手机IMEI
     */

    public static String getIMEI(Context context) {
        if (TextUtils.isEmpty(IMEI)) {
            if (context != null) {//context判空
                DEVICE_ID = AssemblyPublicALLSharedPreUtil.getInstance(context).getString(IDFA_IMEI, "");
                if (TextUtils.isEmpty(DEVICE_ID) || DEVICE_ID == null) {
                    if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE)
                            != PackageManager.PERMISSION_GRANTED) {
                        DEVICE_ID = Settings.System.getString(context.getContentResolver(), Settings.System.ANDROID_ID);
                    } else {
                        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                        DEVICE_ID = tm.getDeviceId();
                    }
                    if (TextUtils.isEmpty(DEVICE_ID)) {//校验数据是否为空
                        int randNum = new Random().nextInt(90) + 10;
                        DEVICE_ID = AssemblyPublicALLSharedPreUtil.getInstance(context).getInt("passport_id", 0) + randNum + "";
                    }
                    AssemblyPublicALLSharedPreUtil.getInstance(context).putStringValue(IDFA_IMEI, DEVICE_ID);
                } else {
                    IMEI = DEVICE_ID;
                }
            } else {
                DEVICE_ID = KmfUtils.createExamUnique();//实在拿不到数据，用时间唯一码
            }
            IMEI = DEVICE_ID.replace(" ", "");
        }
        return IMEI;
    }

    /**
     * 转换非法字符
     *
     * @param changeString 要转换的字符
     * @return 转换后的字符
     */
    private static String changeIllegalCharacters(String changeString) {
        String changedString;
        if (TextUtils.isEmpty(changeString)) {
            changedString = "Android";
        } else {
            if (AssemblyUtils.isContainChinese(changeString)) {
//                Bugtags.sendException(new Exception("手机厂商包含中文，名称是-->" + changeString));
                if (changeString.contains("大神")) {
                    changeString = changeString.replace("大神", "da_shen");
                } else if (changeString.contains("词歌")) {
                    changeString = changeString.replace("词歌", "ci_ge");
                } else if (changeString.contains("翼触")) {
                    changeString = changeString.replace("翼触", "yi_chu");
                } else if (changeString.contains("欧唯")) {
                    changeString = changeString.replace("欧唯", "o_wei");
                } else if (changeString.contains("华硕")) {
                    changeString = changeString.replace("华硕", "hua_shuo");
                }
            }
            //处理数据 避免字符出现问题
            for (int i = 0, length = changeString.length(); i < length; i++) {
                char c = changeString.charAt(i);
                if ((c <= '\u001f' && c != '\t') || c >= '\u007f') {
                    changeString = changeString.replace(c, ' ');
                }
            }
            changedString = changeString.replace(" ", "");
            if (TextUtils.isEmpty(changedString)) {
                changedString = "Android";
            }
        }

        return changedString;
    }

    /**
     * 用于判断上次获取敏感信息是否超过了30分钟
     *
     * @return
     */
    public static boolean getTimeBool(Context context, String type) {
        long currentTimeMillis = System.currentTimeMillis();
        long lastTimeMillis = 0L;
        lastTimeMillis = AssemblyPublicALLSharedPreUtil.getInstance(context).getLongValue(type);
        //1000L * 60 * 30
        if (currentTimeMillis - lastTimeMillis < 1000L * 10 && lastTimeMillis != 0) { //大于30分钟才重新获取
            return false;
        } else {
            AssemblyPublicALLSharedPreUtil.getInstance(context).putLongValue(type, currentTimeMillis);
            return true;

        }
    }

}
